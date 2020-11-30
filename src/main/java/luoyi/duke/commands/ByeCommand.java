package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.data.IDuke;

public class ByeCommand extends Command {
    /**
     * Returns a new Command.
     *
     * @param duke Duke object to perform action on.
     */
    protected ByeCommand(IDuke duke) {
        super(-1, duke);
    }

    /**
     * Returns a ByeCommand object.
     *
     * @return ByeCommand object not yet initiated with duke.
     */
    public static ByeCommand getByeCommand() {
        return new ByeCommand(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        return Message.FAREWELL.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command setDuke(IDuke duke) {
        return new ByeCommand(duke);
    }
}
