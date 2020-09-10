package task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{"T", String.valueOf(super.isDone), super.tag, super.name};
        return String.join(Task.DELIMITER, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.equals("")) {
            tag = Task.TAG_ICON + super.tag;
        }
        return "[T]" + super.toString() + " " + tag;
    }
}
