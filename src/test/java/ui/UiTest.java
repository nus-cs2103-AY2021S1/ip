package ui;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void prependIndent_singleLine_success() {
        Ui ui = new Ui();
        String prepended = ui.prependIndent("Test", 1);
        assertEquals(" Test\n", prepended);
    }

    @Test
    public void prependIndent_multiLine_success() {
        Ui ui = new Ui();
        String prepended = ui.prependIndent("Test\nTest", 1);
        assertEquals(" Test\n Test\n", prepended);
    }

    @Test
    public void prependIndent_alreadyIndentedSingleLine_success() {
        Ui ui = new Ui();
        String prepended = ui.prependIndent("  Test", 3);
        assertEquals("     Test\n", prepended);
    }

    @Test
    public void prependIndent_alreadyIndentedMultiLine_success() {
        Ui ui = new Ui();
        String prepended = ui.prependIndent("  Test\n Test", 3);
        assertEquals("     Test\n    Test\n", prepended);
    }

    @Test
    public void prependIndent_zeroIndent_success() {
        Ui ui = new Ui();
        String prepended = ui.prependIndent("  Test", 0);
        assertEquals("  Test\n", prepended);
    }

    @Test
    public void prependIndent_negativeIndent_success() {
        Ui ui = new Ui();
        String prepended = ui.prependIndent("Test\nTest", -1);
        assertEquals("Test\nTest\n", prepended);
    }

}
