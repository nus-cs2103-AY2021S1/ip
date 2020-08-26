import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ParserTest {
    @Test
    public void invalidArgumentTest(){
        try{
            Parser pr = new Parser();
            String input = "deadline ";
            assertFalse(pr.isValidDeadline(input));

        }catch (DukeException e){
            System.out.println(e.toString());
        }
    }

}
