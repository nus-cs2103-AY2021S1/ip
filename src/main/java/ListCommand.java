public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println(">> " + i++ + ". " + task);
        }
    }
}

