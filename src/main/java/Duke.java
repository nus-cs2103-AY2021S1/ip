import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "    ____________________________________________________________";
    private static final String indentation = "     ";

    public static void greet() {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void echo(String command) {
        System.out.println(horizontalLine);
        System.out.println(indentation + command);
        System.out.println(horizontalLine);
    }

    public static void exit() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(indentation + bye);
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        greet();
        String command = scanner.nextLine();
        while(command != null) {
            if(command.equals("bye")) {
                exit();
                break;
            } else {
                echo(command);
            }
            command = scanner.nextLine();
        }

    }
}
