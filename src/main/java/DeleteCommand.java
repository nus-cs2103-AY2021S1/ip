public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(String input, int index) {
        super(input);
        this.index = index;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DeletionException {
        if (index >= tasks.size() || index < 0) {
            throw new DeletionException("Item does not exist in list!");
        }

        Task task = tasks.get(index);
        tasks.remove(index);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    boolean isExit() {
        return false;
    }
}
