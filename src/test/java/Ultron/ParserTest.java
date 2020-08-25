package ultron;

import ultron.commands.ByeCommand;
import ultron.commands.DeleteCommand;
import ultron.commands.DoneCommand;
import ultron.commands.HelpCommand;
import ultron.commands.ListCommand;
import ultron.commands.TaskAllocator;
import ultron.exceptions.UltronException;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void checkByeInputTest(){
        try {
            assert Parser.parseCommand("bye") instanceof ByeCommand;
        } catch (UltronException e){
            assert false;
        }
    }

    @Test
    public void checkListInputTest(){
        try {
            assert Parser.parseCommand("list") instanceof ListCommand;
        } catch (UltronException e){
            assert false;
        }
    }

    @Test
    public void checkHelpInputTest(){
        try {
            assert Parser.parseCommand("help") instanceof HelpCommand;
        } catch (UltronException e){
            assert false;
        }
    }

    @Test
    public void checkDeleteInputTest(){
        try {
            assert Parser.parseCommand("delete") instanceof DeleteCommand;
        } catch (UltronException e){
            assert false;
        }
    }

    @Test
    public void checkDoneInputTest(){
        try {
            assert Parser.parseCommand("done") instanceof DoneCommand;
        } catch (UltronException e){
            assert false;
        }
    }

    @Test
    public void checkTodoInputTest(){
        try {
            assert Parser.parseCommand("todo") instanceof TaskAllocator;
        } catch (UltronException e){
            assert false;
        }
    }

    @Test
    public void checkDeadlineInputTest(){
        try {
            assert Parser.parseCommand("deadline") instanceof TaskAllocator;
        } catch (UltronException e){
            assert false;
        }
    }

    @Test
    public void checkEventInputTest(){
        try {
            assert Parser.parseCommand("event") instanceof TaskAllocator;
        } catch (UltronException e){
            assert false;
        }
    }
}
