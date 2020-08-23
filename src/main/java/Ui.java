public class Ui {
    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++";

    private static String formatMessage(String msg) {
        return SEPARATOR + "\n" + msg + "\n" + SEPARATOR;
    }

    public static void showMessage(String msg) {
        System.out.println(formatMessage(msg));
    }

    public static void showWelcomeMessage() {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");
    }

    public static void showErrorMessage(Exception e) {
        System.out.println(formatMessage("☹ OOPS!!! " + e));
    }

    public static void showFarewellMessage() {
        System.out.println(formatMessage("Goodbye! Hope to see you again soon!"));
    }
}
