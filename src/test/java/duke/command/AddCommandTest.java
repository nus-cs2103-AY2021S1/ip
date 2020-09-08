package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;


public class AddCommandTest {

    class StorageStub extends Storage {


        public StorageStub(String filepath) {
            super(filepath, "");
        }

        @Override
        public void writeToMain(ArrayList<Task> arrayList) {
            //Do nothing
        }
    }

    class UiStub extends Ui {

    }

    @Test
    public void testAddCommand() {
        TaskList tasks = new TaskList();
        TaskList archives = new TaskList();
        AddCommand c = new AddCommand(new ToDo("Read book"));
        c.execute(tasks, archives, new UiStub(), new StorageStub(""));
        assertEquals(new ToDo("Read book").toString(),
                tasks.getTasks().get(0).toString());

    }

}
