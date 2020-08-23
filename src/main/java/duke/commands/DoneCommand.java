package duke.commands;


import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import static java.lang.Integer.parseInt;

public class DoneCommand extends Command {
    
    public DoneCommand(String[] inputArr) {
        super(inputArr);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        marking(parseInt(inputArr[1]), tasks.size(), ui, tasks);
    }

    // marking the duke.task
    private void marking(int num, int size, Ui ui, TaskList tasks) {
        if (num <= 0 || size < num) {
            ui.messageFormatter(() -> System.out.println("Invalid input for done"));
        } else {
            Task task = tasks.get(num - 1);
            ui.messageFormatter(() -> task.markAsDone());
        }
    }
}
