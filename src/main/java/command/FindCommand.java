package command;

import duke.Storage;
import duke.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filtered = taskList.find(keyword);
        ui.print("Here are the matching tasks in your list:");
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < filtered.size(); i++) {
            ui.print(String.format("%d. %s", i + 1, filtered.show(i)));
            response += String.format("%d. %s", i + 1, filtered.show(i));
        }
        return new CommandResult(response);
    }
}
