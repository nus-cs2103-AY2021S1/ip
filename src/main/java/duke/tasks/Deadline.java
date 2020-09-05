package duke.tasks;

import duke.Parser;

/**
 * Deadline is a subclass of task, with a time by which the task has to be completed
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Constructor for deadline class
     * @param description description of the task
     * @param deadline deadline by which task needs to be completed
     * @param isDone indicates whether the task has been completed or not
     */
    public Deadline (String description, String deadline, boolean isDone) {
        super (description, "Deadline", isDone);
        this.deadline = deadline;
    }
    
    @Override
    public String getTime() {
        return this.deadline;
    }

    @Override
    public String toString() {
        Parser parser = new Parser();
        if (isDone) {
            return String.format ("[D][DONE] %s (by: %s)", this.description, parser.convertDate(deadline));
        } else {
            return String.format ("[D][NOT DONE] %s (by: %s)", this.description, parser.convertDate(deadline));
        }
    }
}
