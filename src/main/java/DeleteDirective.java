public class DeleteDirective implements Executable {
    private final String ERROR_INCORRECT_INDEX = "sToP TrYiNg tO FoOl mE. taSK #%d dOeS NoT ExIsT.";
    private final String MESSAGE_DELETE_SUCCESS = "gReAt! OnE FeWeR ThInG To rEmEmBeR: %s\n" +
            "i sTiLl nEeD To rEmEmBeR %d tAsK(s).";

    private final int index;

    public DeleteDirective(int index) {
        this.index = index;
    }

    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui)
            throws IncorrectArgumentException, StorageException {
        try {
            Task deletedTask = tasks.delete(index);

            storage.save(tasks.getTaskList());

            return new Report(String.format(MESSAGE_DELETE_SUCCESS, deletedTask.toString(), tasks.count()));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgumentException(String.format(ERROR_INCORRECT_INDEX, index + 1));
        }
    }
}
