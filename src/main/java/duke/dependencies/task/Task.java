package duke.dependencies.task;

import java.io.Serializable;

import static duke.dependencies.task.CompletionState.*;

public abstract class Task implements Serializable, Schedulable {
    protected CompletionState state;
    protected String task;

    protected Task(String task) {
        this.task = task;
        this.state = UNFINISHED;
    }

    abstract String getDateLine();

    /**
     * Returns boolean indicating presence of date/deadline.
     * @return true if the task has a date/deadline associated with it
     */
    public boolean hasADate() {
        return false;
    };

    /**
     * Returns the task.
     * @return string describing the task
     */
    public String showTask() {
        return this.task;
    }

    /**
     * Returns boolean indicating whether task is empty.
     * @return true if the task is empty
     */
    public boolean isItEmpty() {
        return false;
    }

    /**
     * Completes the task.
     */
    public void completed() {
        this.state = COMPLETED;
    }

    /**
     * Checks whether this task has been completed.
     * @return
     */
    public boolean isCompleted() {
        return state == COMPLETED;
    }

    /* -------------------------- Static factory methods to create different Tasks ----------------------- */

    /**
     * Returns a task representing the corresponding task index in the todoList of the user.
     * Task corresponds to Done, Delete commands. MiscTask cannot be completed by the user.
     *
     * @param task this task should be in the inform "1" or "1 2 3", checking done at Parser
     * @return task
     */
    public static Task createMiscTask(String task) { return new DoneTask(task); }

    /**
     * Returns a task that is empty. Corresponds to a List command where there is no task.
     * Empty task cannot be completed.
     *
     * @return empty task
     */
    public static Task createEmptyTask(){
        return new EmptyTask();
    }

    /**
     * Returns a task representing a todoTask. Task has no date or deadline associated.
     *
     * @param task string describing the task
     * @return todoTask
     */
    public static Task createTodo(String task) {
        return new ToDos(task);
    }

    /**
     * Returns a task representing an event. Task will have a date associated with it,
     * specifying when the event is taking place.
     *
     * @param task string describing the task
     * @param date date string, in valid format: "dd/MM/uuuu" or "uuuy-MM-dd"
     * @return eventTask
     */
    public static Task createEvent(String task, String date) {
        return new Events(task, date);
    }

    /**
     * Returns a task representing a deadline. Deadlines has to be completed by the specified date
     * De deadline associatione is user referencion.
     *
     * @param task string describing the task
     * @param date date string, in valid format: "dd/MM/uuuu" or "uuuy-dd-MM"
     * @return deadlineTask
     */
    public static Task createDeadline(String task, String date) {
        return new Deadlines(task, date);
    }

    /* ----------------------------------------- Subclasses--------------------------------------------- */

    private static class EmptyTask extends Task{

        private EmptyTask() {
            super(null);
            super.state = null;
        }

        @Override
        public String getDateLine() {
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

    private static class DoneTask extends Task{

        private DoneTask(String nums) {
            super(nums);
            super.state = null;
        }

        @Override
        public String getDateLine() {
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
        public String getDateLine() {
            return this.tDate.toString();
        }

        @Override
        public String toString() {
            return String.format(
                    "[Event][%s] %s (at: %s)", super.state == UNFINISHED ? "X"
                            : Character.toString((char)0x2713),
                    super.task,
                    date);
        }
    }

    private static class ToDos extends Task {
        private ToDos(String task) {
            super(task);
        }

        @Override
        public String getDateLine() {
            return "";
        }

        @Override
        public String toString() {
            return String.format(
                    "[ToDo][%s] %s", super.state == UNFINISHED ? "X"
                            : Character.toString((char)0x2713),
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
        public String getDateLine() {
            return this.tDate.toString();
        }

        @Override
        public String toString() {
            return String.format(
                    "[Deadline][%s] %s (by: %s)", super.state == UNFINISHED ? "X"
                            : Character.toString((char)0x2713),
                    super.task,
                    deadline);
        }
    }

}
