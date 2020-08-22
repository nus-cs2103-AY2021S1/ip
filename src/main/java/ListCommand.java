public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new DukeException("\tThere are currently no tasks on your list!\n"
                    + "\tStart adding one now!");
        } else {
            taskList.listTasks();
        }
    }
}
