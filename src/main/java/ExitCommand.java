public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidSaveFileException {
        System.out.println("\tBye. Hope to see you again soon!");
        storage.saveFile(tasks.getTasks());
    }
    public boolean isExit() {
        return true;
    }
}
