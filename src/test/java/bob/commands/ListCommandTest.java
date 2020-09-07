package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.common.MsgGenerator;
import bob.data.task.Tasklist;
import bob.storage.Storage;


public class ListCommandTest {
    private static final String FILE_PATH = "src/test/java/bob/data/bob.txt";
    private ListCommand listCommand;
    private Tasklist tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        listCommand = new ListCommand();
        tasks = new Tasklist();
        storage = new Storage(FILE_PATH);
    }

    @Test
    public void execute_emptyTasklistAndStorage_listSuccessful() {
        assertEquals(listCommand.execute(tasks, storage), MsgGenerator.generateListMessage(tasks));
    }

    @Test
    public void isExit_noParams_returnsFalse() {
        assertTrue(!listCommand.isExited());
    }
}
