import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoStringTest(){
        Todo myTodo = new Todo("hello");
        String expectedStringRep = "[T][✘] hello";
        assertEquals(myTodo.toString(), expectedStringRep);
    }
}
