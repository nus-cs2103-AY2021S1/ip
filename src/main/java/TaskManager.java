import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    protected List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTaskDescription(Task newTask) {
        tasks.add(newTask);
        System.out.println("\t____________________________________________________________\n");
        System.out.println("\tGot it. I've added this task:\n");
        System.out.println("\t\t" + newTask + "\n");
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.\n");
        System.out.println("\t____________________________________________________________\n");
    }

    public void addTask(String userCommand) {
        if (userCommand.contains("todo")) { // To Do
            List<Task> tasks = this.getTasks();
            Task newTask = new Todo(userCommand);
            this.addTaskDescription(newTask);
        } else if (userCommand.contains("deadline")) { // Deadline
            try {
                String[] userCommandSplit = userCommand.split(" /by ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String by = userCommandSplit[1];
                List<Task> tasks = this.getTasks();
                Task newTask = new Deadline(description, by);
                this.addTaskDescription(newTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                DukeException.invalidDeadline();
            }
        } else { // Event
            try {
                String[] userCommandSplit = userCommand.split(" /at ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String at = userCommandSplit[1];
                List<Task> tasks = this.getTasks();
                Task newTask = new Event(description, at);
                this.addTaskDescription(newTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                DukeException.invalidEvent();
            }
        }
    }

    public void showAllTask() {
        System.out.println("\t____________________________________________________________\n");
        for (int i = 0; i < tasks.size(); i++) {
            int serialNumber = i + 1;
            Task task = tasks.get(i);
            System.out.println("\t" + serialNumber + "." + task);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public void markTaskDone(String userCommand) {
        try {
            // E.g given "done 1", we split to ["done", "1"]
            String[] userCommandSplit = userCommand.split(" ");
            // To prevent cases such as "done 1 7", "done", "done123123123"
            if (userCommandSplit.length != 2) {
                DukeException.invalidDoneCommand();
            } else {
                // Take serial number e.g 1 "done 1" and mark as done
                int serialNumber = Integer.parseInt(userCommandSplit[1]);
                List<Task> tasks = this.getTasks();
                Task doneTask = tasks.get(serialNumber - 1);
                doneTask.markAsDone();
                System.out.println("\t____________________________________________________________\n");
                System.out.println("\tNice! I've marked this task as done:\n");
                System.out.println("\t" + doneTask);
                System.out.println("\t____________________________________________________________\n");
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException.noSuchTask();
        }
    }
}
