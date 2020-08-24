import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

	Deadline deadline;

	DeadlineCommand(String input) throws DukeArgumentException {
		String[] args = input.split("/by ", 2);
		try {
			this.deadline = new Deadline(args[0].trim(), LocalDate.parse(args[1].trim()));
		} catch (ArrayIndexOutOfBoundsException ae) {
			throw new DukeArgumentException("Insufficient arguments provided for Deadline.");
		} catch (DateTimeParseException de) {
			throw new DukeArgumentException("Deadline date/time could not be parsed.");
		}
	}

	@Override
	public boolean isExit() {
		return super.isExit();
	}

	@Override
	void execute(Storage storage) throws DukeExecutionException {
		try {
			storage.add(deadline);
			Ui.showTaskAddition(deadline);
		} catch (IOException e) {
			throw new DukeExecutionException("Could not execute command due to IO exception.");
		}
	}
}
