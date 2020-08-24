class InvalidCommand extends Command {

    public InvalidCommand() {
    }

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {
        throw new DukeException("Invalid Command.");
    }
}
