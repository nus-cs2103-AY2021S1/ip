package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        tasks = new TaskList(new ArrayList<>(tasks.toStream()
                .filter(x -> x.description.contains(description)).collect(Collectors.toList())));
        StringBuilder output = new StringBuilder();
        if (tasks.isEmpty()) {
            output = new StringBuilder("I'm sorry, there's nothing that matches your search.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (i == tasks.size() - 1) {
                    output.append(String.format("%d. %s", i + 1, tasks.get(i)));
                } else {
                    output.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
                }
            }
        }
        return ui.showOutput(output.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
