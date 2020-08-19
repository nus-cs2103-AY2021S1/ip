import task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {

    private final List<Task> taskList;

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
            input = scanner.next();

            if (input.equals("bye")) { // Exit
                break;
            }

            if (input.equals("list")) { // List all tasks
                list();
                continue;
            }

            if (input.equals("done")) { // Mark task as completed
                // TODO: Catch IndexOutOfBoundsException
                int taskIndex = scanner.nextInt() - 1;
                markDone(this.taskList.get(taskIndex));
                continue;
            }

            Task task = null;

            if (input.equals("todo")) {
                task = new ToDo(scanner.nextLine());
            }

            if (input.equals("deadline")) {
                Pattern p = Pattern.compile("^(.+)\\s*\\/by\\s*(.+)$");
                Matcher m = p.matcher(scanner.nextLine());
                m.matches();
                task = new Deadline(m.group(1), m.group(2));
            }

            if (input.equals("event")) {
                Pattern p = Pattern.compile("^(.+)\\s*\\/at\\s*(.+)$");
                Matcher m = p.matcher(scanner.nextLine());
                m.matches();
                task = new Event(m.group(1), m.group(2));
            }

            if (task != null) {
                this.taskList.add(task);
                System.out.println("Added: " + task.toString());
                continue;
            }

            System.out.println("Error: Invalid input!");

        }
    }
}
