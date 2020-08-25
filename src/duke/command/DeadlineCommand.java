package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.task.Deadline;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

	Deadline deadline;

	public DeadlineCommand(String input) throws DukeArgumentException {
		String[] args = input.split("/by ", 2);
		try {
			this.deadline = new Deadline(args[0].trim(), LocalDate.parse(args[1].trim()));
		} catch (ArrayIndexOutOfBoundsException ae) {
			throw new DukeArgumentException("Insufficient arguments provided for duke.Tasks.Deadline.");
		} catch (DateTimeParseException de) {
			throw new DukeArgumentException("duke.Tasks.Deadline date/time could not be parsed.");
		}
	}

	@Override
	public boolean isExit() {
		return super.isExit();
	}

	@Override
	public void execute(Storage storage) throws DukeExecutionException {
		try {
			storage.add(deadline);
			Ui.showTaskAddition(deadline);
		} catch (IOException e) {
			throw new DukeExecutionException("Could not execute command due to IO exception.");
		}
	}
}
