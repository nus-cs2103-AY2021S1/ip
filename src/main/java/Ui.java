import java.util.Scanner;

public class Ui {

    private static final String HORIZONTAL_LINE =
            "_________________________________________________________________________________________";

    public static void startUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        horizontalLine();
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        //horizontalLine();
    }

    public static void horizontalLine() {
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    // Prompts user to confirm
    public static boolean promptConfirm(Scanner sc) {
        System.out.println("Are you sure? (Y/N)");
        horizontalLine();
        String input = sc.nextLine();
        horizontalLine();
        return input.toLowerCase().equals("y") ? true : false;
    }
}