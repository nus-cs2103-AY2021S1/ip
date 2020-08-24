import java.util.Scanner;

public class Ui {
    // String for output format
    private static final String UNDERSCORE = "_________________________________________" +
            "______________________________________________";
    static Scanner sc = new Scanner(System.in);

    private static final String ALL_COMMANDS = "\n    You can use the following commands in this chat bot:"
            + (Commands.TODO).getUsage()
            + (Commands.DEADLINE).getUsage()
            + (Commands.EVENT).getUsage()
            + (Commands.LIST).getUsage()
            + (Commands.DONE).getUsage()
            + (Commands.DELETE).getUsage()
            + (Commands.SCHEDULED).getUsage()
            + (Commands.BYE).getUsage();

    public static void greet () {
        reply("\n    Hello! I'm Dobby" + ALL_COMMANDS + "\n    How can I help you?\n    ");
    }

    public static String getInput() {
         String text = sc.nextLine();
         if (text.equals("bye")) {
             sc.close();
         }
         return text;
    }
    public static void reply(String message) {
        System.out.println("    " + UNDERSCORE + message + UNDERSCORE);
    }
}
