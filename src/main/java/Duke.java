import java.util.Scanner;

public class Duke {

    private final String lineSeparator = "***********************";

    public void greet() {
        printMessage("Hi! I'm Duke. What can I do for you?");
    }

    public void printMessage(String msg) {
        System.out.println(lineSeparator);
        System.out.println(msg);
        System.out.println(lineSeparator);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                duke.printMessage("Bye. Hope to see you soon!");
                break;
            } else {
                duke.printMessage(userInput);
            }
        }
        scanner.close();
    }
}
