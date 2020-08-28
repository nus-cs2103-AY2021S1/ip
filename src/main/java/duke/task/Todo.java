package duke.task;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
        this.storeAs = "T,0," + description;
    }

    public Todo(String done, String description) {
        super(description);

        if (done.equals("1")) {
            this.isDone = true;
            this.storeAs = "T,1," + description;
        }
        this.storeAs = "T,0," + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}