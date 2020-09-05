package nekochan.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoStorageException;
import nekochan.exceptions.NekoTaskCreationException;
import nekochan.util.Messages;

public class ToDoTest {

    @Test
    public void createTask_dateOnly_success() {
        ToDo todo = ToDo.createTask("return book");
        assertEquals("[T][\u2718] return book", todo.toString());
    }

    @Test
    public void createTask_withoutDescription_throwsException() {
        NekoException thrown = assertThrows(NekoTaskCreationException.class, () -> {
            ToDo todo = ToDo.createTask(null);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_TODO_MISSING_ARGUMENT));
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
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_missingDescription_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|N");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_incorrectCompletion_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            ToDo todo = ToDo.decode("T|X|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_incorrectTaskType_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            ToDo todo = ToDo.decode("E|X|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.DECODE_UNEXPECTED_TYPE_ERROR));
    }
}
