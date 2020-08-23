package duke.command;

import duke.exception.DukeException;

import duke.task.Task;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        StringBuilder output = new StringBuilder("\t Here are the tasks containing the keyword ")
                .append(String.format("\"%s\"", keyword)).append(":\n");

        int numbering = 1;
        boolean isUnavailable = true;
        Task task;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if (task.hasKeyword(keyword)) {
                if (isUnavailable) {
                    isUnavailable = false;
                }
                output.append("\t ").append(numbering).append(".").append(task).append("\n");
                numbering++;
            }
        }

        if (isUnavailable) {
            ui.showMessage("\tThere are no tasks containing the keyword "
                    + String.format("\"%s\"", keyword) + "!\n");
        } else {
            ui.showMessage(output.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
