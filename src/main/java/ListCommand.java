public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks.getList()) {
            System.out.println(index + ". " + task.toString());
            index++;
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}