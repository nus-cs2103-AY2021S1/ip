package command;

import java.util.ArrayList;
import java.util.List;

/**
 * A subclass of Command which adds a deadline to the taskList
 */
public class DeadlineCommand extends AddCommand {
    private final String name;
    private final String schedule;

    public DeadlineCommand(String content) {
        String[] contentParts = content.split(" /by ");
        this.name = contentParts[0];
        this.schedule = contentParts[1];
    }

    @Override
    public List<String> getContent() {
        List<String> contentList = new ArrayList<>();
        contentList.add("D");
        contentList.add(this.name);
        contentList.add(this.schedule);
        return contentList;
    }
}
