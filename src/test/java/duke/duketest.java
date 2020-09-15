package duke;


import org.junit.jupiter.api.Test;
import duke.DukeExceptions;


import static org.junit.jupiter.api.Assertions.assertFalse;
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
        String userInput = "todo";
        assertFalse(testDuke.run(userInput).isSuccessful());
    }

    @Test
    public void testIncompleteCommands2(){
        Duke testDuke = new Duke();
        String userInput = "deadline";
        assertFalse(testDuke.run(userInput).isSuccessful());
    }

    @Test
    public void testIncompleteCommands3(){
        Duke testDuke = new Duke();
        String userInput = "done";
        assertFalse(testDuke.run(userInput).isSuccessful());
    }



}
