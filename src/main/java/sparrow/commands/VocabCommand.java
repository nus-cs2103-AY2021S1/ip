package sparrow.commands;

import sparrow.data.task.TaskList;
import sparrow.data.trivia.VocabList;
import sparrow.data.trivia.Vocabulary;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

public class VocabCommand extends Command {
    public static final String COMMAND_WORD = "vocab";

    public static final String ADD_VOCAB_SUCCESS = " has been added t' yer vocabulary list.";

    private final Vocabulary newVocab;

    public VocabCommand(String newWord) {
        this.newVocab = new Vocabulary(newWord);
    }

    public VocabCommand(String newWord, String definition) {
        this.newVocab = new Vocabulary(newWord, definition);
    }

    @Override
    public String execute(TaskList tasks, VocabList vocabList, Ui ui, Storage storage) {
        vocabList.getVocabList().add(newVocab);
        storage.saveTaskListToFile(tasks);
        storage.saveVocabListToFile(vocabList);
        return newVocab.getWord() + ADD_VOCAB_SUCCESS;
    }
}
