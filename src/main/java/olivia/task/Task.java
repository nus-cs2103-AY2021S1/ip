package olivia.task;

import java.util.regex.Pattern;

import olivia.util.OliviaException;

/**
 * Task class that represents a task.
 */

public abstract class Task {
    protected String description;
    protected String tag;
    protected boolean isDone;

    /**
     * Constructor that creates a Task object that has a description of the
     * task, a tag representing the type of task, and whether the task has been
     * completed. Protected so only subclasses of Task can access and create a
     * Task object, to prevent any instances from being directly created.
     *
     * @param description a String representing the description of the task
     * @param tag a String representing the type of task
     * @param isDone a boolean representing whether the task has been completed
     */

    protected Task(String description, String tag, boolean isDone) {
        this.description = description;
        this.tag = tag;
        this.isDone = isDone;
    }

    /**
     * Marks the task as complete by changing the isDone boolean to true.
     */

    public void complete() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Parses a string that has been validated to contain the necessary information,
     * then returning a Task matching the information. Throws an exception if the tag
     * given is incorrect, or if too few arguments are given.
     *
     * @param str a String containing the necessary information to create a Task
     * @return a Task object with the information stated in the String
     * @throws OliviaException thrown if the tag given is incorrect, or if too few arguments
     *                       are given
     */

    public static Task parse(String str) throws OliviaException {
        String[] input = str.split(Pattern.quote(" | "));
        try {
            boolean isDone = input[1].equals("1");
            switch (input[0]) {
            case "T":
                return new ToDo(input[2], isDone);
            case "D":
                return new Deadline(input[2], input[3], isDone);
            case "E":
                return new Event(input[2], input[3], isDone);
            default:
                throw new OliviaException("One or more Tasks are wrongly tagged!");
            }
        } catch (ArrayIndexOutOfBoundsException aiooe) {
            throw new OliviaException("One or more Tasks have too few arguments!");
        }
    }

    public void update(String str) {
        this.description = str;
    }

    /**
     * Returns a String formatted to how a Task should be saved in the associated
     * save file.
     *
     * @return a formatted String to be written to the save file
     */

    public String toSave() {
        return String.format("%s | %s | %s", this.tag, isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tag, this.getStatusIcon(), this.description);
    }
}
