public class AddCommand implements Command {
    private Task newTask;

    public AddCommand(Task newTask) { this.newTask = newTask; }

    public boolean isExit() { return false; }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui = new Ui();
        taskList.add(newTask);
        storage.saveList(taskList);
        ui.giveResponse("Got it. I've added this task:\n       " +
                newTask +
                "\n\t Now you have " + taskList.size() +
                " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }

}
