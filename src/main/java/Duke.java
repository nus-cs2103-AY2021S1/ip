import exceptions.*;
import tasks.Command;
import tasks.TaskManager;
import tasks.TextParser;

/**
 * Backend Object Class for the Duke Chatbot Interface
 */
class Duke {
    private final TaskManager taskManager;
    private final TextParser textParser;

    public Duke(String path) {
        TaskManager list1;
        try {
            list1 = new TaskManager(path);
        } catch (DukeIOException e) {
            list1 = new TaskManager(path, true);
        }
        this.textParser = new TextParser();
        this.taskManager = list1;
    }

    /**
     * Re
     *
     * @param input
     * @return String form of command to output to UI
     * @throws DukeUnknownException
     */
    public String takeInput(String input) throws DukeException {
        //To prevent an Security Concern or Code Injection Cleaning of text is first performed and authenticated
        // by adding an ending token
        //TODO eventually to convert the input -> Command with getter for task, deadline(if applicable)
        String cleaned = cleanInput(input);
        // There is minimally a sep
        String[] words = cleaned.split(" ");
        cleaned = cleaned.replace(" [sep]", "");
        // Take out command from the words
        String text_input = cleaned.replaceFirst(words[0], "");
        //Sep token is added to prevent index errors
        Command c = textParser.parseCommand(words[0]);

        switch (c) {
            case BYE:
                return Boolean.TRUE.toString();
            case HELP:
                return this.help();
            case DONE:
                return taskManager.doTask(words[1]);
            case DELETE:
                return taskManager.deleteTask(words[1]);
            case LIST:
                return taskManager.parseoutput();
            case TODO:
                return this.taskManager.addToDo(text_input);
            case DEADLINE:
                return this.taskManager.addDeadline(text_input);
            case EVENT:
                return this.taskManager.addEvent(text_input);
            case BLANK:
                throw new DukeBlankCommandException("''");
            case ERROR:
                throw new DukeCommandException(words[0]);
            default:
                throw new DukeUnknownException(text_input);

        }
    }


    /**
     * inputs string, processes and cleans the text for the chatbot
     * via adding a ending token seperator
     *
     * @param userInput
     * @return
     */
    private String cleanInput(String userInput) {
        return userInput + " [sep]";
    }

    public String help() {
        //eventually to add command help <command>
        return "\t Need some help huh?\n" +
                "\t Heres a list of my commands!\n" +
                "\t- 'bye' to close the application\n" +
                "\t- 'list' to list the current list of tasks and their statuses\n" +
                "\t- 'done' to set a task as done\n" +
                "\t- 'todo' to list a untimed task\n" +
                "\t- 'deadline' to list a timed deadline task, please structure with [deadline <task name> /by <time>]\n" +
                "\t- 'event' to list a timed event task, please structure with [event <task name> /at <time>\n" +
                "\t- 'help' to list these commands again\n";
    }

    /**
     * Greeting from Duke Bot
     * @return Sends a greeting from dukebot to the user
     */
    String greeting(String name) {

        String logo = "\tHello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "\tHello! I'm Duke\n\tWhat can I do for you " +
                name +
                "\n";
        return logo;
    }

    String goodbye(String name) {
        return "Bye " + name + "! Hope to see you again soon!";
    }

    void saveTasks() throws DukeIOException {
        this.taskManager.saveTasks();
    }
}
