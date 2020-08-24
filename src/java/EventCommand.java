import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

	Event event;

	EventCommand(String input) throws DukeArgumentException {
		String[] args = input.split("/at ", 2);
		try {
			this.event = new Event(args[0].trim(), LocalDate.parse(args[1].trim()));
		} catch (ArrayIndexOutOfBoundsException ae) {
			throw new DukeArgumentException("Insufficient arguments provided for Event.");
		} catch (DateTimeParseException de) {
			throw new DukeArgumentException("Event date/time could not be parsed.");
		}
	}

	@Override
	public boolean isExit() {
		return super.isExit();
	}

	@Override
	void execute(Storage storage) throws DukeExecutionException {
		try {
			storage.add(event);
			Ui.showTaskAddition(event);
		} catch (IOException e) {
			throw new DukeExecutionException("Could not execute command due to IO exception.");
		}
	}
}
