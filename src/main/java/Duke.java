import java.util.Scanner;

public class Duke {

    public static String lineDivider = "------------------------------------------";

    public static void echo(String message) {
        System.out.println(lineDivider);
        System.out.println(message);
        System.out.println(lineDivider + "\n");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        echo("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNext()) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                echo("Bye. Hope to see you again soon!");
                break;
            } else {
                echo(msg);
            }
        }
    }
}
