public class DeleteCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String indexString;
        try {
            indexString = input.substring(7);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\tPlease enter a task number you wish to delete!\n"
                    + "\tYou have " + taskList.getSize() + " tasks on your list now.");
        }
        if (indexString.isBlank()) {
            throw new DukeException("\tPlease enter a task number you wish to delete!\n"
                    + "\tYou have " + taskList.getSize() + " tasks on your list now.");
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > taskList.getSize())) {
            throw new DukeException("\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!");
        }
        taskList.deleteTask(index, storage);
    }
}
