import task.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    private boolean hasCharacter(String string) {
        return Pattern.compile("\\w").matcher(string).find();
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
                try {
                    int taskIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    Task task = this.taskList.get(taskIndex);
                    task.setCompleted(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + task.toString());
                } catch(NumberFormatException e) {
                    System.out.println("Format: done {index}");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index Error: Invalid task index");
                }
                continue;
            }

            if (input.equals("delete")) { // Delete task
                try {
                    int taskIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    Task task = this.taskList.remove(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("    " + task.toString());
                } catch(NumberFormatException e) {
                    System.out.println("Format: delete {index}");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index Error: Invalid task index");
                }
                continue;
            }

            Task task = null;

            if (input.equals("todo")) {
                String description = scanner.nextLine();
                if (hasCharacter(description)) {
                    task = new ToDo(description);
                } else {
                    System.out.println("Format: todo {description}");
                    continue;
                }
            }

            if (input.equals("deadline")) {
                Pattern p = Pattern.compile("^(.+)\\s*\\/by\\s*(.+)$");
                Matcher m = p.matcher(scanner.nextLine());
                if (m.matches() && hasCharacter(m.group(1)) && hasCharacter(m.group(2))) {
                    task = new Deadline(m.group(1), m.group(2));
                } else {
                    System.out.println("Format: deadline {description} /by {deadline}");
                    continue;
                }
            }

            if (input.equals("event")) {
                Pattern p = Pattern.compile("^(.+)\\s*\\/at\\s*(.+)$");
                Matcher m = p.matcher(scanner.nextLine());
                if (m.matches() && hasCharacter(m.group(1)) && hasCharacter(m.group(2))) {
                    task = new Event(m.group(1), m.group(2));
                } else {
                    System.out.println("Format: Event {description} /at {time}");
                    continue;
                }
            }

            if (task != null) {
                this.taskList.add(task);
                System.out.println("Added: " + task.toString());
                continue;
            }

            System.out.println("Error: Unknown input");

        }
    }
}
