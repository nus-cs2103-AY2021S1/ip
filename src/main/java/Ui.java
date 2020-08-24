import java.util.Scanner;

public class Ui {
    public static final String LINE = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke, your personal assistant!\n"
        + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public static void print(String txt) {
        System.out.println(LINE + "\n" + txt + "\n" + LINE);
    }

    public static void printGreeting() {
        print(WELCOME_MESSAGE);
    }

    public static void printGoodbye() {
        print(GOODBYE_MESSAGE);
    }
}
