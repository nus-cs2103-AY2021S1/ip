package duke.tasks;

import duke.Parser;

public class Deadline extends Task {

    String time;

    public Deadline(String description, String time) {
        super(description, "Deadline");
        this.time = time;
    }

    public Deadline (String description, String time, boolean done) {
        super (description, "Deadline", done);
        this.time = time;
    }
    
    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        Parser p = new Parser();
        if (done) {
            return String.format ("[D][DONE] %s (by: %s)", this.description, p.convertDate(time));
        } else {
            return String.format ("[D][NOT DONE] %s (by: %s)", this.description, p.convertDate(time));
        }
    }
}