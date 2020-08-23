package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String output() {
        return "T" + super.output() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo o = (ToDo) obj;
            return description == o.description;
        } else {
            return false;
        }
    }
}
