import java.util.Scanner;

public class UI {

    final static String BYE = "aight imma head out\n";
    final static String WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n";
    final static String LINE = "____________________________________________________________\n";
    Scanner scanner = new Scanner(System.in);

    public static void print(String str) {
        System.out.print(LINE + str + LINE);
    }

    String getInput() {
        return scanner.nextLine();
    }

    void welcome(TaskList tasks) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        print(WELCOME + tasks.loadMessage);
    }

    void bye() {
        print(BYE);
    }
}
