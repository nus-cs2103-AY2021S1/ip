public class ListCommand extends Command {
    public ListCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment! Good job!\n");
            return;
        }

        System.out.println("Here are your tasks!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". "
                    + tasks.get(i));
        }
        System.out.println();
    }
}
