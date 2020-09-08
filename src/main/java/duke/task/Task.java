package duke.task;

/**
 * Abstract class Task that provides basic functionality to the other Task objects
 * that extends from it.
 */
public abstract class Task {

    private boolean done;
    private String task;
    private Notes notes;

    Task(String task) {
        this.done = false;
        this.task = task;
        this.notes = new Notes("null");
    }
    Task(String task, boolean done) {
        this.done = done;
        this.task = task;
        this.notes = new Notes("null");
    }
    Task(String task, boolean done, String notes) {
        this.done = done;
        this.task = task;
        this.notes = new Notes(notes);
    }
    public boolean isDone() {
        return this.done;
    }
    public String getDescription() {
        return this.task;
    }

    /**
     * Marks the Task as completed.
     *
     * @return The Task that has been completed.
     */
    public String markDone() {
        this.done = true;
        return "Great job, keep it up!\n" + this.toString();
    }

    /**
     * Marks the Task as not completed.
     *
     * @return The Task that has been marked as not complete.
     */
    public String revertDone() {
        this.done = false;
        return "Guess you made a mistake huh?\n" + this.toString();
    }

    /**
     * Adds a note to the current Task.
     *
     * @param note The note to be added.
     * @return The String representation of the task and the note.
     */
    public String addNote(String note) {
        this.notes = new Notes(note);
        return this.toString() + "\nNote:\n" + this.notes.toString();
    }

    /**
     * Provides the string representation of the Task note when it is saved.
     *
     * @return The string representation of the note to be used in saving.
     */
    public String getNotesSaveString() {
        if (this.notes.getNote() == null) {
            return "null";
        }
        return this.notes.getNote();
    }

    /**
     * Provides the string representation of the Task when it is saved.
     *
     * @return The string representation of the Task to be used in saving.
     */
    public String getSaveString() {
        String result = "";
        if (this.done) {
            result += "[1] ";
        } else {
            result += "[0] ";
        }
        result += this.task;
        return result;
    }

    /**
     * Provides the string representation of the entire task including its note.
     *
     * @return The string representation of the entire task.
     */
    public String getFullTask() {
        String result = "";
        result += this.toString();
        result += "\nNote:\n" + this.notes.toString();
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        if (this.done) {
            result += "[\u2713] ";
        } else {
            result += "[\u2718] ";
        }
        result += this.task;
        return result;
    }
}
