import java.util.Scanner;

public class Duke {

    public static void readUserInput() {
        Scanner sc = new Scanner(System.in);
        String display = sc.next();
        if (display.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(display);
            readUserInput();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke \n" + "What can I do for you?";
        System.out.println("Hello from\n" + logo + "\n" + greeting);
        readUserInput();
    }
}
