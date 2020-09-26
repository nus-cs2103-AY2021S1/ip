package duke.tasks;

/**
 * The ToDo is a subclass of Task and it is used to describe tasks that have no specific deadline
 */

public class ToDo extends Task {
    /**
     * Constructor assigns name to name value and initialize ToDO task
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor assigns name and done to values and initialize ToDO task
     *
     * @param name assigns name to this.name
     * @param done assigns done to this.done
     */
    public ToDo(String name, boolean done) {
        super(name, done);
    }

    /**
     * Takes no arguments and overrides the toString method
     *
     * @return the specific representation for ToDo class as mentioned with [T] indicating that it is a ToDo class
     *
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    public String inputListFormat() {
        return "T" + super.inputListFormat(); //format of Tasks to appear in file in Storage
    }
}
