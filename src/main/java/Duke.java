import java.util.Scanner;

public class Duke {
    boolean echoInput(String input) {
        if (input.equals("bye")) {
            exit();
            return true;
        }
        System.out.println(
            "    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n    "
            + input
            + "\n    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
        );
        return false;
    }

    void exit() {
        System.out.println(
            "    ========================================================\n"
            + "    Goodbye\n"
            + "    ========================================================"
        );
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello my name\n" + logo + "\nHow may I help?");

        while (scanner.hasNext()) {
            String input = scanner.next();
            boolean exit = duke.echoInput(input);
            if (exit) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}
