package data.task;

//A task represents an item in Duke's todoList
//A task consists of:
// 1) Content Description
// 2) Status (isDone or not)

import data.exception.DukeInvalidUserInputException;

public abstract class Task {
    private String description;
    private boolean isDone;

    //Constructor
    public Task(String description) {
        this.description = description;
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
            return ToDo.parse(txtFormat, txtArray);
        } else if (firstLetter == 'D') {
            return Deadline.parse(txtFormat, txtArray);
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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
