package task;

import magic.Format;
import magic.MyString;

public class Event extends Task {
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
        String[] args = new String[]{MyString.DATA_EVENT_SYMBOL,
                String.valueOf(super.isDone), super.tag, super.name, this.at};
        return String.join(MyString.DATA_TASK_SEP, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.isEmpty()) {
            tag = Task.TAG_ICON + super.tag;
        }
        return String.format(Format.DISPLAY_EVENT,
                MyString.DATA_EVENT_SYMBOL, super.toString(), this.at, tag);
    }
}
