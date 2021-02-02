import org.junit.jupiter.api.Test;

import rock.utility.Parser;

public class ParserTest {
    @Test
    public void getTypeTest() {
        Parser.CommandType tmp = Parser.getType("deadline something /by sometime");
        assert(tmp.equals(Parser.CommandType.DEADLINE));
    }
}
