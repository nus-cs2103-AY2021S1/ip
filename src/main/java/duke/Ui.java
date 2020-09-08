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

    public static String getWelcome() {
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
        String intro = "Hola! I am dook, how can i help you?:)";
        return intro;
    }
}
