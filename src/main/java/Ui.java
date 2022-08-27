import java.util.Scanner;

public class Ui {
    public static void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void showLoadingError() {
        System.out.println("Sorry, an error has occured");
    }

    public static String getCommand() {
        Scanner nextCommand = new Scanner(System.in);
        return nextCommand.nextLine();
    }

    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
