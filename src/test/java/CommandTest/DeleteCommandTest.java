package CommandTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.DeleteCommand;
import exceptions.MissingSpecifiedDeleteError;
import exceptions.WrongIndexError;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class DeleteCommandTest {

    @Test
    public void DeleteCommandErrorTest() {
        assertThrows(WrongIndexError.class, () -> {
            new DeleteCommand("delete 4")
                .execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }

    @Test
    public void DeleteCommandErrorTest2() {
        assertThrows(MissingSpecifiedDeleteError.class, () -> {
            new DeleteCommand("delete")
                .execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }
}
