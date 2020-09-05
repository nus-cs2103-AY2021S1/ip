package task;

import java.util.List;

import exception.DukeException;
import systemexit.SystemExit;
import task.tasks.Task;

/**
 * Contains list of tasks and provide operations to manipulate this list of tasks.
 */
public class TaskList {
    /**
     * Task list.
     */
    private List<Task> tasks;

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
            } else if (userCommand.split(" ").length != 1) {
                // Add and report that the todo is added
                TaskList tasklist = this;
                return TaskHelper.handleTodo(userCommand, tasklist);
            } else {
                return SystemExit.terminateDuke("todo error");
            }
        } else if (userCommand.contains("deadline")) { // Deadline
            try {
                TaskList tasklist = this;
                return TaskHelper.handleDeadline(userCommand, tasklist);
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g deadline return book /bylmklmlmlkmlkmlmlmlmlmkl Sunday
                return DukeException.invalidDeadline();
            }
        } else if (userCommand.contains("event")) { // Event
            try {
                TaskList tasklist = this;
                return TaskHelper.handleEvent(userCommand, tasklist);
            } catch (ArrayIndexOutOfBoundsException e) {
                // E.g event project meeting /atlmklmlmlkmlkmlmlmlmlmkl Mon 2-4pm
                return DukeException.invalidEvent();
            }
        } else {
            return SystemExit.terminateDuke("add error");
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
            } else if (userCommandSplit.length == 2) {
                TaskList tasklist = this;
                return TaskHelper.handleCompletedTask(userCommandSplit, tasklist);
            } else {
                return SystemExit.terminateDuke("done error");
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
                TaskList tasklist = this;
                return TaskHelper.handleTaskDeletion(userCommandSplit, tasklist);
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
        } else if (userCommandSplit.length == 2) {
            TaskList tasklist = this;
            return TaskHelper.handleTaskFinding(userCommandSplit, tasklist);
        } else {
            return SystemExit.terminateDuke("find error");
        }
    }
}
