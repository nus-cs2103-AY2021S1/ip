import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {

    private final List<Task> taskList;
    Pattern donePattern = Pattern.compile("^done (\\d+)\\s*$");

    public Chat() {
        this.taskList = new ArrayList<>(100);
    }

    private void list() {
        int i = 0;
        for (Task item : this.taskList) {
            System.out.println(++i + ". " + item.toString());
        }
    }

    private void markDone(Task task) {
        task.setCompleted(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task.toString());
    }

    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);

        while(true) {

            // Prompt for input
            System.out.print("> ");
            input = scanner.nextLine();

            if (input.equals("bye")) { // Exit
                break;
            }

            if (input.equals("list")) { // List all tasks
                list();
                continue;
            }

            Matcher matcher = donePattern.matcher(input);
            if (matcher.matches()) { // Mark task as completed
                int taskIndex = Integer.parseInt(matcher.group(1)) - 1;
                markDone(this.taskList.get(taskIndex)); // TODO: Catch IndexOutOfBoundsException
                continue;
            }

            // Add new task and echo
            Task task = new Task(input);
            this.taskList.add(task);
            System.out.println("Added: " + task.getDescription());

        }
    }
}
