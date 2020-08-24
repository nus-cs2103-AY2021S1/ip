package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    class StorageStub extends Storage {


        public StorageStub(String filepath) {
            super(filepath);
        }

        @Override
        public void writeToFile(ArrayList<Task> arrayList) {
            //Do nothing
        }
    }

    class UiStub extends Ui {
        @Override
        public void printAddTask(Task task, int size) {
            //Do nothing
        }
    }

    @Test
    public void testAddCommand() {
        TaskList tasks = new TaskList();
        AddCommand c = new AddCommand(new ToDo("Read book"));
        c.execute(tasks, new UiStub(), new StorageStub(""));
        assertEquals(new ToDo("Read book").toString(),
                tasks.getTasks().get(0).toString());

    }

}
