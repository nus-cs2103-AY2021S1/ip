package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Here are the matching tasks in your list:");

        String wordToFind = userInput.substring(5);

        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(wordToFind)) {
                System.out.println("    " + (i + 1) + ". " + curr);
            }
        }
    }
}
