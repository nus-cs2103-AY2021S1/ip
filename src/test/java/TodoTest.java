import org.junit.jupiter.api.Test;
import task.EmptyStringException;
import task.Task;
import task.Todo;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void encode(){
        try{
            Task task = new Todo("Green eggs and ham");
            String encoded = task.encode();
            assertEquals("todo Green eggs and ham", encoded);
        }
        catch (EmptyStringException e){
            fail();
        }
    }
    @Test
    public void todo_normalInput_writtenCorrectly(){
        try {
            Task task = new Todo("Green eggs and ham");
            String taskOutput = task.toString();
            assertEquals(taskOutput, "[T] [✗] Green eggs and ham");
        }
        catch (EmptyStringException e){
            fail();
        }
    }
    @Test
    public void todo_emptyInput_exceptionThrown(){
        try {
            Task task = new Todo("");
            fail();
        }
        catch (EmptyStringException e){

        }
    }
}
