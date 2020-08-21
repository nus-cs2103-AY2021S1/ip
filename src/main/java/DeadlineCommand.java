public class DeadlineCommand extends Command {
    private final String description;
    private final String by;


    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            tasks.addTask(new Deadline(description, by));
        } catch (Exception ex) {
            throw new DukeException("Error creating this deadline");
        }
    }


}