import java.util.Scanner;
public class Duke {
    final static String LINE = "____________________________________________________________\n";
    final static String BYE = "aight imma head out\n";
    final static String WELCOME = "Hello! I'm Duke\nWhat can I do for you?\n";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + WELCOME + "\n" + LINE);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            System.out.println(LINE + input + "\n" + LINE);
            input = scanner.nextLine();
        }
        System.out.println(LINE + BYE + LINE);
    }
}
