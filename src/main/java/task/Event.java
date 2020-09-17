package task;

public class Event extends Task {
    public static final String COMMAND = "event";
    public static final String DISPLAY_SYMBOL = "[E]";
    public static final String PARSED_SYMBOL = "E";
    public static final String DELIMITER = "/at";

    private final String at;

    /**
     * Create Deadline task based on the name and range.
     * @param name Name of event.
     * @param at Time of event as a string.
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{Event.PARSED_SYMBOL, String.valueOf(super.isDone),
            super.tag, super.name, this.at};
        return String.join(Task.DELIMITER, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.equals("")) {
            tag = Task.TAG_ICON + super.tag;
        }
        return String.format("%s%s (at: %s) %s", Event.DISPLAY_SYMBOL,
                super.toString(), this.at, tag);
    }
}
