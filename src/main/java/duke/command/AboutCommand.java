package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Class that represents user command for app's about page.
 */
public class AboutCommand extends Command {

    /**
     * Displays information about the app.
     *
     * @param ui Print-out and display manager.
     * @param taskManager <code>Task</code> manipulation manager.
     * @param saveManager Handles saving and loading.
     */
    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        String[] messages = {
                "Hi! Duke's a friendly chatbot here to help you keep track of your life!",
                "Duke will be the galaxy brain that you wished you could be :D",
                "",
                "Author: Wang Zijun (GitHub: WangZijun97)",
                "",
                ">>> Credits:",
                "flaticon.com for the user icon image"
        };

        for (String s : messages) {
            ui.queueMessageToDisplay(s);
        }

    }
}
