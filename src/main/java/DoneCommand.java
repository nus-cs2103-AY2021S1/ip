public class DoneCommand extends Command {
    private final String description;

    public DoneCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(this.description);
            Task task = tasks.getTask(taskNum);
            tasks.markDone(taskNum, storage);

            System.out.println("Can, I help you mark this as done liao:" +
                    "\n  " +
                    task.toString());
        } catch (NumberFormatException e) {
            throw new WrongItemIndexException(CommandType.DONE.toString().toLowerCase(),
                    tasks.getListLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
