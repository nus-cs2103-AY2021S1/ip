public class AddCommand extends Command {

    private String type;
    private String task;

    public AddCommand(String type, String task) {
        this.type = type;
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String echoizer = "\t Got it. I've added this task:\n"
                + "\t %s\n"
                + "\t Now you have %d tasks in the list.\n";
        Task t = tasks.addTask(type, task);
        ui.showAction(String.format((echoizer) + "%n", t, tasks.size()));
    }
}
