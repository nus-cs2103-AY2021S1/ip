package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.data.trivia.Vocabulary;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class DefineCommand extends Command {

    public static final String COMMAND_WORD = "define";
    public static final String NO_WORD_FOUND = "Ye 'ave nah added this word t' yer list.";


    private final String targetWord;

    /**
     * Creates a DefineCommand with the word specified.
     * @param word Word to define.
     */
    public DefineCommand(String word) {
        super();
        this.targetWord = word;
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        for (Vocabulary word: vocabList.getVocabList()) {
            if (word.getWord().equals(targetWord)) {
                return word.define();
            }
        }
        return NO_WORD_FOUND;
    }
}
