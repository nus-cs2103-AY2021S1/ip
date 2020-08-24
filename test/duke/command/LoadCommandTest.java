package duke.command;

import org.junit.jupiter.api.Test;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class LoadCommandTest {

    List<Task> taskList = new ArrayList<>(1);

    @Test
    void isModifying_false() {
        assertFalse(new LoadCommand(taskList, "save.txt").hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new LoadCommand(taskList, "save.txt").isExit());
    }

    @Test
    void execute() {
        // Implemented in StorageTest.java
    }
}