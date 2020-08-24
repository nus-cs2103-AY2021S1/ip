package seedu.duke;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void UiCreation_true_Success(){
        assertEquals(true, new Ui(new Parser(new TaskList(new ArrayList<Task>()))).getContinue());
    }
}
