import java.util.Scanner;

public class Duke {
    public final Scanner scanner;

    public Duke() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        duke.sendMessage(duke.getGreetMessage());
        while (true) {
            String input = duke.getInput();
            if (input.equalsIgnoreCase("bye")) {
                duke.sendMessage(duke.getByeMessage());
                break;
            } else {
                duke.sendMessage(input);
            }
        }
    }

    public String getGreetMessage() {
        return "Hello. What can I do for you?";
    }

    public String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public void sendMessage(String message) {
        System.out.println(String.format("----------\n%s\n----------\n", message));
        return;
    }

    public String getInput() {
        String input = this.scanner.nextLine();
        return input;
    }
}
