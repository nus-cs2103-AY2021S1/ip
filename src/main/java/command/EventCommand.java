package command;

import java.util.ArrayList;
import java.util.List;

/**
 * A subclass of Command which adds an event to the taskList
 */
public class EventCommand extends AddCommand {
    private final String name;
    private final String schedule;

    public EventCommand(String content) {
        String[] contentParts = content.split(" /at ");
        this.name = contentParts[0];
        this.schedule = contentParts[1];
    }

    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add("E");
        contentList.add(this.name);
        contentList.add(this.schedule);
        return contentList;
    }
}
