public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        if (taskList.getSize() == 0) {
            ui.showNoTask();
        } else {
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + ": " + taskList.get(i));
            }
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
