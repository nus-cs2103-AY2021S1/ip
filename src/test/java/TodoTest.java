import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoConstructorTest1(){ //tests basic constructor
        Todo todo = new Todo("do homework");
        assertEquals("[T][0] do homework", todo.toString());
    }

    @Test
    public void todoConstructorTest2(){ //test constructor that marks task as done
        Todo todo = new Todo("watch youtube videos", true);
        assertEquals("[T][1] watch youtube videos", todo.toString());
    }

    @Test
    public void todoDisplayTest(){ //tests display() method
        Todo todo = new Todo("watch youtube videos", true);
        assertEquals("[T][1] watch youtube videos", todo.display());
    }

    @Test
    public void todoEmptyDescriptionTest(){ //tests display() method
        Todo todo = new Todo("", true);
        assertEquals("[T][1] ", todo.display());
    }
}
