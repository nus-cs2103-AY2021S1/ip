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

    /**
     * Constructor for the Duke Chatbot, if is old initialisation, will read from txt file
     * Eles it will initialise a new TaskManager class
     * @param path The path of the home initialisation.
     */
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
     * Manages all internal dataflow from Main or textual interaction
     * with the chatbot, by cleaning it
     * @param input User Input from the UI
     * @return String form of command to output to UI
     * @throws DukeException when there is an exception thrown
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
            case SEARCH:
                return taskManager.findTasks(words[1]);
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
     * @param userInput Direct user input of the string
     * @return Cleaned user input
     */
    private String cleanInput(String userInput) {
        return userInput + " [sep]";
    }

    /**
     * Returns a help message about all commands supported by Duke
     * @return help message
     */
    public String help() {
        //eventually to add command help <command>
        return "\t Need some help huh?\n" +
                "\t Heres a list of my commands!\n" +
                "\t- 'bye' to close the application\n" +
                "\t- 'list' to list the current list of tasks and their statuses\n" +
                "\t- 'done' to set a task as done\n" +
                "\t- 'find' to find a task using regex or a query text string\n" +
                "\t- 'todo' to list a untimed task\n" +
                "\t- 'deadline' to list a timed deadline task, please structure with " +
                "[deadline <task name> /by dd-MM-YYYY]\n" +
                "\t- 'event' to list a timed event task, please structure with [event <task name> /at dd-MM-YYYY]\n" +
                "\t- 'help' to list these commands again\n";
    }

    /**
     * Greeting from Duke Bot
     * @param name Name of the user
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

    /**
     * Goodbye from DukeBot
     * @param name Name of the user
     * @return Sends a goodbye message from dukebot to the user
     */
    String goodbye(String name){
        return "Bye " + name +"! Hope to see you again soon!";
    }

    /**
     * Message passing from mainloop to save tasks.
     * @throws DukeIOException if something goes wrong with the IO Savefiles
     */
    void saveTasks() throws DukeIOException {
        this.taskManager.saveTasks();
    }
}
