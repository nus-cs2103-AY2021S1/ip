package duke;

public class Todo extends Task {

    Todo(String name) {
        super(name);
    }

    @Override
    String getIndicator() {
        return "[T]";
    }

}
