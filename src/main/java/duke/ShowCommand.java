package duke;

import java.util.stream.IntStream;

/**
 * Represents a show command.
 */
public class ShowCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "tasks have not been initialised";
        assert ui != null : "ui have not been initialised";
        assert storage != null : "storage have not been initialised";
        StringBuilder output = new StringBuilder();
        if (tasks.isEmpty()) {
            output.append("Theres currently nothing in your list.");
        } else {
            IntStream.range(0, tasks.size()).forEach(i -> {
                if (i == tasks.size() - 1) {
                    output.append(String.format("%d. %s", i + 1, tasks.get(i)));
                } else {
                    output.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
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
