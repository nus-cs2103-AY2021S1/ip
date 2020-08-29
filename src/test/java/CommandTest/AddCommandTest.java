package CommandTest;
import Command.AddCommand;
import Exceptions.MissingSpecifiedDeleteError;
import Exceptions.NoDateException;
import Exceptions.NoTaskException;
import Exceptions.WrongDateTimeFormatException;
import ParserStorageUi.Storage;
import ParserStorageUi.Ui;
import Task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    @Test
    public void AddCommandErrorTest() {
        assertThrows(WrongDateTimeFormatException.class, () -> {
            new AddCommand("deadline cs2103 project /by 26-06-2020 ").execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }

    @Test
    public void AddCommandErrorTest2() {
        assertThrows(NoDateException.class, () -> {
            new AddCommand("deadline cs2103 project").execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }

    @Test
    public void AddCommandErrorTest3() {
        assertThrows(NoTaskException.class, () -> {
            new AddCommand("event").execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }
}
