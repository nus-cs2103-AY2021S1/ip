package duke.dependencies.task;

import java.io.Serializable;

import static duke.dependencies.task.CompletionState.*;

/**
 * Represents a task, something that is associated with a given Executable/command from the user.
 */
public abstract class Task implements Serializable, Schedulable {
    protected CompletionState state;
    protected String task;

    private static final String TICK = "\u2713";
    private static final String CROSS = "\u274c";

    protected Task(String task) {
        this.task = task;
        this.state = UNFINISHED;
    }

    abstract String getDateString();

    /**
     * Returns whether the Task has a given date/deadline which is represented by a TaskDate object.
     * @return True if the Task has a Task Date.
     */
    public boolean hasADate() {
        return false;
    };

    /**
     * Returns the String describing this Task.
     * @return String representation of this Task.
     */
    public String showTaskDescription() {
        return this.task;
    }

    /**
     * Returns whether the Task is an empty Task.
     * @return True if it is empty.
     */
    public boolean isItEmpty() {
        return false;
    }

    /**
     * Returns whether this task is a Miscellaneous task. Miscellaneous task are task
     * defined to be task which are not for adding to the user's task list. These task do not represent
     * task in which the user can complete in any way shape or form. They are meant for the Duke's
     * implementation.
     *
     * @return True if the task is a Misc. task, false otherwise.
     */
    public boolean isMiscTask() {
        return false;
    }

    /**
     * Completes this Task. This represents the user finishing this task.
     */
    public void completed() {
        this.state = COMPLETED;
    }

    /**
     * Returns whether this Task is completed.
     * @return True if the user has completed this task.
     */
    public boolean isCompleted() {
        return state == COMPLETED;
    }

    /* -------------------------- Static factory methods to create different Tasks ----------------------- */

    /**
     * Returns a task representing the corresponding task index in the todoList of the user.
     * Task corresponds to Done, Delete commands.
     *
     * @param task This task should be in the inform "1" or "1 2 3".
     * @return Miscellaneous Task.
     */
    public static Task createMiscTask(String task) { return new DoneTask(task); }

    /**
     * Returns a task that is empty. Corresponds to a List command where there is no task.
     *
     * @return Empty Task.
     */
    public static Task createEmptyTask(){
        return new EmptyTask();
    }

    /**
     * Returns a task representing a todoTask. Task has no date or deadline associated.
     *
     * @param task String describing the task.
     * @return Todo Task.
     */
    public static Task createTodo(String task) {
        return new ToDos(task);
    }

    /**
     * Returns a task representing an event. Task will have a date associated with it,
     * specifying when the event is taking place.
     *
     * @param task String describing the task.
     * @param date Date string, in valid format: "dd/MM/uuuu" or "uuuy-MM-dd".
     * @return Event Task.
     */
    public static Task createEvent(String task, String date) {
        return new Events(task, date);
    }

    /**
     * Returns a task representing a deadline. Deadlines has to be completed by the specified date
     * De deadline associatione is user referencion.
     *
     * @param task String describing the task.
     * @param date Date string, in valid format: "dd/MM/uuuu" or "uuuy-MM-dd".
     * @return Deadline Task.
     */
    public static Task createDeadline(String task, String date) {
        return new Deadlines(task, date);
    }

    /* ----------------------------------------------- Subclasses---------------------------------------------------- */

    private static class EmptyTask extends Task{

        private EmptyTask() {
            super(null);
            super.state = null;
        }

        @Override
        public String getDateString() {
            return "";
        }

        @Override
        public boolean isItEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "There is nothing here to see...";
        }
    }

    private static class DoneTask extends Task{ // Should be Misc Task not Done Task

        private DoneTask(String nums) {
            super(nums);
            super.state = null;
        }

        @Override
        public String getDateString() {
            return "";
        }

        @Override
        public boolean isItEmpty() {
            return true;
        }

        @Override
        public boolean isMiscTask() {
            return true;
        }

        @Override
        public String toString() {
            return "There is nothing here to see...";
        }
    }

    private static class Events extends Task {
        private String date;
        private TaskDate tDate;

        private Events(String task, String date) {
            super(task);
            this.date = date;
            this.tDate = new TaskDate(date);
        }

        @Override
        public boolean hasADate() {
            return true;
        }

        @Override
        public String getDateString() {
            return this.tDate.toString();
        }

        @Override
        public String toString() {
            return String.format(
                    "[Event][%s] %s (at: %s)", super.state == UNFINISHED ? CROSS
                            : TICK,
                    super.task,
                    date);
        }
    }

    private static class ToDos extends Task {
        private ToDos(String task) {
            super(task);
        }

        @Override
        public String getDateString() {
            return "";
        }

        @Override
        public String toString() {
            return String.format(
                    "[ToDo][%s] %s", super.state == UNFINISHED ? CROSS
                            : TICK,
                    super.task);
        }
    }

    private static class Deadlines extends Task {
        private String deadline;
        private TaskDate tDate;

        private Deadlines(String task, String deadline) {
            super(task);
            this.deadline = deadline;
            this.tDate = new TaskDate(deadline);
        }

        @Override
        public boolean hasADate() {
            return true;
        }

        @Override
        public String getDateString() {
            return this.tDate.toString();
        }

        @Override
        public String toString() {
            return String.format(
                    "[Deadline][%s] %s (by: %s)", super.state == UNFINISHED ? CROSS
                            : TICK,
                    super.task,
                    deadline);
        }
    }

}
