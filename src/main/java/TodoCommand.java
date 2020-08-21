public class TodoCommand extends Command {
    private final String description;


    public TodoCommand(String description) {
        this.description = description;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        tasks.addTask(new Task(description));
    }


}