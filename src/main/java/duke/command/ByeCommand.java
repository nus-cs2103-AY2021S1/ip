package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Implements bye command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class ByeCommand extends Command {
    /**
     * Executes the given command.
     *
     * @param tasks Task list the user currently have.
     * @param ui Tool to interact with user.
     * @param storage Storage to load and save data.
     * @return Responses to be passed to user.
     * @throws DukeException If failed to save tasks.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks.getTasks());
        //@@author AudreyFelicio-reused
        //Reused from
        //https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        //with modifications and extra functionality. Code is used to set timeout before exiting application.
        PauseTransition delayExit = new PauseTransition(Duration.seconds(1));
        delayExit.setOnFinished(event -> {
            Platform.exit();
            System.exit(0);
        });
        delayExit.play();
        //@@author
        return ui.showGoodbyeUser();
    }

    /**
     * Returns an indicator if the program will terminate.
     *
     * @return Indicator of termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
