package duke;

public class DoneCommand extends Command {

    public DoneCommand(String task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processDone(this.task, taskList, ui, storage);
        } catch (DoneException done) {
            System.out.println(done.getMessage());
        }
    }

    public void processDone(String theRest, TaskList taskList, Ui ui, Storage storage) throws DoneException {
        try {
            Integer taskNum = Integer.parseInt(theRest);
            taskList.markTaskAsDone(taskNum);
            storage.updateData(taskList.getTasks());

        } catch (DukeException d) {
            throw new DoneException("Please enter a valid task number");
        }
    }
}
