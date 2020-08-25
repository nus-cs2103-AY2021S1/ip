package duke;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class duketest {
    @Test
    public void testErrOutput(){
        Duke testDuke = new Duke("./");
        assertThrows(IllegalArgumentException.class, () -> testDuke.run("blah"));
    }

}
