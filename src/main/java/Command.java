public interface Command {

    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException, InvalidFormatException;
}
