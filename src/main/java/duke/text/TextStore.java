package duke.text;

public class TextStore {

    // Maybe consider changing this class into a text file of sorts and be loaded at the start as an experiment
    // Public messages means that they are referenced outside to craft messages with other strings (ie strings from task)

    // Bling msgs
    protected final static String top = "-------------Bot made by Hendey Fan-------------";
    protected final static String errorBling = "--------------!*!*!ERROR DETECTED!*!*!--------------";
    protected final static String bottom = "-------------------------------------------------------";
    protected final static String startMessage = "This is a task management bot made by Hendey Fan.\n" +
            "       Bot is currently starting up BRRRRRRRRRRR";
    protected final static String endMessage = "         oi don run away from ur tasks hor    \n" +
            "        Window will now close in 5 seconds";

    // Error msgs
    protected final static String taskNotFound = "Error: task not found :(";
    protected final static String commandNotFound = "Error: command does not exist :(\n" +
            "Type '!commands' for list of commands and instructions";
    protected final static String descriptionNotFound = "Error: description of task cannot be empty\n" +
            "Please check command formats using !commands";
    protected final static String taskNumNotSpecified = "Error: task number not specified\n" +
            "Please check command formats using !commands";
    protected final static String timeNotFound = "Error: time not found\n" +
            "Please check command formats using !commands";
    protected final static String dateTimeFormatError = "Error: date time in an incorrect format\n" +
            "Please follow this format: <dd/MM/yyyy HHmm>";
    protected final static String noSearchTermError = "Error: search term not specified\n" +
            "Please check command formats using !commands";

    // Status msgs
    public final static String saveFound = "Status: previous save found and loaded";
    public final static String saveNotFound = "Status: no previous save found\n" +
            "Will create new save when application closes";
    public final static String tasksSavedToTextFile = "Status: tasks saved to text file successfully";

    // static info/display strings
    public final static String doneText    = "[Complete] ";
    public final static String notDoneText = "[ToBeDone] ";
    protected final static String commandList =
            "1) !commands | returns a list of text commands\n" +
                    "2) list | lists out all the current tasks\n" +
                    "3) bye | saves the current tasks into a file and exits program\n" +
                    "4) done | format: \"done <task number>\", marks a task in the list as done\n" +
                    "5) todo | format: \"todo <description>\", creates a todo task\n" +
                    "6) deadline | format: \"deadline <description> /by <dd/MM/yyyy HHmm>\", creates a deadline task\n" +
                    "7) event | format: \"event <description> /at <dd/MM/yyyy HHmm>\", creates an event task\n" +
                    "8) delete | format: \"delete <task number>\", deletes a task in the list\n" +
                    "9) find | format: \"find <string>\", finds tasks with matching string";
    protected final static String promptMsg = "Type \"!commands\" for a list of commands and formats :)";

    // Strings used by TaskList class
    public final static String listOutHeader = "Here are the tasks in your list:";
    public final static String searchListHeader = "Here are the matching tasks in your list:";
}
