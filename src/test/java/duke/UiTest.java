package duke;

import org.junit.jupiter.api.Test;

public class UiTest {
    Ui ui = new Ui();
    @Test
    public void testWelcome(){
        ui.welcome();
    }

}
