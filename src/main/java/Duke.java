import java.util.Scanner;

public class Duke {
    private static final String line = "----------------------";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                echo(input);
            }
        }
        exit();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke" + "\n" +
                "What can I do for you?");
    }

    private static void echo(String input) {
        System.out.println(line + "\n\t" + input + "\n" + line);
    }

    private static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

}
