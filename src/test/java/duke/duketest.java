package duke;


import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class duketest {
    @Test
    public void testErrOutput(){
        Duke testDuke = new Duke();
        assertThrows(IllegalArgumentException.class, () -> testDuke.run("blah"));
    }

    @Test
    public void testIncompleteCommands1(){
        Duke testDuke = new Duke();
        assertThrows(NoSuchElementException.class, () -> testDuke.run("Todo"));
    }

    @Test
    public void testIncompleteCommands2(){
        Duke testDuke = new Duke();
        assertThrows(NoSuchElementException.class, () -> testDuke.run("Deadline"));
    }


}
