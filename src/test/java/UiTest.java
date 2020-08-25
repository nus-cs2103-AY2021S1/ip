import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    Ui myUi = new Ui();

    @Test
    public void horizontalRuleTest(){
        assertEquals(myUi.horizontalRule(),"____________________________________________________________");
    }

    @Test
    public void greetingTest(){
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        assertEquals(myUi.greeting(),logo);
    }


}
