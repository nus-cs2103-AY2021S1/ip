package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeCommandException;
import duke.exception.DukeIndexException;
import duke.exception.DukeListException;
import duke.exception.DukeTaskException;

/**
 * Parser class to parse through the input given by the user.
 */
public class Parser {

    private String parseMessage;
    private UserInterface ui;
    private TaskList taskList;

    /**
     * Constructor for Parser.
     * @param parseMessage Message that has to be parsed by the parser.
     * @param ui UserInterface for the parser to trigger printing events.
     * @param taskList taskList that the Parser needs to keep track of.
     */
    public Parser(String parseMessage, UserInterface ui, TaskList taskList) {
        this.parseMessage = parseMessage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Initialises the parser to start processing the parseMessage. Continues until user terminates.
     * @return the response by Duke.
     */
    public String start() {

        try {
            String command = parseMessage.split(" ")[0];
            String response = "";
            if (command.equals("bye")) {
                ByeCommand byeCommand = new ByeCommand();
                response = byeCommand.execute(taskList, ui);
            } else if (command.equals("list")) {
                ListCommand listCommand = new ListCommand();
                response = listCommand.execute(taskList, ui);
            } else if (command.equals("find")) {
                String wordToFind = this.parseMessage.substring(command.length());
                FindCommand findCommand = new FindCommand(wordToFind);
                response = findCommand.execute(taskList, ui);
            } else if (command.equals("done")) {
                int index = Integer.parseInt(parseMessage.split(" ")[1]) - 1;
                DoneCommand doneCommand = new DoneCommand(index);
                response = doneCommand.execute(taskList, ui);
            } else if (command.equals("delete")) {
                int index = Integer.parseInt(parseMessage.split(" ")[1]) - 1;
                DeleteCommand deleteCommand = new DeleteCommand(index);
                response = deleteCommand.execute(taskList, ui);
            } else if (command.equals("event") || command.equals("deadline") || command.equals("todo")) {
                String dissectedMessage = this.parseMessage.substring(command.length());
                response = executeTask(command, dissectedMessage);
            } else {
                String errorMessage = "Command is wrong. Please start with delete, "
                        + "list, done, deadline, todo or event.";
                throw new DukeCommandException(errorMessage);
            }
            this.taskList.updateStorage();
            return response;
        } catch (DukeCommandException | DukeIndexException | DukeTaskException | DukeListException e) {
            return ui.printError(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.printError("INPUT DATE TIME FORMAT IS WRONG");
        } catch (IOException e) {
            return ui.printError(e.getMessage());
        }
    }

    /**
     * Helper method to organize the different types of task that can be recorded.
     * @param tasktype Type of task to be recorded.
     * @param message Details of the task.
     * @return response from Duke.
     * @throws DukeTaskException When there is an error in the input.
     */
    public String executeTask(String tasktype, String message) throws DukeTaskException {

        String[] parsedMessage = null;
        Task newTask = null;
        int taskListSize = this.taskList.getTaskSize();

        try {

            if (message.isEmpty()) {
                throw new IndexOutOfBoundsException();
            } else if (tasktype.equals("todo")) {
                newTask = new Todo(message);
            } else if (tasktype.equals("deadline")) {
                parsedMessage = message.split("/by ");
                newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
            } else if (tasktype.equals("event")) {
                parsedMessage = message.split("/at ");
                newTask = new Event(parsedMessage[0], parsedMessage[1]);
            }

            this.taskList.addTask(newTask);
            return ui.printAddTask(newTask.toString(), this.taskList.getTaskSize());

        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "You might have left your message or duration empty.";
            throw new DukeTaskException(errorMessage);
        }
    }

    /**
     * retrieveResponse method to retrieve the response printed by Duke.
     * @return response by Duke.
     */
    public String retrieveResponse() {
        return this.start();
    }
}
