package duke;
import java.util.Scanner;

public class Ui {
    public static final String INDENT = "    ";
    public static final String DIVIDER = INDENT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints Text to screen in nicely formatted form.
     *
     * @param str  String to be printed.
     */
    public void print(String str) {
        String[] arr = str.split("\n");
        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(INDENT).append(s).append("\n");
        }
        String intro = DIVIDER + res.toString() + DIVIDER;
        System.out.println(intro);
    }

    /**
     * Prints Welcome screen.
     */
    public void showWelcome() {
        String logo = " .----------------.  .----------------.  .----------------.  .----------------.\n"
                + "| .--------------. || .--------------. || .--------------. || .--------------. |\n"
                + "| |  ________    | || |     ____     | || |     ____     | || |  ___  ____   | |\n"
                + "| | |_   ___ `.  | || |   .'    `.   | || |   .'    `.   | || | |_  ||_  _|  | |\n"
                + "| |   | |   `. \\ | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |_/ /    | |\n"
                + "| |   | |    | | | || |  | |    | |  | || |  | |    | |  | || |   |  __'.    | |\n"
                + "| |  _| |___.' / | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  \\ \\_  | |\n"
                + "| | |________.'  | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n"
                + "| |              | || |              | || |              | || |              | |\n"
                + "| '--------------' || '--------------' || '--------------' || '--------------' |\n"
                + " '----------------'  '----------------'  '----------------'  '----------------'\n";
        String intro = "\n\n" + logo + "\n" + DIVIDER + INDENT + "Hola! I am dook\n" + INDENT
                + "how i can help u?\n" + DIVIDER;
        System.out.println(intro);
    }
    public String readCommand() {
        return sc.nextLine();
    }
    public void showExit() {
        print("see u later alligator");
    }
}
