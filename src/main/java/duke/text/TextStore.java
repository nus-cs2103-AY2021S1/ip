package duke.text;

public class TextStore {


    // Bling msgs
    protected final static String top = "--------------Bot made by Hendey Fan--------------";
    protected final static String errorBling = "--------------!!!!!ERROR DETECTED!!!!!--------------";
    protected final static String bottom = "--------------------------------------------------------";
    protected final static String startMessage = "This is a chat bot made by Hendey Fan.\n" +
            "What can this magnificent bot do for you?";
    protected final static String endMessage = "    oi don run away from ur tasks hor    ";

    // Error msgs
    protected final static String taskNotFound = "Error: task not found :(";
    protected final static String commandNotFound = "Error: command does not exist :(\n" +
            "Type '!commands' for list of commands and instructions";
    protected final static String descriptionNotFound = "Error: description of task cannot be empty";
    protected final static String taskNumNotSpecified = "Error: task number not specified";
    protected final static String timeNotFound = "Error: time not specified";
    protected final static String dateTimeFormatError = "Error: date time in an incorrect format\n" +
            "Please follow this format: dd/MM/yyyy HHmm";
    protected final static String noSearchTermError = "Error: search term not defined";

    // Status msgs
    public final static String saveFound = "Status: previous save found and loaded";
    public final static String saveNotFound = "Status: no previous save found\n" +
            "Will create new save when application closes";
    public final static String tasksSavedToTextFile = "Status: tasks saved to text file successfully";

    // static info/display strings
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

    public final static String doneText    = "[Complete] ";
    public final static String notDoneText = "[ToBeDone] ";
    protected final static String promptMsg = "Type \"!commands\" for a list of commands and formats :)";
}
