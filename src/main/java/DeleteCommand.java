public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    // Method to get index for the task to be deleted
    protected int getDeletedTaskIndex() throws DukeException {
        try {
            String[] deleteCommand = this.command.split(" ");
            return Integer.parseInt(deleteCommand[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

}
