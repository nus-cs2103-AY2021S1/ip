package duke.task;

public class Todo extends Task {

    public Tag tag;
    public Todo(String description, boolean hasTag) {
        super(description, hasTag);
        if (hasTag) {
            this.tag = new Tag(description.substring(description.indexOf("@") + 1));
        }
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        return "T" + " | " + completionStatus + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + super.toString();
    }
}
