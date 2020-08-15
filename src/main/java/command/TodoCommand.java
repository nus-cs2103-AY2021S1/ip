package command;

import java.util.ArrayList;
import java.util.List;

/**
 * A subclass of Command which adds a todo to the taskList
 */
public class TodoCommand extends AddCommand {
    private final String name;

    public TodoCommand(String content) {
        String[] contentParts = content.split(" /at ");
        this.name = contentParts[0];
    }

    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add("T");
        contentList.add(this.name);
        return contentList;
    }
}
