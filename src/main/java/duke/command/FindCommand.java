package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.StringJoiner;

public class FindCommand extends Command {
    String toFind;
    
    public FindCommand(String toFind) {
        super(true);
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringJoiner response = new StringJoiner("\n");
        response.add(String.format("Here are the tasks in your list that contain \"%s\":", toFind));
        response.add(tasks.find(toFind));
        ui.printResponse(response.toString());
    }
}
