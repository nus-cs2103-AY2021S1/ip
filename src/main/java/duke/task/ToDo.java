package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) || obj instanceof ToDo) {
            ToDo todoObj = (ToDo) obj;
            return todoObj.isDone == this.isDone && todoObj.description.equals(this.description);
        } else {
            return false;
        }
    }
}
