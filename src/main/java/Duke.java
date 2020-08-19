import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! I'm Duke your friendly neighbourhood chat bot");
        System.out.println("What can i do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (!input.equals("bye")) {
                echo(input);
            } else {
                System.out.println("        Bye have a good day!");
                break;
            }

        }
    }

    public static void echo(String input) {
        System.out.println("        " + input);
    }
}
