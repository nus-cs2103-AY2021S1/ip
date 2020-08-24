package duke.task;

import duke.io.Parser;

import java.util.ArrayList;

public class Task {
    private boolean done;
    protected final String description;

    public Task(String task) {
        this.description = task;
        done = false;
    }

    public void markDone() {
        done = true;
    }

    private String getStatusSymbol() {
        return done ? "[\u2713]" : "[\u2718]";
    }
    
    protected int getDoneInteger() {
        return done ? 0 : 1;
    }

    public String toString() {
        return getStatusSymbol() + " " + description;
    }

    public String toSave() {
        return getDoneInteger() + " | " + description;
    }
    
    protected ArrayList<Object> dateAndTimeFormatter(String date) {
        Parser parser = new Parser();
        return parser.dateAndTimeFormatter(date);
    }
    
}
