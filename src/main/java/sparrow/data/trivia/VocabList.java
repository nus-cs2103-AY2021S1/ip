package sparrow.data.trivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VocabList {
    private List<Vocabulary> vocabList;

    public VocabList() {
        this.vocabList = new ArrayList<Vocabulary>(Arrays.asList(new Vocabulary("phone"), new Vocabulary("wallet")));
    }

    public List<Vocabulary> getVocabList() {
        return vocabList;
    }
}
