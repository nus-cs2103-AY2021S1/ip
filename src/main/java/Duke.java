import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?");

        List<Task> savedItems = new ArrayList<>();

        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            userInput = scanner.nextLine();

            switch(userInput) {
                case "bye":
                    // Fallthrough
                case "exit":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "ls":
                    System.out.println("Here are your saved items:");
                    for (int i = 0; i < savedItems.size(); ++i) {
                        Task task = savedItems.get(i);
                        System.out.println(i + 1 + ". " + task.getDescription());
                    }
                    break;
                default:
                    savedItems.add(new Task(userInput));
                    System.out.println("added: " + userInput);
            }
        }
    }
}
