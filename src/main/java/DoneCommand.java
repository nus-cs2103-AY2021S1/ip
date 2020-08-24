public class DoneCommand extends Command {

    private TaskList taskList;
    private Storage storage;
    private String user_input;

    public DoneCommand(TaskList taskList, Storage storage, String user_input) {
        this.taskList = taskList;
        this.storage = storage;
        this.user_input = user_input;
    }

    @Override
    public void execute() throws DukeInvalidUserInputException{
        //Get number after done keyword
        if (this.user_input.length() == 4) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the description of a done must not be empty.");
        }
        try {
            String int_substring = this.user_input.substring(5);
            int int_substring_converted = Integer.parseInt(int_substring);
            this.taskList.markDone(int_substring_converted);
            this.storage.saveTaskList(this.taskList); //Overwrites current data.txt file
        } catch (NumberFormatException ex) {
            throw new DukeInvalidUserInputException("My sincere apologies, but please enter a valid number.");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidUserInputException("Oh dear, it appears that item does not exist.");
        }
    }
}
