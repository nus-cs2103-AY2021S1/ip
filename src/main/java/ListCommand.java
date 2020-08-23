public class ListCommand extends Command {
    private final CommandType commandType;
    
    public ListCommand() {
        this.commandType = CommandType.LIST;
    }
    
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
