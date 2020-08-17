import java.util.Scanner;

public class Duke {
    static String line = "    ____________________________________________________________\n";

    private static String format(String string) {
        return line + string + "\n" + line;
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (input.equals("bye")) {
            System.out.println(format("     Bye. Hope to see you again soon!"));
        } else {
            System.out.println(format(input));
            echo();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(format("     Hello! I'm Duke\n" +
                "     What can I do for you?"));
        echo();
    }
}
