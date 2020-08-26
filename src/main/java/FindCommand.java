public class FindCommand extends Command {
    private final String toFind;
    
    public FindCommand(String toFind) {
        this.toFind = toFind;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
        // find
        TaskList matchingTasks = tasks.FindTask(toFind);
        
        // print
        ui.printOnFind(matchingTasks);
    }
}
