package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.IDuke;

/**
 * HelpCommand class to handle help command.
 */
public class HelpCommand extends Command {

    private final String keyword;

    private HelpCommand(IDuke duke, String keyword) {
        super(-1, duke);
        this.keyword = keyword;
    }

    /**
     * Returns a HelpCommand object.
     *
     * @param keyword Keyword used to find help.
     * @return HelpCommand object, not yet initiated with duke.
     */
    public static HelpCommand getHelpCommand(String keyword) {
        return new HelpCommand(null, keyword);
    }

    /**
     * Returns and displays help message.
     *
     * @return Help message.
     */
    @Override
    public String execute() {
        String helpMessage = Message.getHelpMessage(keyword);
        TextFormatter.getFormattedText(helpMessage);
        return helpMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HelpCommand setDuke(IDuke duke) {
        return new HelpCommand(duke, keyword);
    }
}
