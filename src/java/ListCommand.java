public class ListCommand extends Command {

	/**
	 * Prints out the entire list in the storage
	 *
	 * @param storage The Storage object from which to print the list
	 */
	@Override
	void execute(Storage storage) {
		Ui.wrapText(storage.toString());
	}

}
