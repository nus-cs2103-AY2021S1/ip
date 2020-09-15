package duke.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int isDone) {
        super(description, isDone);
    }


    @Override
    public String toString() {
        return super.toString().replace("[", "[T][");
    }

    public String getData() {
        return "T" + super.getData();
    }
}
