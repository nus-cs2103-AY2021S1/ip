package duke;

import command.AddDeadlineCommand;

import java.util.Arrays;

/**
 * Represents the task to be saved in the list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor to create task object.
     * @param description the description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A constructor to create task object.
     * @param description the description of task.
     * @param isDone mark if it is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * A function to return the icon.
     * @return String which is the icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * A function to help saving to the file.
     * @return String Which is the template to be saved in the file.
     */
    public String writeToFile() {
        String result = "T # ";
        if (isDone) {
            result += "1 # ";
        } else {
            result += "0 # ";
        }
        result += description;
        return result;
    }

    /**
     * A function to change the isDone to be negated.
     */
    public void changeIsDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task cur = (Task) o;
            if (cur.description.equals(this.description) && cur.isDone ==this.isDone)  {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
