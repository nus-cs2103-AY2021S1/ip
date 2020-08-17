import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    private static ArrayList<Task> todoList = new ArrayList<>();
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
                System.out.println(index + ". " + todoList.get(i).returnTask());
            }
        } else if (command.equals("bye")) {
            TodoList.terminate = true;
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.startsWith("done ")) {
            String splicedCommand = command.substring(5);
            try {
                int index = Integer.parseInt(splicedCommand);
                Task targetTask = todoList.get(index - 1);
                String message = targetTask.completeTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(message);
            } catch (NumberFormatException error) {
                System.out.println("Are you sure this is the right command?");
            }
        } else {
            TodoList.addTask(command);
        }
    }

    private static void addTask(String task) {
        Task newTask = new Task(task);
        todoList.add(newTask);
        System.out.println("added: " + task);
    }


}
