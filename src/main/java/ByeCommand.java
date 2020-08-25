public class ByeCommand extends Command{
    ByeCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Bye. Hope to see you again soon!");
    }
    
    @Override
    public String toString() {
        return "bye";
    }
}
