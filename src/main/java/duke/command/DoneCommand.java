package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            if (index > tasks.size()) {
                throw new DukeException("C'mon parder! That task doesn't exist cos you don't go so many!");
            }
            Task toBeDone = tasks.get(index);
            tasks.markAsDone(index);
            System.out.println("Sure thing baws! This right here is marked as done!\n" + toBeDone.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
