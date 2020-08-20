public class Text {

    private static String top = "----------Bot made by Hendey Fan----------";
    private static String bottom = "------------------------------------------";
    private static String startMessage = "This is a chat bot made by Hendey Fan.\n" +
            "What can this magnificent bot do for you?";
    private static String endMessage = "    Oi don run away from ur tasks hor    ";
    private static String taskNotFound = "Error: No task found :(";
    private static String commandNotFound = "Error: Command does not exist :(\n Type '!command]' for list of commands";
    private static String descriptionNotFound = "Error: description of task cannot be empty";

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
}
