package raythx98.grandma.command;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import raythx98.grandma.storage.Storage;
import raythx98.grandma.task.TaskList;
import raythx98.grandma.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save();
        ui.farewell();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Something.
     *
     * @return
     */
    public boolean isExit() {
        return true;
    }
}
