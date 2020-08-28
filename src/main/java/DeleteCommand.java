public class DeleteCommand extends Command {
    private final String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(this.description);
            Task task = tasks.getTask(taskNum);
            tasks.delete(taskNum, storage);

            System.out.println("Okay, I deleted this liao:" +
                    "\n  " +
                    task.toString() +
                    "\nNow left " +
                    tasks.getListLength() +
                    " things in the list.");
        } catch (Exception e) {
            throw new WrongItemIndexException(CommandType.DELETE.toString().toLowerCase(),
                    tasks.getListLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
