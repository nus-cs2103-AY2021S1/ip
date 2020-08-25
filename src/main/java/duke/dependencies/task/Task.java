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

    public boolean hasADate() {
        return false;
    };

    public String showTask() {
        return this.task;
    }

    public boolean isItEmpty() {
        return false;
    }

    public void completed() {
        this.state = COMPLETED;
    }

    public boolean isCompleted() {
        return state == COMPLETED;
    }

    /* -------------------------- Static factory methods to create different Tasks ----------------------- */

    public static Task createMiscTask(String task) { return new DoneTask(task); }

    public static Task createEmptyTask(){
        return new EmptyTask();
    }

    public static Task createTodo(String task) {
        return new ToDos(task);
    }

    public static Task createEvent(String task, String date) {
        return new Events(task, date);
    }

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
