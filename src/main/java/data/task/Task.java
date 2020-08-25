package data.task;

//A task represents an item in Duke's todoList
//A task consists of:
// 1) Content Description
// 2) Status (isDone or not)

import data.exception.DukeInvalidUserInputException;

public abstract class Task {
    private String description;
    private String[] descriptionArr; //to facilitate finding/searching of keyword
    private boolean isDone;

    //Constructor
    public Task(String description) {
        this.description = description;
        this.descriptionArr = description.split(" ");
        this.isDone = false;
    }

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
            return null;
        }
    }

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