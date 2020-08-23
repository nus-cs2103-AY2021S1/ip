import java.io.IOException;

public class AddEventCommand extends AddCommand {

    private String fullCommand;

    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EventWrongFormatException {
        try {
            String[] splitLineIntoTwo = fullCommand.split("/at");
            Task newTask = new Event(splitLineIntoTwo[0]
                    .substring(6).trim(),
                    splitLineIntoTwo[1].trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException e)
        { // Command is in a wrong format
            throw new EventWrongFormatException();
        }
    }
}