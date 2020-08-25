package duke.task;

import duke.exceptions.DukeException;
import duke.exceptions.DukeStorageException;
import duke.exceptions.DukeTaskCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void createTask_dateOnly_success() {
        ToDo todo = ToDo.createTask("return book");
        assertEquals("[T][✘] return book", todo.toString());
    }

    @Test
    public void createTask_withoutDescription_failure() {
        DukeException thrown = assertThrows(DukeTaskCreationException.class, () -> {
            ToDo todo = ToDo.createTask(null);
        });
        assertTrue(thrown.getMessage().contains("I need something to work with."));
    }

    @Test
    public void encode_success() {
        ToDo todo = ToDo.createTask("Description");
        assertEquals("T|N|Description", todo.encode());
    }

    @Test
    public void decode_success() {
        ToDo todo = ToDo.decode("T|N|Description");
        assertEquals("[T][✘] Description", todo.toString());
    }

    @Test
    public void decode_missingCompletion_failure() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|Description");
        });
        assertTrue(thrown.getMessage().contains("There are some holes in my memory..."));
    }

    @Test
    public void decode_missingDescription_failure() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|N");
        });
        assertTrue(thrown.getMessage().contains("There are some holes in my memory..."));
    }

    @Test
    public void decode_incorrectCompletion_failure() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|X|Description");
        });
        assertTrue(thrown.getMessage().contains("There are some holes in my memory..."));
    }

    @Test
    public void decode_incorrectTaskType_failure() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("E|X|Description");
        });
        assertTrue(thrown.getMessage().contains("Something doesn't seem right..."));
    }
}
