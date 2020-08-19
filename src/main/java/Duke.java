import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    List<String> tasks = new ArrayList<>();

    private void activate() {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n\nMe: ";
        System.out.print(greeting);

        String input = "";

        while (!input.equals("bye") && sc.hasNextLine()) {
            input = sc.nextLine();
            reply(input);
        }
    }

    private void reply(String input) {
        if (input.equals("bye")) {
            quit();
        } else if (input.equals("list")) {
            showTasks();
        } else {
            add(input);
        }
    }

    private void quit() {
        System.out.println("Duke: Adios!\n");
    }

    private void showTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            String message = String.valueOf(i + 1) + ". " + tasks.get(i);
            System.out.println(message);
        }
        System.out.print("\nMe: ");
    }

    private void add(String input) {
        tasks.add(input);
        System.out.println("Duke: Added '" + input + "' to list of tasks");
        System.out.print("\nMe: ");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.activate();
    }
}
