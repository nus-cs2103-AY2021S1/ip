public class DeleteCommand extends Command {

    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeIndexException {

        if (this.index > tasklist.getTaskSize() - 1 || this.index < 0) {
            String errorMessage = "Wrong list number input. " +
                    "Please put a number between 1 and " + tasklist.getTaskSize();
            throw new DukeIndexException(errorMessage);
        }

        ui.printDelete(tasklist.get(index).toString(), tasklist.getTaskSize());
        tasklist.removeTask(index);

    }
}
