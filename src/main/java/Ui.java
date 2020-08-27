import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor used to create UI .
     */
    public Ui() {
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(DukeException e) {
        printOutput(e.getMessage(), true);
    }

    public void printOutput(String input, boolean hasLastNewLine) {
        if (hasLastNewLine) {
            System.out.println("    ____________________________________________________________\n"
                    + input + "\n"
                    + "    ____________________________________________________________");

        } else {
            System.out.println("    ____________________________________________________________\n"
                    + input + "    ____________________________________________________________");
        }
    }

    public void showWelcome() {
        printOutput("     Hello! I'm Duke\n"
                + "     What can I do for you?", true);
    }

    public void showExit() {
        printOutput("     Bye. Hope to see you again soon!", true);
    }
}
