package bob.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.common.Messages;
import bob.data.task.Tasklist;
import bob.storage.Storage;



public class ExitCommandTest {
    private static final String FILE_PATH = "src/test/java/bob/data/bob.txt";
    private ExitCommand exitCommand;
    private Tasklist tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        exitCommand = new ExitCommand();
        tasks = new Tasklist();
        storage = new Storage(FILE_PATH);
    }

    @Test
    public void execute_emptyTasklistAndStorage_exitSuccessful() {
        assertEquals(exitCommand.execute(tasks, storage), Messages.OUTRO);
    }

    @Test
    public void isExited_noParams_returnsTrue() {
        assertTrue(exitCommand.isExited());
    }
}
