import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Command {
    protected String command;
    protected int order;
    protected LocalDate date;
    protected boolean hasDate;
    protected String time;
    protected String type;
    protected boolean isExit;
    protected int priority;

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

    public Command(String command, int order, int priority) {
        this.command = command;
        this.order = order;
        this.priority = priority;
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
            message = message + "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                message = message + (i + 1) + "." + task.toString() + "\n";
            }
        } else if (command.contains("done")) {
            assert order >= 1 : "order should be at least one";
            Task task = taskList.get(order - 1);
            task.markAsDone();
            message = message + "  Nice! I've marked this task as done:" + "    ["
                    + task.getStatusIcon() + "] "
                    + task.getDescription();
        } else if (command.contains("delete")) {
            Task task = taskList.get(order - 1);
            taskList.remove(order - 1);
            message = message + "Noted. I've removed this task:\n" + "  "
                    + task.toString() + "\nNow you have "
                    + taskList.size() + " tasks in the list.\n";
        } else if (command.equals("bye")) {
            isExit = true;
            message = message + "  Bye. Hope to see you again soon!";
        } else if (command.contains("priority")) {
            Task task = taskList.get(order - 1);
            task.setPriority(priority);
            message = message + "set the priority of task " + order + " to be " + priority;
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
                message = message + "Here are the matching tasks in your list:\n";
                int j = 1;
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    if (task.toString().contains(command)) {
                        message = message + j + "." + task.toString() + "\n";
                        j++;
                    }
                }
                return message;
            } else {
                taskList.add(new ToDoTask(command));
            }

            message = message + "Got it. I've added this task:\n  "
                    + taskList.get(taskList.size() - 1).toString() + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.";
        }

//        try {
//            storage.writeToFile(taskList);
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }

        return message;
    }

    public boolean isExit() {
        return isExit;
    }
}
