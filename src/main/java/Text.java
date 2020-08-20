public class Text {

    private static String top = "----------Bot made by Hendey Fan----------";
    private static String bottom = "----------Bot made by Hendey Fan----------";
    private static String startMessage = "Hello, I'm a chat bot made by Hendey Fan.\n" +
            "What can this magnificent bot do for you?";
    private static String endMessage = "           Bye! Bot has closed.";
    private static String taskNotFound = "Error: No such task found :(";

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
}
