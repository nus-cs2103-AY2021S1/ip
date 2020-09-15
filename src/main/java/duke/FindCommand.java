package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a Find Command
 */
public class FindCommand extends Command {
    /**
     * Constructs a Find Command with description.
     * @param description Description of command.
     */
    FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks have not been initialised";
        assert ui != null : "ui have not been initialised";
        assert storage != null : "storage have not been initialised";
        TaskList filteredTaskList = new TaskList(new ArrayList<>(tasks.toStream()
                .filter(x -> x.description.contains(description)).collect(Collectors.toList())));
        StringBuilder output = new StringBuilder();
        if (filteredTaskList.isEmpty()) {
            output.append("I'm sorry, there's nothing that matches your search.");
        } else {
            IntStream.range(0, filteredTaskList.size()).forEach(i -> {
                if (i == filteredTaskList.size() - 1) {
                    output.append(String.format("%d. %s", i + 1, filteredTaskList.get(i)));
                } else {
                    output.append(String.format("%d. %s%n", i + 1, filteredTaskList.get(i)));
                }
            });
        }
        return ui.showOutput(output.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
