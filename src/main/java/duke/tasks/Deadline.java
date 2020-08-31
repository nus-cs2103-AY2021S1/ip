package duke.tasks;

import duke.Parser;

public class Deadline extends Task {

    private final String time;

    /**
     * Constructor for deadline class
     * @param description description of deadline task
     * @param time time by which task needs to be completed
     * @param isDone indicates whether the task has been completed
     */
    public Deadline (String description, String time, boolean isDone) {
        super (description, "Deadline", isDone);
        this.time = time;
    }
    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        Parser p = new Parser();
        if (isDone) {
            return String.format ("[D][DONE] %s (by: %s)", this.description, p.convertDate(time));
        } else {
            return String.format ("[D][NOT DONE] %s (by: %s)", this.description, p.convertDate(time));
        }
    }
}
