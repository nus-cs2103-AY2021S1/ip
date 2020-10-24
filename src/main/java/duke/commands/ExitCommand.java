package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{

    @Override
    public String execute(String userInput, Ui ui, TaskList tasks, boolean isLoading) {
        String response = ui.showGoodBye();
        // use thread to exit Duke after few seconds, so there is enough time to show good bye message
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                },
                3000
        );
        return response;
    }
}
