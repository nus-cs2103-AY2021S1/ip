package duke.task;

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
        try {
            return Arrays.stream(arr).filter(str -> str.contains(word)).toArray().length > 0;
        } catch (Exception e) {
            System.out.println("EXCEPTION");
            return true;
        }
    }
    
    public boolean fuzzyMatch(String word) {
        String [] arr = description.split(" ");
        return Arrays.stream(arr).filter(str -> partialEquals(word, str)).toArray().length > 0;
    }
    
    private boolean partialEquals(String inputWord, String descriptionWord) {
        int mid = (int) Math.ceil(inputWord.length() / 2.0);
        return descriptionWord.length() >= mid && inputWord.substring(0, mid).equals(descriptionWord.substring(0,
                mid));
    }
    

    /**
     * Return true or false - whether date of task is equal to input date.
     * Always overidden.
     * @param word Input date.
     * @return boolean.
     */
    public boolean isSameDate(String word) {
        return false;
    }
    
}
