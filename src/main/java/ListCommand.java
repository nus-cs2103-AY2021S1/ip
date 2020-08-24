public class ListCommand extends Command {
    public static final boolean IS_EXIT = false;

    public ListCommand() {
        super(IS_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
//        System.out.println("    ___________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.println("    " + (i + 1) + "." + task);
        }
//        System.out.println("    ___________________________________________________________");
    }
}

