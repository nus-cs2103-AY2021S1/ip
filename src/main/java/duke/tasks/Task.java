package duke.tasks;

import duke.Tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a task that can be completed
 */
public class Task implements Serializable {
    private String name;
    private Boolean isDone;
    private List<Tag> tags;

    public Task (String s) {
        name = s;
        isDone = false;
        tags = new ArrayList<Tag>();
    }

    public String getName() {
        return name;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setAsDone() {
        isDone = true;
    }

    public void addTag(Tag t) {
        tags.add(t);
    }

    public List<Tag> getTags() {
        return tags;
    }


}
