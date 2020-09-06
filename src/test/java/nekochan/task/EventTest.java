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

public class EventTest {

    @Test
    public void createTask_usingDates_success() {
        Event event = Event.createTask("Description from 20/03/2019 to 21/03/2019");
        assertEquals("[E][\u2718] Description (from 20 Mar 2019 to 21 Mar 2019)", event.toString());
    }

    @Test
    public void createTask_usingDuration_success() {
        Event event = Event.createTask("Description from 20/03/2019 12:00 for 3h 30min ");
        assertEquals("[E][\u2718] Description (from 20 Mar 2019 12:00 to 20 Mar 2019 15:30)", event.toString());
    }

    @Test
    public void createTask_allDayDuration_success() {
        Event event = Event.createTask("Description from 20/03/2019 for all day");
        assertEquals("[E][\u2718] Description (on 20 Mar 2019 for all day)", event.toString());
    }

    @Test
    public void createTask_withoutDate_throwsException() {
        NekoException thrown = assertThrows(NekoTaskCreationException.class, () -> {
            Event event = Event.createTask("Submit homework");
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_EVENT_DATETIME_ERROR));
    }

    @Test
    public void createTask_withoutDescription_throwsException() {
        NekoException thrown = assertThrows(NekoTaskCreationException.class, () -> {
            Event event = Event.createTask(null);
        });
        assertTrue(thrown.getMessage().contains(Messages.PARSE_COMMAND_EVENT_MISSING_ARGUMENT));
    }

    @Test
    public void encode_success() {
        Event event = Event.createTask("Description from 1/01/2020 12:00 to 2/01/2020 12:00");
        assertEquals("E|N|1 Jan 2020 12:00|2 Jan 2020 12:00|Description", event.encode());
    }

    @Test
    public void encode_dateOnlyEvent_success() {
        Event event = Event.createTask("Description from 1/01/2020 to 2/01/2020");
        assertEquals("E|N|1 Jan 2020|2 Jan 2020|Description", event.encode());
    }

    @Test
    public void encode_allDayEvent_success() {
        Event event = Event.createTask("Description from 1/01/2020 for all day");
        assertEquals("E|N|1 Jan 2020|1 Jan 2020|Description", event.encode());
    }

    @Test
    public void decode_success() {
        Event event = Event.decode("E|N|1 Jan 2020 12:00|2 Jan 2020 12:00|Description");
        assertEquals("[E][\u2718] Description (from 1 Jan 2020 12:00 to 2 Jan 2020 12:00)", event.toString());
    }

    @Test
    public void decode_dateOnlyEvent_success() {
        Event event = Event.decode("E|N|1 Jan 2020|2 Jan 2020|Description");
        assertEquals("[E][\u2718] Description (from 1 Jan 2020 to 2 Jan 2020)", event.toString());
    }

    @Test
    public void decode_allDayEvent_success() {
        Event event = Event.decode("E|N|1 Jan 2020|1 Jan 2020|Description");
        assertEquals("[E][\u2718] Description (on 1 Jan 2020 for all day)", event.toString());
    }

    @Test
    public void decode_missingCompletion_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Event event = Event.decode("E|1 Jan 2020|2 Jan 2020|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_missingDescription_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Event event = Event.decode("E|N|1 Jan 2020|2 Jan 2020");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_incorrectCompletion_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Event event = Event.decode("E|X|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.STORAGE_ERROR_CORRUPT));
    }

    @Test
    public void decode_incorrectTaskType_throwsException() {
        NekoException thrown = assertThrows(NekoStorageException.class, () -> {
            Event event = Event.decode("D|X|Description");
        });
        assertTrue(thrown.getMessage().contains(Messages.DECODE_UNEXPECTED_TYPE_ERROR));
    }

    @Test
    public void isSimilar_similarDeadline_true() {
        Event e1 = Event.createTask("Description from 20/03/2019 to 21/03/2019");
        Event e2 = Event.createTask("description from 20/03/2019 to 21/03/2019");
        assertTrue(e1.isSimilar(e2));
    }

    @Test
    public void isSimilar_differentDate_false() {
        Event e1 = Event.createTask("Description from 20/03/2019 to 22/03/2019");
        Event e2 = Event.createTask("description from 20/03/2019 to 21/03/2019");
        assertFalse(e1.isSimilar(e2));
    }

    @Test
    public void equals_sameDeadline_true() {
        Event e1 = Event.createTask("Description from 20/03/2019 to 21/03/2019");
        Event e2 = Event.createTask("Description from 20/03/2019 to 21/03/2019");
        assertEquals(e1, e2);
    }

    @Test
    public void equals_similarDeadline_false() {
        Event e1 = Event.createTask("Description from 20/03/2019 to 21/03/2019");
        Event e2 = Event.createTask("description from 20/03/2019 to 21/03/2019");
        assertNotEquals(e1, e2);
    }
}
