import java.util.Scanner;

public class Duke {
    static String wrapMessage(String message) {
        String line = "----------------------------------------------";
        return line + "\n" + message + "\n" + line;
    }

    static void greet() {
        String greeting = " Hello! I'm Duke\n"
                        + " What can I do for you?";
        System.out.println(wrapMessage(greeting));
    }

    static void readInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String byeMessage = " Bye. Hope to see you again soon!";
                System.out.println(wrapMessage(byeMessage));
                break;
            } else {
                System.out.println(wrapMessage(" " + input));
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        greet();
        readInput();
    }
}

