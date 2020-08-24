public class ListCommand extends Command {

    public ListCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty. Add a new task!");
        } else {
            System.out.println("Here is a list of all your tasks:");
            for (int i = 0; i < tasks.getListSize(); i++) {
                int index = i + 1;
                System.out.println("\t" + String.format("%d. %s", index, tasks.getTask(i)));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
