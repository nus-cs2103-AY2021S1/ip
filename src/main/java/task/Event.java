package task;

public class Event extends Task {
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{"E", String.valueOf(super.isDone), super.tag, super.name, this.at};
        return String.join(Task.DELIMITER, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.equals("")) {
            tag = Task.TAG_ICON + super.tag;
        }
        return "[E]" + super.toString() + " (at: " + at + ")" + tag;
    }
}
