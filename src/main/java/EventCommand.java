public class EventCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException, InvalidFormatException {
        String[] details = Parser.parseEvent(input);
        String title = details[0];
        String at = details[1];
        Task task = new Event(title, at);
        taskList.add(task);
        ui.add(task);
    }
}
