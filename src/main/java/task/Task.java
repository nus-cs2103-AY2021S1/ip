package task;

import magic.Format;
import magic.MyString;

/**
 * Super class for all types of tasks.
 */
public class Task {
    public static final String TAG_ICON = MyString.TAG_ICON;

    protected final String name;
    protected boolean isDone;
    protected String tag;

    /**
     * Constructor creates a task and initialises done to false.
     *
     * @param name name of Task.
     */
    public Task(String name) {
        assert name.length() > 0 : MyString.ERROR_INVALID_NAME;
        this.name = name;
        this.isDone = false;
        this.tag = "";
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Formats task into the data format.
     *
     * @return Task as Parsed Format.
     */
    public String getParsedData() {
        String[] output = new String[]{String.valueOf(this.isDone), this.name};
        return String.join(MyString.DATA_TASK_SEP, output);
    }

    public void setTag(String tagName) {
        this.tag = tagName;
    }

    @Override
    public String toString() {
        char symbol = isDone ? MyString.TICK : MyString.CROSS;
        return String.format(Format.DISPLAY_TASK, symbol, name);
    }
}

