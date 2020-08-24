class ListCommand extends Command {

    public ListCommand() {
        
    }
    
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {
        ui.printToConsole(taskList.convertTaskListToString());
    }
    
}
