package commands;

import duke.Storage;
import duke.Ui;
import exceptions.InvalidFileException;
import javafx.application.Platform;
import tasks.TaskList;

/**
 * Represents an instruction from the user to quit the bot.
 */
public class ExitCommand extends Command {

    /**
     * Prints the exit message and writes the current TaskList to the hard drive.
     * @param tasks The current TaskList.
     * @param ui The Ui object in use.
     * @param storage The Storage object in use.
     * @throws InvalidFileException If file to be written to cannot be found.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws InvalidFileException {
        // ui.printExitMessage();
        ui.setMessageExit();
        storage.writeToFile("data.txt", tasks.writeString());
        setTimeout(Platform::exit, 1000);
    }

    /**
     * Delays the execution of a command. Used to ensure exit message is shown before the program ends.
     * @param runnable A Runnable that you want to delay the execution for.
     * @param delay Number of milliseconds to delay execution of the command by.
     */
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
}
