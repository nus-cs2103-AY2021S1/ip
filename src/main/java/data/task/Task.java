package data.task;

import data.exception.DukeInvalidUserInputException;

/**
 * Base class of a task.
 */
public abstract class Task {
    private String description;
    private String[] descriptionArr; //to facilitate finding/searching of keyword
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.descriptionArr = description.split(" ");
        this.isDone = false;
    }

    /**
     * Obtains status icon(either tick or cross symbol) based on the Task's isDone status.
     * @return status icon string.
     */
    //return tick or cross symbol accordingly
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Parses a given string into a specific type of task.
     * @param txtFormat to be parsed into a task.
     * @return a specific task type based on the txtFormat.
     * @throws DukeInvalidUserInputException when txtFormat is of invalid format to be parsed into a task.
     */
    public static Task parse(String txtFormat) throws DukeInvalidUserInputException {
        char firstLetter = txtFormat.charAt(0);
        String[] txtArray = txtFormat.split("\\|");
        if (firstLetter == 'T') {
            return ToDo.parse(txtArray);
        } else if (firstLetter == 'D') {
            return Deadline.parse(txtArray);
        } else if (firstLetter == 'E') {
            return Event.parse(txtArray);
        } else {
            throw new DukeInvalidUserInputException("My apologies but I am not familiar with such a task type.");
        }
    }

    /**
     * Converts the task into a string to be saved into a text file.
     * @return task in the form of a string.
     */
    public String toTxtFormat() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Checks whether the description of a task contains a specific keyword.
     * @param keyword to be checked.
     * @return whether the keyword is within the description.
     */
    public boolean descriptionContains(String keyword) {
        for (String word : descriptionArr) {
            if (word.toLowerCase().equals(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}