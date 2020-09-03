package blue.command;

import java.util.Random;

import blue.Storage;
import blue.task.Tasks;
import blue.ui.Ui;

/**
 * The Bye command which terminates the program.
 */
public class ByeCommand extends Command {
    /**
     * Instantiates a new Bye command.
     */
    public ByeCommand() {
        this.commandType = CommandType.BYE;
    }

    /**
     * Returns a response consisting exit message.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to bye command.
     */
    @Override
    public CommandResponse execute(Tasks tasks, Ui ui, Storage storage) {
        String message = ui.getExitMessage();
        if (new Random().nextInt(4) == 0) {
            message += ui.getLongExitMessage();
        }
        return new CommandResponse(message, this.isExit());
    }
}
