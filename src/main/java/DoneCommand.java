public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeIndexException {

        if (index > tasklist.getTaskSize() - 1 || index < 0) {
            String errorMessage = "Wrong list number input. " +
                    "Please put a number between 1 and " + tasklist.getTaskSize();
            throw new DukeIndexException(errorMessage);
        }

        tasklist.makeTaskDone(index);
        ui.printDone(tasklist.get(index).toString());

    }
}
