package duke.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTypeTest {

    @Test
    void valueOfDeleteType() {
        ActionType todoType = ActionType.valueOfType("delete");
        assertEquals(ActionType.DELETE, todoType);
    }

    @Test
    void valueOfDoneType() {
       ActionType doneType = ActionType.valueOfType("done");
        assertEquals(ActionType.DONE, doneType);
   }

    @Test
    void valueOfFindType() {
        ActionType findType = ActionType.valueOfType("find");
        assertEquals(ActionType.FIND, findType);
    }
}
