import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                return ;
            } else {
                printMessage(command);
            }

        }
    }

    public static void printMessage(String message) {
        System.out.println("    ___________________________\n" +
                "     " + message + "\n    ___________________________");
    }
}
