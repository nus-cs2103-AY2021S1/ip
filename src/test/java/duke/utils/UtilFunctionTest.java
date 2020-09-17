package duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class UtilFunctionTest {
    @Test
    public void matchPatternTest() {
        Assertions.assertEquals(true,
               UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "ls"));
        Assertions.assertEquals(true,
                UtilFunction.matchPattern("^(l|L)(i|I)?(s|S)(t|T)?$", "list"));
    }

}
