import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import data.task.ToDo;

public class ToDoTest {

    @Test
    public void toTxtFormat_emptyDescription_returnsEmptyDescription() {
        ToDo toDo = new ToDo("");
        assertEquals("T | 0 | ", toDo.toTxtFormat());
    }

    @Test
    public void toTxtFormat_nonEmptyDescription_returnsDescription() {
        ToDo toDo = new ToDo("Description");
        assertEquals("T | 0 | Description", toDo.toTxtFormat());
    }

    @Test
    public void parse_nullTxtArray_throwsException() {
        String[] txtArray = null;
        assertThrows(NullPointerException.class, () -> ToDo.parse(txtArray));
    }

    @Test
    public void parse_validTxtArray_returnsToDo() {
        String[] txtArray = {"T", "1", "A Valid Description"};
        ToDo toDo = ToDo.parse(txtArray);
        assertEquals("[T][\u2713] A Valid Description", toDo.toString());
    }
}
