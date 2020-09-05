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
     *
     * @param tasks   The tasklist object that contains the list of tasks.
     * @param ui      The ui object.
     * @param storage The storage object
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();

        String message = "";

        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            message = message + "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + "." + task.toString());
                message = message + (i + 1) + "." + task.toString() + "\n";
            }
        } else if (command.contains("done")) {
            Task task = taskList.get(order - 1);
            task.markAsDone();
            System.out.println("____________________________________________________________\n" +
                    "  Nice! I've marked this task as done:");
            System.out.println("    [" + task.getStatusIcon() + "] " + task.getDescription());
            message = message + "  Nice! I've marked this task as done:" + "    [" + task.getStatusIcon() + "] "
                    + task.getDescription();
        } else if (command.contains("delete")) {
            Task task = taskList.get(order - 1);
            taskList.remove(order - 1);
            System.out.println("____________________________________________________________\n" +
                    "Noted. I've removed this task:");
            System.out.println("  " + task.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            message = message + "Noted. I've removed this task:\n" + "  " + task.toString() + "\nNow you have "
                    + taskList.size() + " tasks in the list.\n";
        } else if (command.equals("bye")) {
            isExit = true;
            String message1 = "  Bye. Hope to see you again soon!";
            System.out.println(message1);
            message = message + "  Bye. Hope to see you again soon!";
        } else {
            if (type.equals("deadline")) {
                if (hasDate) {
                    taskList.add(new DeadlineTask(command, time, date));
                } else {
                    taskList.add(new DeadlineTask(command, time));
                }
            } else if (type.equals("event")) {
                if (hasDate) {
                    taskList.add(new EventTask(command, time, date));
                } else {
                    taskList.add(new EventTask(command, time));
                }
            } else if (type.equals("find")) {
                System.out.println("Here are the matching tasks in your list:");
                message = message + "Here are the matching tasks in your list:\n";
                int j = 1;
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    if (task.toString().contains(command)) {
                        System.out.println(j + "." + task.toString());
                        message = message + j + "." + task.toString() + "\n";
                        j++;
                    }
                }
                return message;
            } else {
                taskList.add(new ToDoTask(command));
            }

            String message1 = "Got it. I've added this task:\n  "
                    + taskList.get(taskList.size() - 1).toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
            message = message + "Got it. I've added this task:\n  "
                    + taskList.get(taskList.size() - 1).toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
            System.out.println(message1);
        }

        try {
            storage.writeToFile(taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return message;
    }

    public boolean isExit() {
        return isExit;
    }
}
