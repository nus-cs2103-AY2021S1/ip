package task;


public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ToDo) {
            ToDo other = (ToDo) o;
            return this.description.equals(other.description);
        } else {
            return false;
        }
    }

}
