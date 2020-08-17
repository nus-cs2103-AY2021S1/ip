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
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand[0].equals("list")) {
            for (int i = 0; i < todoList.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + todoList.get(i).toString());
            }
        } else if (splitCommand[0].equals("bye")) {
            TodoList.terminate = true;
            System.out.println("Bye. Hope to see you again soon!");
        } else if (!splitCommand[0].equals("done") && !splitCommand[0].equals("todo") &&
                !splitCommand[0].equals("deadline") && !splitCommand[0].equals("event")) {
            System.out.println(splitCommand[0]);
            TodoList.addTask(command);
        } else {
            try {
                if (splitCommand[0].equals("done")) {
                    int index = Integer.parseInt(splitCommand[1]);
                    Task targetTask = todoList.get(index - 1);
                    String message = targetTask.completeTask();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(message);
                } else if (splitCommand[0].equals("deadline")) {

                } else if (splitCommand[0].equals("todo")) {

                } else if (splitCommand[0].equals("event")) {

                }
            } catch (NumberFormatException error) {
                // When "done" is not followed by a valid number
                System.out.println("Please enter a valid index!");
            } catch (ArrayIndexOutOfBoundsException error) {
                // When "done" is not followed by any number
                System.out.println("Please let me know which task you are referring to!");
            }
        }
    }

    private static void addTask(String task) {
        Task newTask = new Task(task);
        todoList.add(newTask);
        System.out.println("added: " + task);
    }
}
