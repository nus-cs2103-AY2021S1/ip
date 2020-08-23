import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void isTaskModificationF_done_isTaskModification(){
        boolean isTaskModification = Parser.isTaskModification("done");

        assertEquals(isTaskModification, true);
    }

    @Test
    public void isTaskModificationF_delete_isTaskModification(){
        boolean isTaskModification = Parser.isTaskModification("delete");

        assertEquals(isTaskModification, true);
    }

    @Test
    public void isTaskModificationF_todo_isNotTaskModification(){
        boolean isTaskModification = Parser.isTaskModification("todo");

        assertEquals(isTaskModification, false);
    }
}

/*
todo borrow book
deadline return book /by 2019-10-15
event project meeting /at 2019-10-15

list

done 2

delete 2
 */