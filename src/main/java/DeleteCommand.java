public class DeleteCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public DeleteCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = input.split(" ");

        if (splitStr.length == 1) {
            throw new DukeException("☹ OOPS!!! I don't know which task to delete.");
        }

        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
        Task task = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);

        storage.deleteCurrentDataInFile(taskIndex + 1, tasks.getSize());
        System.out.println("    Noted. I've removed this task:\n      " + task + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
