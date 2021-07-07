package command;

import java.util.concurrent.CompletableFuture;

import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * Implements the Command object created when user quits the application
 */
public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Shows close message
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
                System.exit(0);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
        return new CommandResult(ui.showCloseMessage());
    }
}
