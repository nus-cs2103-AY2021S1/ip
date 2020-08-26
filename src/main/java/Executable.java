public interface Executable {
    Report execute(Storage storage, TaskList tasks, Ui ui) throws IncorrectArgumentException, StorageException;
}
