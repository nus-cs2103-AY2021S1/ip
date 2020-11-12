package duke.tasks;

import duke.Tag;

import java.io.Serializable;
import java.util.List;

public class ToDo extends Task implements Serializable {

    public ToDo(String s) {
        super(s);
    }

    public ToDo(String s, Boolean b) {
        super(s, b);
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

    /**
     * Returns true if both ToDos have the same name and boolean.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherConsultation = (Task) other;
        return otherConsultation.getName().equals(getName())
                && otherConsultation.isDone().equals(isDone());
    }

}
