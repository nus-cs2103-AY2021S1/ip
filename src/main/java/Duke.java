import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final List<Task> savedItems = new ArrayList<>();

    private static void lsHandler() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < savedItems.size(); ++i) {
            Task task = savedItems.get(i);
            System.out.println(i + 1 + ". [" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }

    private static void doneHandler(String userInput) {
        // TODO: input sanitization
        int taskNo = Integer.parseInt(userInput.split(" ")[1]);
        Task task = savedItems.get(taskNo - 1);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskNo + ". [" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            userInput = scanner.nextLine();
            String command = userInput.split(" ")[0];

            switch(command) {
                case "bye":
                    // Fallthrough
                case "exit":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "done":
                    doneHandler(userInput);
                    break;
                case "ls":
                    lsHandler();
                    break;
                default:
                    savedItems.add(new Task(userInput));
                    System.out.println("added: " + userInput);
            }
        }
    }
}
