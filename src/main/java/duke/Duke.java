package duke;

import duke.commands.Commands;
import duke.dukehelper.Parser;
import duke.dukehelper.Storage;
import duke.dukehelper.TaskList;
import duke.dukehelper.Ui;
import duke.dukehelper.uiparts.Statistics;
import duke.exception.DukeException;
import duke.helper.DateTimeHelper;
import duke.task.Task;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

public class Duke {
    private final static String WRONG_FORMAT_MSG = "Wrong format\n"
            + " Your date and time(optional) should be in this format:\n"
            + "yyyy-mm-dd HHmm\ne.g: 2019-10-15 1800 or 2019-10-15";
    public final static String ERROR_MSG = "OOPS!!! CAN YOU PLEASE TYPE SOMETHING MEANINGFUL?";
    private final static String GREETINGS = "Hello! I'm Elon Musk. Type 'help' if you know nothing\n"
            + "Your tasks will be saved at /data\nWhat can I do for you?";
    private final static String ERROR_LOAD_MSG = "Something wrong happened while loading saved tasks";
    private final static String FIRST_TIME = "This is the first time you use Duke!";
    public final static String ERROR_CODE = "000";
    public final static String EXIT_MSG = "Bye. Hope to see you again soon!";
    private final static String HELP_MSG = "Type 'help' if you know nothing";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private int[] statData;
    private Statistics statistics;
    /**
     * Constructor
     */
    public Duke() {
        this.statistics = new Statistics();
        this.ui = new Ui();
        this.storage = new Storage("data/save_file.txt");
        this.tasks = new TaskList();
        this.parser = new Parser();
        this.statData = new int[]{0,0,0};
    }
    private int getNumTasks() {
        return this.tasks.getTaskList().size();
    }

    /**
     * Lists all tasks with date/time filter
     * @param isLoaded
     * @param tokens
     * @return string representation of tasks
     * @throws DukeException
     */
    private String listTasks(boolean isLoaded, String[] tokens) throws DukeException {
        String[] extractedData = parser.extractData(isLoaded, tokens);
        String content = extractedData[0];
        DateTimeHelper dtHelper = DateTimeHelper.processDateTime(content);
        if (dtHelper != null) {
            LocalDate deadline = dtHelper.getDate();
            return tasks.filterTaskList(deadline);
        } else {
            throw new DukeException(WRONG_FORMAT_MSG);
        }
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

        //pass this to parser to hide storage from it
        Function<Void, Void> saveTaskListStorage = param -> {
            storage.saveTasks(tasks.getTaskList());
            return null;
        };

        if (commandType == Commands.DEADLINE || commandType == Commands.EVENT || commandType == Commands.TODO) {
            if(commandType == Commands.DEADLINE) {
                statData[1]++;
            } else if(commandType == Commands.EVENT) {
                statData[2]++;
            } else {
                statData[0]++;
            }
            statistics.saveData(statData);

            parsedTask = parser.parseTaskCommand(commandType, tokens, isLoaded, getNumTasks());
        } else if (commandType == Commands.DELETE) {

            int markNumber = Integer.parseInt(tokens[1]);
            return parser.parseOperationCommand(Commands.DELETE, markNumber, getNumTasks(), tasks, saveTaskListStorage);

        } else if (commandType == Commands.DONE) {

            int markNumber = Integer.parseInt(tokens[1]);
            return parser.parseOperationCommand(Commands.DONE, markNumber, getNumTasks(), tasks, saveTaskListStorage);

        }  else if (commandType == Commands.LIST) {

            return listTasks(isLoaded, tokens);

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
    protected String parsedCommand(String command, boolean isLoaded) throws DukeException {
        command = command.strip();
        if (command.equals("")) return "";
        String[] tokens = command.split(" ");
        try {
            return distributeCommand(Commands.valueOf(tokens[0].toUpperCase()), tokens, isLoaded);
        } catch (IllegalArgumentException e) {
            throw new DukeException(ERROR_MSG);
        }
    }

    /**
     * Reads input and return output to the user
     */
    protected String init() {
        String result = "";
        result += Ui.printDialog(GREETINGS);
        ArrayList<String> savedTasks = storage.loadSavedTasks();
        assert savedTasks != null : "Storage is null";

        if (savedTasks.size() > 0 && savedTasks.get(0).equals("000")) {
            result += Ui.printDialog("This is the first time you use Duke!");
        } else {
            try {
                for (String task : savedTasks) {
                    parsedCommand(task, true);
                }
            } catch (DukeException e) {
                result += Ui.printDialog(ERROR_LOAD_MSG);
            }
        }
        return result;
    }
    public String getResponse(String content) {
        assert content != null : "Response is null";

        content = content.strip();
        if (content.equals(Commands.BYE.getAction())) {
            return Ui.printDialog(EXIT_MSG);
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
        } else if (content.equals(Commands.STAT.getAction())) {
            return "GET_CHART";
        } else {
            try {
                String result = parsedCommand(content, false);
                if (!result.equals("")) return Ui.printDialog(result);
            } catch (DukeException e) {
                return Ui.printDialog(e.getMessage());
            }
        }
        return HELP_MSG;
    }
}

