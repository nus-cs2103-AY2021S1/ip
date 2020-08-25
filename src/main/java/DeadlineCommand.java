public class DeadlineCommand extends TaskCreationCommand {
    final static String COMMAND = "deadline";
    final static String TIME_SPECIFIER = "/by";
    
    private String description;
    private String time;
    
    DeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, time);
        super.execute(task, ui, tasks);
    }
}
