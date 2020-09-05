import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoCommandTest {

    @Test
    void isExitTest_getIsExitOfTodoCmd_returnsFalse() {
        String[] input = "test case".split("\\s");
        assertEquals(new TodoCommand(input).isExit(), false);
    }

}
