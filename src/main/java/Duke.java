import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "    ________________________________________________________\n";
    private static final String EXIT_COMMAND = "bye";

    private static void greet() {
        String welcomeMessage = "    Konnichiwa!\n"
                + "    What can I do for you?\n";
        System.out.println(DIVIDER + welcomeMessage + DIVIDER);
    }

    private static void exit() {
        String exitMessage = "    Ja ne!\n";
        System.out.println(DIVIDER + exitMessage + DIVIDER);
    }

    private static void echo(String message) {
        System.out.println(DIVIDER + "    " + message + "\n" + DIVIDER);
    }

    private static void chat() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();
        while (!input.equals(EXIT_COMMAND)) {
            echo(input);
            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        chat();
        exit();
    }
}
