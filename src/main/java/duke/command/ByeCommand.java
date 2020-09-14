package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.concurrent.CompletableFuture;

public class ByeCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });
        ui.showFarewell();
        return "Farewell.";
    }
}
