package duke.tasks;

/**
 * ToDo is a Implementation of the Task Class with no additional fields
 * This is the class that stores todo duke.tasks in the Duke program
 */
public class ToDo extends Task {
    /**
     * Constructs the ToDo Class for use when creating new duke.tasks
     * by end user.
     * @param desc Description of task
     */
    public ToDo(String desc) {
        super(desc, false);
    }
    /**
     * Constructs the ToDo Class for use when populating the list of duke.tasks
     * recorded by the save text file
     * @param desc Description of task
     * @param done Done Status of task
     */
    ToDo(String desc, Boolean done) {
        super(desc, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns a String Representation of the ToDo object class to write to text file.
     * @return the saved task to write to a text file
     */
    public String saveTask() {
        return "T" + SEPERATOR + super.saveTask();
    }
}
