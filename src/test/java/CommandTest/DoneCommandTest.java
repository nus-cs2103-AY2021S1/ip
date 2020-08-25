package CommandTest;

import Command.DoneCommand;
import Exceptions.WrongIndexError;
import ParserStorageUi.Storage;
import ParserStorageUi.Ui;
import Task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoneCommandTest {

    @Test
    public void DeleteCommandErrorTest() {
        assertThrows(WrongIndexError.class, () -> {
            new DoneCommand("done 4").execute(new TaskList(new Storage("data/duke.txt").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }
}
