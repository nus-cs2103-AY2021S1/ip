package duke.command;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

public class MacroTest {
    private final Macro testMacro;
    public MacroTest() {
        this.testMacro = Macro.newMacro("et a; event \\$ --at \\a; todo attend \\$ at \\a");
    }

    @Test
    void testMacro_createdWithCorrectOptions() {
        assertTrue(testMacro.getOptions().hasOption("a"));
    }

    @Test
    void testMacro_getsCreatedCorrectly() throws ParseException {
        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(testMacro.getOptions(), new String[]{"test", "-a", "2020-11-11"});
        assertArrayEquals(new String[]{"event test --at 2020-11-11", "todo attend test at 2020-11-11"},
            testMacro.substituteAll(cmd));
    }
}
