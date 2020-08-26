public class DoneDirective implements Executable {
    private final String ERROR_INCORRECT_INDEX = "sToP TrYiNg tO FoOl mE. taSK #%d dOeS NoT ExIsT.";
    private final String MESSAGE_DONE_SUCCESS = "fInAlLy, I feLL AsLeEp wHiLe wAiTiNg fOr yOu tO FiNiSh: %s";

    private final int index;

    public DoneDirective(int index) {
        this.index = index;
    }

    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui)
            throws IncorrectArgumentException, StorageException {
        try {
            tasks.get(index).markAsDone();

            storage.save(tasks.getTaskList());

            return new Report(String.format(MESSAGE_DONE_SUCCESS, tasks.get(index).toString()));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgumentException(String.format(ERROR_INCORRECT_INDEX, index + 1));
        }
    }
}
