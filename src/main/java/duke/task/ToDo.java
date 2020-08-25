package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getStatusIcon() {
        return String.format("[T]%s ", super.getStatusIcon());
    }

    @Override
    public String writeToFile() {
        int done = isDone ? 1 : 0;
        return String.format("T//%d//%s\n", done, this.description );
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (!(o instanceof ToDo)) {
            return false;
        }
        ToDo c = (ToDo) o;
        return super.description.equals(((ToDo) o).description);
    }
}

