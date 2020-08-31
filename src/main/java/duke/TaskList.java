package duke;

import duke.exception.*;
import duke.task.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the list of tasks together with a counter
 * to keep track of pending tasks
 */
public class TaskList implements Cloneable {
    private List<Task> tasks;
    private int numOfPendingTasks;
    private static final Ui UI = new Ui();

    TaskList() {
        this.tasks = new ArrayList<>();
        this.numOfPendingTasks = 0;
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numOfPendingTasks = 0;
        for (Task task : tasks) {
            if (!task.isDone) {
                incrementPendingTasks();
            }
        }
    }

    /**
     * Extracts the list of tasks of a TaskList.
     *
     * @return a list of tasks contained in a TaskList object.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if a call to task index in the CLI is valid.
     *
     * @param target Integer number
     * @return true if target is usable as an array access
     */
    public boolean isWithinValidRange(int target) {
        return target >= 0 && target < tasks.size();
    }

    /**
     * Adds one count to pending task.
     */
    public void incrementPendingTasks() {
        this.numOfPendingTasks++;
    }

    /**
     * Subtracts one count to pending task.
     */
    public void decrementPendingTasks() {
        this.numOfPendingTasks--;
    }

    public int getNumOfPendingTasks() {
        return numOfPendingTasks;
    }

    /**
     * Gets the intended access index of a task in TaskList
     *
     * @param parser Parser which contains the processed command line
     * @return The corresponding array access
     * @throws DukeException If the task is empty,
     * no integer index provided,
     * or index given is out of range
     */
    public int getTaskID(Parser parser) throws DukeException {
        if (tasks.isEmpty()) {
            throw new EmptyTasksException("duke.Task is empty");
        }
        if (Objects.isNull(parser.getTaskNumber())) {
            throw new NullIndexException("Target is null");
        }

        int target = parser.getTaskNumber() - 1;
        if (!isWithinValidRange(target)) {
            throw new RangeIndexException("Range is invalid");
        }

        return target;
    }

    /**
     * Attempts to check a task as done, then display a success message
     * If the checking as done fails, a fail message is displayed instead
     *
     * @param parser
     */
    public void done(Parser parser) {
        try {
            Task targetTask = tasks.get(getTaskID(parser));
            if (targetTask.isDone) {
                throw new AlreadyDoneIndexException("Task already done");
            }

            targetTask.markAsDone();

            UI.displayStarLine();
            System.out.println("Good job! This task is now marked done:");
            System.out.println(targetTask);
            UI.displayStarLine();

            decrementPendingTasks();

        } catch (DukeException e) {
            UI.displayMessage(e.toString());
        }
    }

    /**
     * Attempts to delete a task, then display a success message
     * If the deletion fails, a fail message is displayed instead
     *
     * @param parser
     */
    public void delete(Parser parser) {
        try {
            Task targetTask = tasks.get(getTaskID(parser));
            tasks.remove(targetTask);

            if (!targetTask.isDone) {
                decrementPendingTasks();
            }

            UI.displayStarLine();
            System.out.println("Alright! This task is now deleted:");
            System.out.println(targetTask);
            UI.displayStarLine();
        } catch (DukeException e) {
            UI.displayMessage(e.toString());
        }
    }

    /**
     * Attempts to add a task to the list, then display a success message
     * If the addition fails, a fail message is displayed instead
     *
     * @param parser
     */
    public void add(Parser parser) {
        try {
            String commandWord = parser.getCommandWord();
            String taskName = parser.getTaskName();
            String taskDate = parser.getTaskDate();
            TaskType task = TaskType.getTaskType(commandWord);
            task.addToTasks(tasks, taskName, false, taskDate);

            int size = tasks.size();
            incrementPendingTasks();

            UI.displayStarLine();
            System.out.println("'" + taskName + "' added to list!");
            System.out.println(tasks.get(size - 1));
            System.out.println("\nYou now have " + size + " task(s) in your list.\n");
            System.out.println("(Use 'list' command to see your updated list.)");
            UI.displayStarLine();
        } catch (DukeException e) {
            UI.displayMessage(e.toString());
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

