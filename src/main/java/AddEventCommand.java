public class AddEventCommand extends Command {

    public AddEventCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        String[] arr = command.split(" /at ");

        String desc;
        Task task;
        String at;

        try {
            desc = arr[0].substring(6).trim();
            at = arr[1];
            task = new Event(desc, at);
            ui.print("     Got it. I've added this task");
            task.printTask();

            taskList.add(task);
            ui.printListSize(taskList.listSize());
        } catch (Exception ex) {
            throw new SparklesException("     OOPS!! The description and time of an Event cannot be empty!");
        } finally {
            storage.updateFile(taskList.storage);
        }
    }
}
