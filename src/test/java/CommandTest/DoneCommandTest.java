package CommandTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.DoneCommand;
import exceptions.WrongIndexError;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class DoneCommandTest {

    @Test
    public void DeleteCommandErrorTest() {
        assertThrows(WrongIndexError.class, () -> {
            new DoneCommand("done 4")
                .execute(new TaskList(new Storage("data/").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }
}
