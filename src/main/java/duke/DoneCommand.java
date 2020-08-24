package duke;

public class DoneCommand extends Command {
    private String args;
    
    DoneCommand(String args) {
        this.args = args;
    }
    
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        try {
            // parse for argument - item number
            int itemNumber = Integer.parseInt(args.split(" ")[1]) - 1;
            Task task = taskItems.getTask(itemNumber);
            task.markDone();
            ui.doneTaskReply(task);
            storage.saveTaskToMemory(taskItems.getAll());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("duke.Task number does not exist");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
