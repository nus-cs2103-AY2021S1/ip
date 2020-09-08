package duke.task;

import duke.component.DukeException;

import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> listOfTags;
    protected String stringOfTags;

    /**
     * constructor for task abstract class.
     * @param description string of task's description.
     *
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.listOfTags = new ArrayList<String>();
        this.stringOfTags = "";
    }

    /**
     * returns approprivate icon for task done or not.
     * @return string of icon.
     */
    public String getStatusIcon() {
        final String STATUS_ICON_PREFIX =  ("[" + (isDone ? "\u2713" : "\u2718") + "] ");
        return STATUS_ICON_PREFIX;
    }

    /**
     * changes status of task to done.
     */
    public void markDone() {
        this.isDone = true;
    }

    public void addTag(String tag) {
        String tagAdded = "#" + tag;
        this.listOfTags.add(tagAdded);
        this.stringOfTags = this.stringOfTags + tagAdded;
    }

    public void removeTag(int index) {
        this.listOfTags.remove(index - 1);
    }

    public String toString() {
        return this.getStatusIcon() + description;
    }

    /**
     * checks if task is done based on inary value.
     * @param number character of number representing done status.
     * @return boolean representing icon status.
     * @throws DukeException exception thrown when exception caught while running.
     */
    public static boolean checkIfDone(char number) throws DukeException {
        switch (number) {
        case '0':
            return false;
        case '1':
            return true;
        default:
            throw new DukeException("Item in list on HDD does not have a 'done' status");
        }
    }

    public boolean isContain(String term) {
        String stringToSearch = this.description + this.stringOfTags;
        final boolean isContainsTerm = stringToSearch.contains(term);
        return isContainsTerm;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public abstract String stringToSave();


}

