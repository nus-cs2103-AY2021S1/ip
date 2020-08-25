package CommandTest;
import Command.DeleteCommand;
import Exceptions.DukeException;
import Exceptions.MissingSpecifiedDeleteError;
import Exceptions.WrongIndexError;
import ParserStorageUi.*;
import org.junit.jupiter.api.Test;
import Task.TaskList;
import ParserStorageUi.Parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {

    @Test
    public void DeleteCommandErrorTest() {
        assertThrows(WrongIndexError.class, () -> {
            new DeleteCommand("delete 4").execute(new TaskList(new Storage("data/duke.txt").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }

    @Test
    public void DeleteCommandErrorTest2() {
        assertThrows(MissingSpecifiedDeleteError.class, () -> {
            new DeleteCommand("delete").execute(new TaskList(new Storage("data/duke.txt").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }
}
