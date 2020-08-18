import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Rawr! I'm Dino ><\n" + "What can I do for you?"
                + "\nInput a task to be added to your task list!"
                + "\nTo see your list of tasks, input 'list'."
                + "\n____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;
        List<Task> tasks = new ArrayList<>();

        while (!isBye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Dino says bye
                System.out.println("Rawr. Hope to see you again soon! ><"
                        + "\n____________________________________________________________");
                isBye = true;
            } else if (input.equals("list")) {
                // Dino lists out all items in list
                System.out.println("Dino lists your tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + tasks.get(i));
                }
                System.out.println("To mark off a task after completion, just input 'done <task number>'.");
                System.out.println("____________________________________________________________");
            } else if (input.split(" ")[0].equals("done") && input.split(" ").length == 2
                    && input.split(" ")[1].matches("[0-9]+")) {
                // condition checks that user input is in the format "done X" where X is a numeric

                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber > tasks.size() || taskNumber < 1) {
                    // task number is not valid
                    System.out.println("Task " + taskNumber + " is not in your list of tasks!"
                            + "\n____________________________________________________________");
                } else {
                    // Dino marks task as done
                    int taskIndex = taskNumber - 1;
                    Task doneTask = tasks.get(taskIndex);
                    doneTask.markAsDone();
                    System.out.println("Great! Dino has marked " + "Task " + taskNumber
                            + " as done: \n" + doneTask
                            + "\n____________________________________________________________");
                }
            } else {
                // Dino adds task to list
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("Dino has added to your list of tasks: "
                        + input
                        + "\n____________________________________________________________");
            }
        }
    }
}