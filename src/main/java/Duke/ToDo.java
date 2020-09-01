package Duke;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getParsedData() {
        return "T" + "/" + String.valueOf(super.isDone) + "/" + super.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
