package command;

import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SaveCommandTest {

    List<Task> taskList = new ArrayList<>(1);

    @Test
    void isModifying_false() {
        assertFalse(new SaveCommand(taskList, "save.txt").hasUndo());
    }

    @Test
    void isExit_false() {
        assertFalse(new SaveCommand(taskList, "save.txt").isExit());
    }

    @Test
    void execute() {
        // Implemented in StorageTest.java
    }
}