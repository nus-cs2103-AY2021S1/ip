package duke.tasks;

import duke.Tag;

import java.io.Serializable;
import java.util.List;

public class ToDo extends Task implements Serializable {

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        List<Tag> tags = super.getTags();
        String s = "";

        if (!tags.isEmpty()) {
            for (int i = 0; i < tags.size(); i++) {
                s += " #" + tags.get(i);
            }
        }
        return "[T]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + s;
    }
}
