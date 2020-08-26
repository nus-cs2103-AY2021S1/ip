public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        Ui.addMessage("Bye. Hope to see you again soon!");
        Ui.sendMessages();
        System.exit(0);
    }
}
