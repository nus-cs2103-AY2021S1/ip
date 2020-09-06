package nekochan.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoStorageException;
import nekochan.exceptions.NekoTaskCreationException;
import nekochan.util.Messages;

public class DeadlineTest {

    @Test
    public void createTask_dateOnly_success() {
        Deadline deadline = Deadline.createTask("return book by 20-03-2019");
        assertEquals("[D][\u2718] return book (by: 20 Mar 2019)", deadline.toString());
    }

    @Test
    public void createTask_withoutDate_throwsException() {
        NekoException thrown = assertThrows(NekoTaskCreationException.class, () -> {
            Deadline deadline = Deadline.createTask("Submit homework");
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_DEADLINE_DUE_DATE_ERROR));
    }

    @Test
    public void createTask_withoutDescription_throwsException() {
        NekoException thrown = assertThrows(NekoTaskCreationException.class, () -> {
            Deadline deadline = Deadline.createTask(null);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_DEADLINE_MISSING_ARGUMENT));
    }

    @Test
    public void encode_success() {
        Deadline deadline = Deadline.createTask("Description by 1 Jan 2020 11:59");
        assertEquals("D|N|1 Jan 2020 11:59|Description", deadline.encode());
    }

    @Test
    public void decode_success() {
        Deadline deadline = Deadline.decode("D|N|1 Jan 2020 11:59|Description");
        assertEquals("[D][\u2718] Description (by: 1 Jan 2020 11:59)", deadline.toString());
    }

    @Test
    public void decode_missingCompletion_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Deadline deadline = Deadline.decode("D|1 Jan 2020 11:59|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_missingDescription_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Deadline deadline = Deadline.decode("D|N");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_incorrectCompletion_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Deadline deadline = Deadline.decode("D|X|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_incorrectTaskType_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Deadline deadline = Deadline.decode("E|X|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.DECODE_UNEXPECTED_TYPE_ERROR));
    }

    @Test
    public void isSimilar_similarDeadline_true() {
        Deadline d1 = Deadline.createTask("Description by 1 Jan 2020 11:59");
        Deadline d2 = Deadline.createTask("description by 1 Jan 2020 11:59");
        assertTrue(d1.isSimilar(d2));
    }

    @Test
    public void isSimilar_differentDate_false() {
        Deadline d1 = Deadline.createTask("Description by 1 Jan 2020 11:59");
        Deadline d2 = Deadline.createTask("description by 2 Jan 2020 11:59");
        assertFalse(d1.isSimilar(d2));
    }

    @Test
    public void equals_sameDeadline_true() {
        Deadline d1 = Deadline.createTask("Description by 1 Jan 2020 11:59");
        Deadline d2 = Deadline.createTask("Description by 1 Jan 2020 11:59");
        assertEquals(d1, d2);
    }

    @Test
    public void equals_similarDeadline_false() {
        Deadline d1 = Deadline.createTask("Description by 1 Jan 2020 11:59");
        Deadline d2 = Deadline.createTask("description by 1 Jan 2020 11:59");
        assertNotEquals(d1, d2);
    }
}
