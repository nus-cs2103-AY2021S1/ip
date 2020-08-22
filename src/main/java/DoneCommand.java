public class DoneCommand extends Command {

    public DoneCommand(String command) {
        super(command);
    }

    // Method to get the index of the task that is already completed
    protected int getTaskIndex() throws InvalidTaskNumberException {
        try {
            String[] doneCommand = this.command.split(" ");
            int taskIndex = Integer.parseInt(doneCommand[1]);
            if (taskIndex > 0 && taskIndex <= TaskList.taskNum) {
                return taskIndex;
            } else {
                throw new InvalidTaskNumberException();
            }
        } catch (InvalidTaskNumberException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

}
