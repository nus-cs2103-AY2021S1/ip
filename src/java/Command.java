abstract class Command {

	public boolean isExit() {
		return false;
	}

	abstract void execute(Storage storage) throws DukeExecutionException;

}
