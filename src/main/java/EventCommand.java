public class EventCommand extends Command{

    public EventCommand(String command) {
        super(command);
    }

    // Method to get the date for an event task
    protected String getDateForTask() throws InvalidTaskDescriptionException {
        try {
            String[] taskDetails = this.command.split("/");
            return taskDetails[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }

    // Method to obtain the description of a event or deadline task
    protected String getDescriptionForTask() throws InvalidTaskDescriptionException {
        try {
            String[] taskDetails = this.command.split("/");
            String[] taskDetailsWithoutDate = taskDetails[0].split(" ", 2);
            return taskDetailsWithoutDate[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }
}
