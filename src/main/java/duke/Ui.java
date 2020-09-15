package duke;

import java.util.Scanner;

public class Ui {
//    private final Scanner sc;

//    public Ui() {
//        this.sc = new Scanner(System.in);
//    }

//    /**
//     * Creates a 'frame' for the output that is being printed
//     *
//     * @param s The output that is going to get printed.
//     */
    public static void printMessage(String s) {
        String output = String.format("____________________________________________________________\n%s\n"
                + "____________________________________________________________\n", s);
        System.out.println(output);
    }

    /**
     * Prints message in exception.
     *
     * @param e Duke Exception
     */
    public static void printException(Exception e) {
        String errorMessage = "Whoops! Something went wrong...  DukeException: " + e.getMessage();
        printMessage(errorMessage);
    }


    public void showWelcomeMessage() {
        String greetings = "Hello! I'm Duke, your personal assistant.\nWhat can I do for you?";
        printMessage(greetings);
    }

    public void showStartFailedMessage() {
        String errorMessage = "An error occurred in during initialisation :(";
        printMessage(errorMessage);
    }

    public void showGoodbyeMessage() {
        String byeMessage = "Bye. Take care!!";
        printMessage(byeMessage);
    }

//    /**
//     * Returns what the user input.
//     *
//     * @return One line of user input.
//     */
//    public String getUserInput() {
//        return sc.nextLine();
//    }
}
