import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void userInputList_botReplyHeadingIsCorrect() {
        assertEquals("Checking the whole list doesn't make you finish anything faster... \n",
                new UI().botReplyHeading("list").get(0));
    }
}
