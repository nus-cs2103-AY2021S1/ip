package task;

import magic.Format;
import magic.MyString;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{MyString.DATA_TODO_SYMBOL, String.valueOf(super.isDone), super.tag, super.name};
        return String.join(MyString.DATA_TASK_SEP, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.isEmpty()) {
            tag = Task.TAG_ICON + super.tag;
        }

        return String.format(Format.DISPLAY_TODO, MyString.DATA_TODO_SYMBOL, super.toString(), tag);

    }
}
