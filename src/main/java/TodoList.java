import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    private static ArrayList<String> todoList = new ArrayList<>();
    private static boolean terminate = false;

    public static void initialise() {
        while(!TodoList.terminate) {
            Scanner sc = new Scanner(System.in);  // Create a Scanner object
            String command = sc.nextLine();  // Read user input
            TodoList.handleCommand(command); // Output user input
        }
    }

    private static void handleCommand(String command) {
        if (command.equals("list")) {
            for (int i = 0; i < todoList.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + todoList.get(i));
            }
        } else if (command.equals("bye")) {
            TodoList.terminate = true;
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            TodoList.addTask(command);
        }
    }

    private static void addTask(String task) {
        todoList.add(task);
        System.out.println("added: " + task);
    }


}
