package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class UiTest {

    /**
     * tests for error message
     */
    @Test
    public void exceptionTest() {
        assertEquals(Ui.DukeException.invalid("dshuadhoas").getMessage(),
                new Ui.DukeException("Sorry, 'dshuadhoas' is not a recognised order.").getMessage());
    }
}
