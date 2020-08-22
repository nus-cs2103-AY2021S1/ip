public class DoneCommand extends Command {
    protected boolean isExit() {
        return false;
    }

    protected void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String indexString;
        try {
            indexString = input.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + taskList.getSize() + " tasks on your list now.");
        }
        if (indexString.isBlank()) {
            throw new DukeException("\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + taskList.getSize() + " tasks on your list now.");
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > taskList.getSize())) {
            throw new DukeException("\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!");
        }
        taskList.markTaskDone(index, storage);
    }
}
