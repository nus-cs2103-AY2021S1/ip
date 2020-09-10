package tickbot.task;

import java.util.ArrayList;

/**
 * The class to represent a TO-DO task.
 */
public class Todo extends Task {
    public Todo(boolean completed, String content) {
        super(completed, content, null, new ArrayList<>());
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getTimeMarker() {
        return null;
    }
}
