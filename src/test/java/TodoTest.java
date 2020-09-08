import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void descConstructor_normalInput_success(){ //tests basic constructor
        Todo todo = new Todo("do homework");
        assertEquals("[T][0] do homework", todo.toString());
    }

    @Test
    public void descDoneConstructor_normalInput_success(){ //test constructor that marks task as done
        Todo todo = new Todo("watch youtube videos", true);
        assertEquals("[T][1] watch youtube videos", todo.toString());
    }

    @Test
    public void display_normalInput_success(){ //tests display() method
        Todo todo = new Todo("watch youtube videos", true);
        assertEquals("[T][1] watch youtube videos", todo.display());
    }

    @Test
    public void descConstructor_emptyDesc_success(){ //tests display() method
        Todo todo = new Todo("", true);
        assertEquals("[T][1] ", todo.display());
    }
}
