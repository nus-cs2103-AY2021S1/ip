package duke.task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    public static final String INDENT = "    ";
    public static final String divider = INDENT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void print(String str) {
        String[] arr = str.split("\n");
        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(INDENT).append(s).append("\n");
        }
        String intro = divider + res.toString() + divider;
        System.out.println(intro);
    }
    public void showWelcome() {
        String logo =
                " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                        "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                        "| |  ________    | || |     ____     | || |     ____     | || |  ___  ____   | |\n" +
                        "| | |_   ___ `.  | || |   .'    `.   | || |   .'    `.   | || | |_  ||_  _|  | |\n" +
                        "| |   | |   `. \\ | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |_/ /    | |\n" +
                        "| |   | |    | | | || |  | |    | |  | || |  | |    | |  | || |   |  __'.    | |\n" +
                        "| |  _| |___.' / | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  \\ \\_  | |\n" +
                        "| | |________.'  | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                        "| |              | || |              | || |              | || |              | |\n" +
                        "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                        " '----------------'  '----------------'  '----------------'  '----------------'\n";
        String intro =
                "\n" +
                "\n" +
                logo +
                "\n" +
                        divider +
                        INDENT +
                "Hola! I am dook\n" +
                        INDENT +
                "how i can help u?\n" + divider;
        System.out.println(intro);
    }
    public String readCommand() {
        return sc.nextLine();
    }
    public void showExit() {
        print("see u later alligator");
    }
}