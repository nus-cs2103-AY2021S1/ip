package CommandTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.AddCommand;
import exceptions.NoDateException;
import exceptions.NoTaskException;
import exceptions.WrongDateTimeFormatException;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class AddCommandTest {
    @Test
    public void AddCommandErrorTest() {
        assertThrows(WrongDateTimeFormatException.class, () -> {
            new AddCommand("deadline cs2103 project /by 26-06-2020 ")
                .execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }

    @Test
    public void AddCommandErrorTest2() {
        assertThrows(NoDateException.class, () -> {
            new AddCommand("deadline cs2103 project")
                .execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }

    @Test
    public void AddCommandErrorTest3() {
        assertThrows(NoTaskException.class, () -> {
            new AddCommand("event")
                .execute(new TaskList(new Storage("data").load()), new Ui(), new Storage("data/duke.txt"));
        });
    }
}
