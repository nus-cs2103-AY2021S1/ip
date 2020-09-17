package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String USER_GUIDE_URL = "https://jonfoocy.github.io/ip/";

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        return "This command ain't recognised. Go t' https://jonfoocy.github.io/ip/ fer a list o' supported commands";
    }
}
