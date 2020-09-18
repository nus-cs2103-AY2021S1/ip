package sparrow.data.trivia;

import java.util.ArrayList;
import java.util.List;

public class VocabList {
    private final List<Vocabulary> vocabList;

    public VocabList() {
        this.vocabList = new ArrayList<>();
    }

    public VocabList(List<Vocabulary> vocabList) {
        this.vocabList = vocabList;
    }

    public List<Vocabulary> getVocabList() {
        return vocabList;
    }

    public void addVocab(Vocabulary toAdd) {
        this.vocabList.add(toAdd);
    }

    /**
     * Deletes a word from the vocab list.
     * @param vocabNum Vocab number corresponding to the word to be deleted.
     * @return Vocab deleted.
     */
    public Vocabulary deleteVocab(int vocabNum) {
        return vocabList.remove(vocabNum - 1);
    }
}
