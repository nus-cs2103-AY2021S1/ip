import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Command {
    public String command;
    public int order;
    public LocalDate date;
    public boolean hasDate;
    public String time;
    public String type;
    public boolean isExit;

    public Command(String command) {
        this.command = command;
    }

    public Command(String command, int order) {
        this.command = command;
        this.order = order;
    }

    public Command(String type, String command) {
        this.type = type;
        this.command = command;
    }

    public Command(String type, String command, String time, LocalDate date, boolean hasDate) {
        this.type = type;
        this.command = command;
        this.time = time;
        this.date = date;
        this.hasDate = hasDate;
    }

    /**
     * Execute the command and store the task into the list.
     * @param tasks The tasklist object that contains the list of tasks.
     * @param ui The ui object.
     * @param storage The storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> lists = tasks.getTasks();

        if (type.equals("find")) {
            System.out.println("Here are the matching tasks in your list:");
            int j = 1;
            for (int i = 0; i < lists.size(); i++) {
                Task task = lists.get(i);
                if (task.toString().contains(command)) {
                    System.out.println(j + "." + task.toString());
                    j++;
                }
            }
            return;
        }

        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < lists.size(); i++) {
                Task task = lists.get(i);
                System.out.println((i + 1) + "." + task.toString());
            }
        } else if (command.contains("done")) {
            Task task = lists.get(order - 1);
            task.markAsDone();
            System.out.println("____________________________________________________________\n" +
                    "  Nice! I've marked this task as done:");
            System.out.println("    [" + task.getStatusIcon() + "] " + task.getDescription());
        } else if (command.contains("delete")) {
            Task task = lists.get(order - 1);
            lists.remove(order - 1);
            System.out.println("____________________________________________________________\n" +
                    "Noted. I've removed this task:");
            System.out.println("  " + task.toString());
            System.out.println("Now you have " + lists.size() + " tasks in the list.\n");
        } else if (command.equals("bye")) {
            isExit = true;
            String message1 = "  Bye. Hope to see you again soon!";
            System.out.println(message1);
        } else {
            if (type.equals("deadline")) {
                if (hasDate) {
                    lists.add(new Deadline(command, time, date));
                } else {
                    lists.add(new Deadline(command, time));
                }
            } else if (type.equals("event")) {
                if (hasDate) {
                    lists.add(new Event(command, time, date));
                } else {
                    lists.add(new Event(command, time));
                }
            } else {
                lists.add(new ToDo(command));
            }

            String message1 = "Got it. I've added this task:\n  "
                    + lists.get(lists.size() - 1).toString() + "\n" +
                    "Now you have " + lists.size() + " tasks in the list.";
            System.out.println(message1);
        }

        try {
            storage.writeToFile(lists);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public boolean isExit() {
        return isExit;
    }
}
