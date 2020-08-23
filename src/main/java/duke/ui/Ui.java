package duke.ui;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER =
            "\t____________________________________________________________\n";
    private static final String LOGO =
            " _____     __   __   ________   _________\n"
            + "|     \\   |  | |  | |__    __| |   ______|\n"
            + "|  |\\  \\  |  | |  |    |  |    |  |______\n"
            + "|  | \\  \\ |  | |  |    |  |    |   ______|\n"
            + "|  |  \\  \\|  | |  |    |  |    |  |______\n"
            + "|__|   \\_____| |__|    |__|    |_________|\n";
    private static final String GREETING =
            "\t Hello! I'm Nite, the Dark Knight,\n"
            + "\t Here to help you track your tasks and achieve a great night.\n"
            + "\t What can I do for you?\n";
    private static final String GOODBYE =
            "\t Good nite! Hope you have a good night's sleep. See you soon!\n";

    public Ui() {
    }

    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(DIVIDER + GREETING + DIVIDER);
    }

    public void showFarewell() {
        System.out.print(DIVIDER + GOODBYE + DIVIDER);
    }

    public void showError(String errorMessage) {
        String enhancedMessage = "\t :-( OOPS!!! " + errorMessage + "\n";
        System.out.println(DIVIDER + enhancedMessage + DIVIDER);
    }

    public void showAction(String actionMessage) {
        System.out.println(DIVIDER + actionMessage + DIVIDER);
    }

    public void showLoadingError() {
        System.out.println("Unable to load Tasklist");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
