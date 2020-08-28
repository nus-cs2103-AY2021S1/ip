package duke.task;

import duke.exceptions.DukeException;
import duke.exceptions.DukeStorageException;
import duke.exceptions.DukeTaskCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {

    @Test
    public void createTask_dateOnly_success() {
        ToDo todo = ToDo.createTask("return book");
        assertEquals("[T][\u2718] return book", todo.toString());
    }

    @Test
    public void createTask_withoutDescription_throwsException() {
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
        assertEquals("[T][\u2718] Description", todo.toString());
    }

    @Test
    public void decode_missingCompletion_throwsException() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|Description");
        });
        assertTrue(thrown.getMessage().contains("There are some holes in my memory..."));
    }

    @Test
    public void decode_missingDescription_throwsException() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|N");
        });
        assertTrue(thrown.getMessage().contains("There are some holes in my memory..."));
    }

    @Test
    public void decode_incorrectCompletion_throwsException() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|X|Description");
        });
        assertTrue(thrown.getMessage().contains("There are some holes in my memory..."));
    }

    @Test
    public void decode_incorrectTaskType_throwsException() {
        DukeException thrown = assertThrows(DukeStorageException.class, () -> {
            ToDo todo = ToDo.decode("E|X|Description");
        });
        assertTrue(thrown.getMessage().contains("Something doesn't seem right..."));
    }
}
