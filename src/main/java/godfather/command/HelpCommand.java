package godfather.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import godfather.TaskList;
import godfather.enums.CommandWord;
import godfather.exception.VitoException;
import godfather.ui.Ui;

public class HelpCommand implements Command {
    private final CommandWord topic;
    /**
     * Constructs HelpCommand
     *
     * @param input help command input
     */
    public HelpCommand(String input) {
        StringTokenizer st = new StringTokenizer(input);
        String command = st.nextToken();
        assert command.equals(CommandWord.HELP_CMD.getCmd()) : "Invalid Help Command invocation";
        // if st has more tokens, then it's a specific help request:
        if (st.hasMoreTokens()) {
            String topicString = st.nextToken().strip().toLowerCase();
            this.topic = CommandWord.getCommandWordFromString(topicString);
        } else {
            // generic help
            this.topic = CommandWord.HELP_CMD;
        }
    }
    @Override
    public String execute(TaskList tasks, Ui ui) throws VitoException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(CommandWord.HELP_CMD.getHelpMsg());
        ui.display(lines);
        return topic.getHelpMsg();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
