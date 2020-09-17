package task;

public class ToDo extends Task {
    public static final String COMMAND = "todo";
    private static final String DISPLAY_SYMBOL = "[T]";
    private static final String PARSED_SYMBOL = "T";

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{ToDo.PARSED_SYMBOL, String.valueOf(super.isDone), super.tag, super.name};
        return String.join(Task.DELIMITER, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.equals("")) {
            tag = Task.TAG_ICON + super.tag;
        }

        return String.format("%s%s %s", ToDo.DISPLAY_SYMBOL, super.toString(), tag);

    }
}
