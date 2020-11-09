package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.exception.DukeTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Parser class to parse through the input given by the user.
 */
public class Parser {

    private String parseMessage;
    private UserInterface ui;
    private TaskList taskList;

    /**
     * Constructor for Parser.
     *
     * @param parseMessage Message that has to be parsed by the parser.
     * @param ui UserInterface for the parser to trigger printing events.
     * @param taskList taskList that the Parser needs to keep track of.
     */
    public Parser(String parseMessage, UserInterface ui, TaskList taskList) {
        this.parseMessage = (parseMessage);
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Initialises the parser to start processing the parseMessage. Continues until user terminates.
     *
     * @return the response by Duke.
     */
    public String start() {
        try {
            String command = parseMessage.split(" ")[0];
            String response = "";
            if (command.equals("bye")) {
                ByeCommand byeCommand = new ByeCommand();
                response = byeCommand.execute(taskList, ui);
            } else if (command.equals("sort")) {
                response = new SortCommand().execute(taskList, ui);
            } else if (command.equals("list")) {
                response = new ListCommand().execute(taskList, ui);
            } else if (command.equals("find")) {
                String wordToFind = parseMessage.substring(command.length());
                response = new FindCommand(wordToFind).execute(taskList, ui);
            } else if (command.equals("done")) {
                int index = Integer.parseInt(parseMessage.split(" ")[1]) - 1;
                response = new DoneCommand(index).execute(taskList, ui);
            } else if (command.equals("help")) {
                response = new HelpCommand().execute(taskList, ui);
            } else if (command.equals("delete")) {
                int index = Integer.parseInt(parseMessage.split(" ")[1]) - 1;
                response = new DeleteCommand(index).execute(taskList, ui);
            } else if (checkProperCommand(command)) {
                String dissectedMessage = parseMessage.substring(command.length());
                response = executeTask(command, dissectedMessage);
            } else {
                String errorMessage = "Command is wrong. Please start with delete, "
                        + "list, done, deadline, todo or event.";
                throw new DukeCommandException(errorMessage);
            }
            this.taskList.updateStorage();
            return response;
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Tell you index need put properly already.");
        } catch (DateTimeParseException e) {
            return ui.printError("Read Properly Please: yyyy-MM-dd HH:mm");
        } catch (DukeException | IOException e) {
            return ui.printError(e.getMessage());
        }
    }

    /**
     * Method to check if the input command matches event, deadline or todo.
     *
     * @param command input given by user.
     * @return boolean if the command is correctly typed.
     */
    public boolean checkProperCommand(String command) {
        return command.equals("event") || command.equals("deadline") || command.equals("todo");
    }

    /**
     * Helper method to organize the different types of task that can be recorded.
     *
     * @param taskType Type of task to be recorded.
     * @param message Details of the task.
     * @return response from Duke.
     * @throws DukeTaskException When there is an error in the input.
     */
    public String executeTask(String taskType, String message) throws DukeTaskException {

        String[] parsedMessage = null;
        Task newTask = null;
        int sizeofTaskList = this.taskList.getTaskSize();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (message.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else if (taskType.equals("todo")) {
                newTask = new Todo((message));
            } else if (taskType.equals("deadline")) {
                parsedMessage = (message).split("/by ");
                System.out.print(parsedMessage[1]);
                newTask = new Deadline(parsedMessage[0], LocalDateTime.parse(parsedMessage[1], formatter));
            } else if (taskType.equals("event")) {
                parsedMessage = message.split("/at ");
                newTask = new Event(parsedMessage[0], LocalDateTime.parse(parsedMessage[1], formatter));
            }

            this.taskList.addTask(newTask);
            assert newTask != null;

            return ui.printAddTask(newTask.toString(), sizeofTaskList + 1);

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            String errorMessage = "You might have left your message or duration empty.";
            throw new DukeTaskException(errorMessage);
        }
    }

    /**
     * retrieveResponse method to retrieve the response printed by Duke.
     *
     * @return response by Duke.
     */
    public String retrieveResponse() {
        return this.start();
    }
}
