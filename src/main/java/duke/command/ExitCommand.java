package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Command that exits the Duke program.
 */
public class ExitCommand extends Command {


    /**
     * Prints goodbye message.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     */
    @Override
    public void execute(TaskList tasks, TaskList archives, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    /**
     * Returns goodbye message
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @return String of goodbye message.
     * @throws DukeException
     */
    @Override
    public String getExecuteString(TaskList tasks, TaskList archives,
                                   Ui ui, Storage storage) throws DukeException {
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return ui.getGoodbyeString();
    }

    /**
     * Returns true to exit program.
     * @return True to exit program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
