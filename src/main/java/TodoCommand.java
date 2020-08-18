public class TodoCommand extends Command{

    public TodoCommand(String command) {
        super(command);
    }


    // Method to get the description of a todo task
    protected String getTodoDescription() throws InvalidTaskDescriptionException {
        try {
            String[] todoDetails = this.command.split(" ", 2);
            return todoDetails[1];
        } catch (IndexOutOfBoundsException e){
            throw new InvalidTaskDescriptionException();
        }
    }
}
