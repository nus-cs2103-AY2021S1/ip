import java.util.List;

public class TaskManager {
    protected List<Task> tasks;

    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void showAllTask() {
        System.out.println("\t____________________________________________________________\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            int serialNumber = i + 1;
            Task task = this.tasks.get(i);
            System.out.println("\t" + serialNumber + "." + task);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public void addedTaskDescription(Task newTask) {
        System.out.println("\t____________________________________________________________\n");
        System.out.println("\tGot it. I've added this task:\n");
        System.out.println("\t\t" + newTask + "\n");
        System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list.\n");
        System.out.println("\t____________________________________________________________\n");
    }

    public void addTask(String userCommand) {
        if (userCommand.contains("todo")) { // To Do
            // E.g todowork
            if (userCommand.split(" ").length == 1) {
                DukeException.invalidTodo();
            } else {
                // Add and report that the todo is added
                Task newTask = new Todo(userCommand);
                this.tasks.add(newTask);
                this.addedTaskDescription(newTask);
                FileManipulator.appendToFile(newTask.toString());
            }
        } else if (userCommand.contains("deadline")) { // Deadline
            try {
                String[] userCommandSplit = userCommand.split(" /by ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String by = userCommandSplit[1];

                // Add and report that the deadline is added
                Task newTask = new Deadline(description, by);
                this.tasks.add(newTask);
                this.addedTaskDescription(newTask);
                FileManipulator.appendToFile(newTask.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g deadline return book /bylmklmlmlkmlkmlmlmlmlmkl Sunday
                DukeException.invalidDeadline();
            }
        } else { // Event
            try {
                String[] userCommandSplit = userCommand.split(" /at ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String at = userCommandSplit[1];

                // Add and report that the event is added
                Task newTask = new Event(description, at);
                this.tasks.add(newTask);
                this.addedTaskDescription(newTask);
                FileManipulator.appendToFile(newTask.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g event project meeting /atlmklmlmlkmlkmlmlmlmlmkl Mon 2-4pm
                DukeException.invalidEvent();
            }
        }
    }

    public void doneTaskDescription(Task doneTask) {
        System.out.println("\t____________________________________________________________\n");
        System.out.println("\tNice! I've marked this task as done:\n");
        System.out.println("\t" + doneTask);
        System.out.println("\t____________________________________________________________\n");
    }

    public void markTaskDone(String userCommand) {
        try {
            // E.g given "done 1", we split to ["done", "1"]
            String[] userCommandSplit = userCommand.split(" ");
            // To prevent cases such as "done 1 7", "done", "done123123123"
            if (userCommandSplit.length != 2) {
                DukeException.invalidCommand();
            } else {
                // Take serial number e.g 1 "done 1"
                int serialNumber = Integer.parseInt(userCommandSplit[1]);
                int index = serialNumber - 1;

                // Mark as done and report that the task is done
                Task doneTask = this.tasks.get(index);
                String currentText = doneTask.toString();
                doneTask.markAsDone();
                String amendedText = doneTask.toString();
                this.doneTaskDescription(doneTask);
                FileManipulator.amendFile(currentText, amendedText);
            }
        } catch (IndexOutOfBoundsException e) {
            // E.g "done 719329813298712398123" is not valid as number of tasks is cap to 100 by requirements
            DukeException.noSuchTask();
        } catch (NumberFormatException e) {
            // E.g "done work"
            DukeException.invalidCommand();
        }
    }

    public void deletedTaskDescription(Task deletedTask) {
        System.out.println("\t____________________________________________________________\n");
        System.out.println("\tNoted. I've removed this task:\n");
        System.out.println("\t\t" + deletedTask + "\n");
        System.out.println("\tNow you have " + this.tasks.size() + " tasks in the list.\n");
        System.out.println("\t____________________________________________________________\n");
    }

    public void deleteTask(String userCommand) {
        try {
            // E.g given "delete 1", we split to ["delete", "1"]
            String[] userCommandSplit = userCommand.split(" ");
            // To prevent cases such as "delete 1 7", "delete", "delete123123123"
            if (userCommandSplit.length != 2) {
                DukeException.invalidCommand();
            } else {
                // Take serial number e.g 1 "delete 1" and delete
                int serialNumber = Integer.parseInt(userCommandSplit[1]);
                int index = serialNumber - 1;

                // Mark as deleted and report that the task is deleted
                Task deletedTask = this.tasks.get(index);
                this.tasks.remove(index);
                this.deletedTaskDescription(deletedTask);
                FileManipulator.deleteFromFile(deletedTask.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            // E.g "delete 719329813298712398123" is not valid as number of tasks is cap to 100 by requirements
            DukeException.noSuchTask();
        }
    }
}
