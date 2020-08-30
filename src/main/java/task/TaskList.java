package task;

import java.util.List;

import exception.DukeException;
import storage.Storage;
import task.tasks.Deadline;
import task.tasks.Event;
import task.tasks.Task;
import task.tasks.Todo;

/**
 * Contains list of tasks and provide operations to manipulate this list of tasks.
 */
public class TaskList {
    /**
     * Task list.
     */
    protected List<Task> tasks;

    /**
     * Creates TaskList object containing a list of tasks.
     *
     * @param tasks Task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves task list from TaskList object.
     *
     * @return Task list.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to task list.
     *
     * @param userCommand User input.
     * @throws ArrayIndexOutOfBoundsException If /by or /at user input not written properly.
     *                                        E.g deadline return book /bylmklmlmlkmlkmlmlmlmlmkl Sunday.
     */
    public String addTask(String userCommand) {
        if (userCommand.contains("todo")) { // To Do
            // E.g todowork
            if (userCommand.split(" ").length == 1) {
                return DukeException.invalidTodo();
            } else {
                // Add and report that the todo is added
                String[] userCommandSplit = userCommand.split(" ", 2);
                String description = userCommandSplit[1];
                Task newTask = new Todo(description);
                this.tasks.add(newTask);
                Storage.appendToFile(newTask.toString());
                return TaskDescription.addedTaskDescription(this.tasks, newTask);
            }
        } else if (userCommand.contains("deadline")) { // Deadline
            try {
                String[] userCommandSplit = userCommand.split(" /by ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String by = userCommandSplit[1];

                // Add and report that the deadline is added
                Task newTask = new Deadline(description, by);
                this.tasks.add(newTask);
                Storage.appendToFile(newTask.toString());
                return TaskDescription.addedTaskDescription(this.tasks, newTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g deadline return book /bylmklmlmlkmlkmlmlmlmlmkl Sunday
                return DukeException.invalidDeadline();
            }
        } else { // Event
            try {
                String[] userCommandSplit = userCommand.split(" /at ");
                String description = userCommandSplit[0].split(" ", 2)[1];
                String at = userCommandSplit[1];

                // Add and report that the event is added
                Task newTask = new Event(description, at);
                this.tasks.add(newTask);
                Storage.appendToFile(newTask.toString());
                return TaskDescription.addedTaskDescription(this.tasks, newTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g event project meeting /atlmklmlmlkmlkmlmlmlmlmkl Mon 2-4pm
                return DukeException.invalidEvent();
            }
        }
    }

    /**
     * Marks a task in task list as completed.
     *
     * @param userCommand User input.
     * @throws IndexOutOfBoundsException If given a non-existent task S/N number.
     *                                   E.g "done 719329813298712398123" is not valid as number of tasks is cap to 100
     *                                   by requirements. E.g "done 7" is not valid if there are only 6 tasks in the
     *                                   task list.
     * @throws NumberFormatException     If user input is invalid. E.g "done work".
     */
    public String markTaskDone(String userCommand) {
        try {
            // E.g given "done 1", we split to ["done", "1"]
            String[] userCommandSplit = userCommand.split(" ");

            // To prevent cases such as "done 1 7", "done", "done123123123"
            if (userCommandSplit.length != 2) {
                return DukeException.invalidCommand();
            } else {
                // Take serial number e.g 1 "done 1"
                int serialNumber = Integer.parseInt(userCommandSplit[1]);
                int index = serialNumber - 1;

                // Mark as done and report that the task is done
                Task doneTask = this.tasks.get(index);
                String currentText = doneTask.toString();
                doneTask.markAsDone();
                String amendedText = doneTask.toString();
                Storage.amendFile(currentText, amendedText);
                return TaskDescription.doneTaskDescription(doneTask);
            }
        } catch (IndexOutOfBoundsException e) {
            // E.g "done 719329813298712398123" is not valid as number of tasks is cap to 100 by requirements
            return DukeException.noSuchTask();
        } catch (NumberFormatException e) {
            // E.g "done work"
            return DukeException.invalidCommand();
        }
    }

    /**
     * Deletes a task in task list.
     *
     * @param userCommand User input.
     * @throws IndexOutOfBoundsException If given a non-existent task S/N number.
     *                                   E.g "delete 719329813298712398123" is not valid as number of tasks is
     *                                   cap to 100 by requirements.
     *                                   E.g "delete 7" is not valid if there are only 6 tasks in the task list.
     */
    public String deleteTask(String userCommand) {
        try {
            // E.g given "delete 1", we split to ["delete", "1"]
            String[] userCommandSplit = userCommand.split(" ");

            // To prevent cases such as "delete 1 7", "delete", "delete123123123"
            if (userCommandSplit.length != 2) {
                return DukeException.invalidCommand();
            } else {
                // Take serial number e.g 1 "delete 1" and delete
                int serialNumber = Integer.parseInt(userCommandSplit[1]);
                int index = serialNumber - 1;

                // Mark as deleted and report that the task is deleted
                Task deletedTask = this.tasks.get(index);
                this.tasks.remove(index);
                Storage.deleteFromFile(deletedTask.toString());
                return TaskDescription.deletedTaskDescription(this.tasks, deletedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            // E.g "delete 719329813298712398123" is not valid as number of tasks is cap to 100 by requirements
            return DukeException.noSuchTask();
        }
    }

    /**
     * Finds a task in task list.
     *
     * @param userCommand User input.
     */
    public String findTask(String userCommand) {
        // E.g given "find book", we split to ["find", "book"]
        String[] userCommandSplit = userCommand.split(" ", 2);
        // To prevent cases such as "findasd"
        if (userCommandSplit.length != 2) {
            return DukeException.invalidCommand();
        } else {
            String keyword = userCommandSplit[1];
            // Make a copy of the existing tasks and remove a task if keyword is not found
            List<Task> tasksCopy = this.tasks;
            tasksCopy.removeIf(task -> !task.getDescription().contains(keyword));
            return TaskDescription.searchedTaskDescription(tasksCopy);
        }
    }
}
