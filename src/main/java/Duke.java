import java.util.Scanner;

public class Duke {

    private static final String logo = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greeting = "Hello! I'm Echo\nLet's have a conversation!";
    private static final String endingGreeting = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        Duke duke = new Duke();
        duke.run();
        System.out.println(endingGreeting);
    }

    private void run() {
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.print("> ");
        while(!(input = scanner.nextLine()).equals("bye")) {
            echo(input);
        }
    }

    private String echo(String input) {
        System.out.print("Echo: " + input + "\n> ");
        return input;
    }
}
