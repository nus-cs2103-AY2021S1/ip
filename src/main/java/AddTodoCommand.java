import java.io.IOException;

public class AddTodoCommand extends AddCommand {

    private String fullCommand;

    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TodoWrongFormatException {
        try {
            Task newTask = new ToDo(fullCommand.substring(5).trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException e)
        { // Command is in a wrong format
            throw new TodoWrongFormatException();
        }
    }
}
