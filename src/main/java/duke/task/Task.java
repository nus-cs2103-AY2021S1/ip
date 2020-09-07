package duke.task;

import duke.io.Parser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a task. 
 */
public class Task {
    private boolean done;
    protected final String description;

    public Task(String task) {
        this.description = task;
        done = false;
    }

    /**
     * Label task as completed or done.
     */
    public void markDone() {
        done = true;
    }

    /**
     * Return a tick or cross unicode depending on the done status of a task.
     * 
     * @return String of unicode symbol.
     */
    private String getStatusSymbol() {
        return done ? "[\u2713]" : "[\u2718]";
    }

    /**
     * Return 0 or 1 depending on the done status of a task.
     * For storage purposes.
     * 
     * @return 0 or 1.
     */
    protected int getDoneInteger() {
        return done ? 0 : 1;
    }

    public String toString() {
        return getStatusSymbol() + " " + description;
    }

    public String toSave() {
        return getDoneInteger() + " | " + description;
    }

    /**
     * Return true or false - whether task contains input word.
     * @param word Input filter word.
     * @return boolean.
     */
    public boolean containsWord(String word) {
        String [] arr = description.split(" ");
        return Arrays.stream(arr).filter(str -> str.equals(word)).toArray().length > 0;
    }

    /**
     * Return true or false - whether date of task is equal to input date.
     * Always overriden.
     * @param word Input date.
     * @return boolean.
     */
    public boolean isSameDate(String word) {
        return false;
    }
    
}
