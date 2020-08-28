package duke.tasks;

import duke.exceptions.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;
    private String type;

    public Task(String description, String type) throws DukeException {
        this.type = type;
        if (!description.isEmpty()) {
            if (description.contains("/by")) {
                this.type = "deadline";
                this.description = description;
            } else if (description.contains("/at")) {
                this.type = "event";
                this.description = description;

            } else {
                this.type = "todo";
                this.description = description;
            }
            this.isDone = false;
        } else {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void finishTask() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    public String toSave() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

}