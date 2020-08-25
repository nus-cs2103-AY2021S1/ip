public class Text {

    //Bling msgs
    private static String top = "----------Bot made by Hendey Fan----------";
    private static String bottom = "------------------------------------------";
    private static String startMessage = "This is a chat bot made by Hendey Fan.\n" +
            "What can this magnificent bot do for you?";
    private static String endMessage = "    oi don run away from ur tasks hor    ";

    // Error msgs
    private static String taskNotFound = "Error: task not found :(";
    private static String commandNotFound = "Error: command does not exist :(\n" +
            "Type '!command' for list of commands";
    private static String descriptionNotFound = "Error: description of task cannot be empty";
    private static String taskNumNotSpecified = "Error: task number not specified";
    private static String timeNotFound = "Error: time not specified";
    private static String dateTimeFormatError = "Error: date time in an incorrect format\n" +
            "Please follow this format: dd/MM/yyyy HHmm";

    // static info/display strings
    private static String commandList = "list, bye, done, todo, deadline, event, delete, !command";
    public static String doneText = "[Done] ";
    public static String notDoneText = "[Nope] ";

    public static void normalPrint(String middle) {
        System.out.println(top + "\n" + middle + "\n" + bottom+ "\n");
    }

    public static void printStartMessage() {
        normalPrint(startMessage);
    }

    public static void printEndMessage() {
        normalPrint(endMessage);
    }

    public static void printTaskNotFoundError() {
        normalPrint(taskNotFound);
    }

    public static void printCommandNotFoundError() {
        normalPrint(commandNotFound);
    }

    public static void printDescriptionNotFoundError() {
        normalPrint(descriptionNotFound);
    }

    public static void printTaskNumNotSpecifiedError() {
        normalPrint(taskNumNotSpecified);
    }

    public static void printTimeNotFoundError() { normalPrint(timeNotFound);}

    public static void printDateTimeFormatError() { normalPrint(dateTimeFormatError);}

    public static void printCommands() { normalPrint(commandList); }
}
