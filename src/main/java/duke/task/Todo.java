package duke.task;

public class Todo extends Task {

    public Todo(String description, String tag) {
        super(description, tag);
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        if (this.hasTag) {
            return "T" + " | " + completionStatus + " | " + this.description
                    + this.tag.toString();
        } else {
            return "T" + " | " + completionStatus + " | " + this.description;
        }
    }

    @Override
    public String toString() {
        if (this.hasTag) {
            return "[T]" + this.getStatusIcon() + " " + super.toString()
                    + this.tag.toString();
        } else {
            return "[T]" + this.getStatusIcon() + " " + super.toString();
        }
    }
}
