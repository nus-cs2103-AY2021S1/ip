public class ListCommand extends Command {
    protected boolean isExit() {
        return false;
    }

    protected void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("\tThere are currently no tasks on your list!\n"
                    + "\tStart adding one now!");
        } else {
            taskList.listTasks();
        }
    }
}
