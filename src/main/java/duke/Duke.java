package duke;

import duke.commands.Commands;
import duke.dukehelper.Parser;
import duke.dukehelper.Storage;
import duke.dukehelper.TaskList;
import duke.dukehelper.Ui;
import duke.exception.DukeException;
import duke.helper.DateTimeHelper;
import duke.task.Task;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Stage stage;
    /**
     * Constructor
     */
    public Duke(Stage stage) {
        this.ui = new Ui();
        this.storage = new Storage("data/save_file.txt");
        this.tasks = new TaskList();
        this.parser = new Parser();
        this.stage = stage;
    }
    private int getNumTasks() {
        return this.tasks.getTaskList().size();
    }

    /**
     * Distributes each command based on its type, then return a string that will be shown to the user.
     * @param commandType
     * @param tokens
     * @param isLoaded
     * @return message to user
     * @throws DukeException
     */
    protected String distributeCommand(Commands commandType, String[] tokens, boolean isLoaded) throws DukeException {
        Task parsedTask = new Task("");
        if (commandType == Commands.DEADLINE || commandType == Commands.EVENT || commandType == Commands.TODO) {
            parsedTask = parser.parseCommand(commandType, tokens, isLoaded, getNumTasks());
        } else if (commandType == Commands.DELETE) {

            int markNumber = Integer.parseInt(tokens[1]);
            storage.saveTasks(this.tasks.getTaskList());
            String result = tasks.deleteTask(markNumber, getNumTasks());
            storage.saveTasks(this.tasks.getTaskList());
            return result;

        } else if (commandType == Commands.DONE) {

            int markNumber = Integer.parseInt(tokens[1]);
            String result = tasks.doneTask(markNumber);
            storage.saveTasks(this.tasks.getTaskList());
            return result;

        }  else if (commandType == Commands.LIST) {

            String[] extractedData = parser.extractData(isLoaded, tokens);
            String content = extractedData[0];
            DateTimeHelper dtHelper = DateTimeHelper.processDateTime(content);
            if (dtHelper != null) {
                LocalDate deadline = dtHelper.getDate();
                return tasks.filteredTaskList(deadline);
            } else {
                throw new DukeException("Wrong format\n"
                        + " Your date and time(optional) should be in this format:\n"
                        + "yyyy-mm-dd HHmm\ne.g: 2019-10-15 1800 or 2019-10-15");
            }

        } else if (commandType == Commands.FIND) {
            return tasks.findTasks(tokens);
        } else if (commandType == Commands.BYE || commandType == Commands.HELP) {
            throw new DukeException("Wrong format of command " + "'" + commandType.getAction() + "'");
        }

        if (!parsedTask.getContent().equals("")) {
            this.tasks.addTask(parsedTask);
        }
        storage.saveTasks(this.tasks.getTaskList());
        return parsedTask.getUiOutput();
    }

    /**
     * Strips trailing whitespaces and tokenizes each command before further processing
     * @param command
     * @param isLoaded
     * @return message to user
     * @throws DukeException
     */
    protected String tokenizeCommand(String command, boolean isLoaded) throws DukeException {
        command = command.strip();
        if (command.equals("")) return "";
        String[] tokens = command.split(" ");
        try {
            return distributeCommand(Commands.valueOf(tokens[0].toUpperCase()), tokens, isLoaded);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! CAN YOU PLEASE TYPE SOMETHING MEANINGFUL?");
        }
    }

    /**
     * Reads input and return output to the user
     */
    protected String init() {
        String result = "";
        result += Ui.printDialog("Hello! I'm Elon Musk. Type 'help' if you know nothing\n"
                + "Your tasks will be saved at /data\nWhat can I do for you?");
        ArrayList<String> savedTasks = storage.loadSavedTasks();

        assert savedTasks != null : "Storage is null";

        if (savedTasks.size() > 0 && savedTasks.get(0).equals("000")) {
            result += Ui.printDialog("This is the first time you use Duke!");
        } else {
            try {
                for (String task : savedTasks) {
                    tokenizeCommand(task, true);
                }
            } catch (DukeException e) {
                result += Ui.printDialog("Something wrong happened while loading saved tasks");
            }
        }
        return result;
    }
    public String getResponse(String content) {
        assert content != null : "Response is null";

        content = content.strip();
        if (content.equals(Commands.BYE.getAction())) {
            this.stage.close();
            //exit the program
            return Ui.printDialog("Bye. Hope to see you again soon!");
        }
        if (content.equals(Commands.HELP.getAction())) {
            String res = "";
            for(Commands command: Commands.values()) {
                res += command.getAction() + ": " + command.getDescription();
                res += "\n    ";
            }
            return Ui.printDialog(res);
        } else if (content.equals(Commands.LIST.getAction())) {
            return Ui.printStoredTasks(this.tasks.getTaskList());
        } else {
            try {
                String result = tokenizeCommand(content, false);
                if (!result.equals("")) return Ui.printDialog(result);
            } catch (DukeException e) {
                return Ui.printDialog(e.getMessage());
            }
        }
        return "Type 'help' if you know nothing";
    }
}

