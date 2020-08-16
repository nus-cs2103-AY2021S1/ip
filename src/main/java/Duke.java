import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = "start";

        while (!input.equals("bye")) {
            String[] line = scanner.nextLine().split(" ");
            input = line[0];
            switch (input) {
                case "list":
                    int counter = 1;
                    for (Task item: tasks) {
                        System.out.println(counter + ". " + item.getStatusIcon() + " " + item.description);
                        counter++;
                    }
                    break;

                case "done":
                    int task_index = Integer.parseInt(line[1]);
                    // Validate task index
                    if (task_index <= tasks.size()) {
                        Task task = tasks.get(task_index - 1);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[" + task.getStatusIcon() + "]" + " " + task.description);
                    } else {
                        System.out.println("Task index should be <= " + tasks.size());
                    }
                    break;

                default:
                    if (!input.equals("bye")) {
                        String task = String.join(" ", line);
                        Task newTask = new Task(task);
                        tasks.add(newTask);
                        System.out.println("added: " + newTask.description);
                    }
                    break;
            }
        }
        System.out.println("Bye bye. See you again soon!");
    }
}