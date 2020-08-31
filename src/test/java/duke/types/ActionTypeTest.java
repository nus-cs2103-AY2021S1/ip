package duke.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTypeTest {

    @Test
    void valueOfDeleteType() {
        ActionType todoType = ActionType.valueOfType("delete");
        assertEquals(todoType, ActionType.DELETE);
    }

    @Test
    void valueOfDoneType() {
       ActionType doneType = ActionType.valueOfType("done");
        assertEquals(doneType, ActionType.DONE);
   }

    @Test
    void valueOfFindType() {
        ActionType findType = ActionType.valueOfType("find");
        assertEquals(findType, ActionType.FIND);
    }
}