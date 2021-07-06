package duke;


import org.junit.jupiter.api.Test;

import command.Command;
import command.InvalidCommand;
import command.Result;
import duke.DukeExceptions;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class duketest {
    @Test
    public void testErrOutput(){
        Duke testDuke = new Duke();
        assertEquals(InvalidCommand.class, testDuke.getCommand("blah").getClass()  );
    }

    @Test
    public void testIncompleteCommands1(){
        Duke testDuke = new Duke();
        String userInput = "todo";
        Command command = testDuke.getCommand(userInput);
        Result result = testDuke.executeCommand(command);
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testIncompleteCommands2(){
        Duke testDuke = new Duke();
        String userInput = "deadline";
        Command command = testDuke.getCommand(userInput);
        Result result = testDuke.executeCommand(command);
        assertFalse(result.isSuccessful());
    }

    @Test
    public void testIncompleteCommands3(){
        Duke testDuke = new Duke();
        String userInput = "done";
        Command command = testDuke.getCommand(userInput);
        Result result = testDuke.executeCommand(command);
        assertFalse(result.isSuccessful());
    }

    //    @Test
    //    public void testCommands4(){
    //        Duke testDuke = new Duke();
    //        String userInput = "alias li list";
    //        Command command = testDuke.getCommand(userInput);
    //        Result result = testDuke.executeCommand(command);
    //        assertTrue(result.isSuccessful());
    //    }



}
