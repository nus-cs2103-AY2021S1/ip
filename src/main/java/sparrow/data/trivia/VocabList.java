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
}
