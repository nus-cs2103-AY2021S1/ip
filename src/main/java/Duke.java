import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private final String lineSeparator = "***********************";
    private List<String> taskList = new ArrayList<>();

    public void greet() {
        printMessage("Hi! I'm Duke. What can I do for you?");
    }

    public void printMessage(String msg) {
        System.out.println(lineSeparator);
        System.out.println(msg);
        System.out.println(lineSeparator);
    }

    public void addTask(String task) {
        taskList.add(task);
        printMessage(String.format("added: %s", task));
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
                duke.addTask(userInput);
            }
        }
        scanner.close();
    }
}
