package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteDukeCommandException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class SearchCommand extends Command {

    private String searchParameter;
    private List<Task> results;

    public SearchCommand(String searchParameter) {
        this.searchParameter = searchParameter;
        results = new ArrayList<>();
    }

    @Override
    public void execute(TaskList list, Storage storage) {
        for (Task task : list) {
            if (task.match(searchParameter)) {
                results.add(task);
            }
        }
        super.completed = true;
    }

    @Override
    public void printFeedback(Ui ui) throws DukeException {
        if (super.completed) {
            String resultPrint = "";
            for (Task result : results) {
                if (resultPrint.length() > 0) {
                    resultPrint = resultPrint.concat("\n");
                }
                resultPrint = resultPrint.concat(result.toString());
            }
            ui.formattedPrint(ui.prependIndent(resultPrint, 1));
        } else {
            throw new IncompleteDukeCommandException("Search command was not completed.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
