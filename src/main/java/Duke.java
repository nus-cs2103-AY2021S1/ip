import java.util.Scanner;

public class Duke {

    private static final String dash = String.valueOf('\u2500').repeat(5);
    private static final String greeting = "Hello! I'm Duke \n" +
            "What can I do for you?";
    private static final String farewell = "Bye. Hope to see you again soon.";
    private boolean running = true;

    public Duke() {
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Duke servingDuke = new Duke();
        servingDuke.respondToInput(greeting);
        while (servingDuke.canRun()) {
            String input = sc.nextLine();
            servingDuke.respondToInput(input);
        }
    }

    private void respondToInput(String input) {
        if (input.equals("bye")) {
            running = false;
            System.out.println(constructMessage(farewell));
        } else if (!input.isEmpty()){
            System.out.println(constructMessage(input));
        }
    }

    private boolean canRun() {
        return running;
    }

    public String constructMessage(String message) {
        return dash + "\n" + message + "\n" + dash;
    }
}
