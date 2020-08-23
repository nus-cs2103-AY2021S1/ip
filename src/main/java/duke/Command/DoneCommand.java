package duke.Command;

import duke.Exception.DoneException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private final int listNumber;

    public DoneCommand(String input, int listNumber) {
        super(input);
        this.listNumber = listNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoneException {
        if (listNumber > tasks.size()) {
            throw new DoneException("Item number " + listNumber + " does not exist in list!");
        }
        tasks.get(listNumber - 1).completeTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(listNumber - 1).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
