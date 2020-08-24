class ByeCommand extends Command {
    
    public ByeCommand() {
    }
    
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {
        ui.close();
    }
}
