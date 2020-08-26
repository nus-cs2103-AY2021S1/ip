import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventStringTest(){
        Event myEvent = new Event("test event", "2020-08-26");
        String expectedStringRep = "[E][✘] test event (at: Aug 26 2020)";
        assertEquals(myEvent.toString(), expectedStringRep);
    }
}
