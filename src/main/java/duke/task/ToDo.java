package duke.task;

/**
 * Represents probably the simplest type of Task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertTxt() {
        return "T | " + (this.status ? "1" : "0") + " | " + name;
    }

    @Override
    public String getDate() {
        return "";
    }
}
