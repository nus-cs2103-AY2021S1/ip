package sparrow.data.trivia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VocabularyTest {

    @Test
    public void equals() {
        Vocabulary vocabWithDefinition = new Vocabulary("pirate", "a person who attacks and robs ships at sea");
        Vocabulary sameDefinition = new Vocabulary("pirate", "a person who attacks and robs ships at sea");
        Vocabulary differentDefinition = new Vocabulary("pirate", "a person who robs and attacks ships at sea");
        Vocabulary differentWord = new Vocabulary("marauder", "a person who attacks and robs ships at sea");

        // same values -> returns true
        assertTrue(vocabWithDefinition.equals(sameDefinition));

        // different word -> returns false
        assertFalse(vocabWithDefinition.equals(differentWord));

        // different definition -> returns false
        assertFalse(vocabWithDefinition.equals(differentDefinition));

        // null -> returns false
        assertFalse(vocabWithDefinition.equals(null));

        // different type -> returns false
        assertFalse(vocabWithDefinition.equals(27));
    }
}
