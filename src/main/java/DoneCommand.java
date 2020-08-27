public class DoneCommand implements Command {
    private int index;
    
    DoneCommand(int index) {
        this.index = index;
    }

    private void checkIndex(TaskList tasks) throws DukeOutOfBoundsException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeOutOfBoundsException(CommandKey.DONE.getKey() + " " + index);
        }
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            checkIndex(tasks);
            Task task = tasks.get(index);
            task.setDone();
            ui.displayDoneMessage(task);
        } catch (DukeOutOfBoundsException e) {
            ui.displayError(e.toString());
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
