package emily.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class EmilyTest {
    private Emily bot = new Emily();

    @Test
    void receiveCommandLine_validInput_successfullyAddTask() {

        String testLine = "todo english";
        String actualOutput = bot.receiveCommandLine(testline);
        String expectedOutput = "Got it! I have added this task: [T][\u2718] english";

        assertTrue(actualOutput.contains(expectedOutput));
    }
}
