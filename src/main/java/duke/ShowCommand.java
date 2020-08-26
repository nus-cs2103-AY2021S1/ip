package duke;

public class ShowCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        if (tasks.isEmpty()) {
            output = new StringBuilder("Theres currently nothing in your list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (i == tasks.size() - 1) {
                    output.append(String.format("%d. %s", i + 1, tasks.get(i)));
                } else {
                    output.append(String.format("%d. %s%n", i + 1, tasks.get(i)));
                }
            }
        }
        ui.showOutput(output.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
