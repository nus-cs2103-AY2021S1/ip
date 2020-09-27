package botbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private static final LocalDateTime DATE_WITH_TIME = LocalDateTime.of(2021, 3, 17, 3, 45);
    private static final LocalDateTime DATE_WITHOUT_TIME = LocalDateTime.of(2020, 5, 5, 3, 14, 15, 926535898);

    @Test
    public void getType() {
        assertEquals('D', new Deadline("test1", DATE_WITH_TIME).getType());
        assertEquals('D', new Deadline("test2", TaskStatus.DONE, DATE_WITHOUT_TIME.toString()).getType());
        assertEquals('E', new Event("sample1", DATE_WITHOUT_TIME).getType());
        assertEquals('E', new Event("sample2", TaskStatus.NOT_DONE, DATE_WITH_TIME.toString()).getType());
        assertEquals('T', new Todo("test3").getType());
        assertEquals('T', new Todo("test4", TaskStatus.DONE).getType());
    }

    @Test
    public void getDescription() {
        assertEquals("test1", new Deadline("test1", DATE_WITH_TIME).getDescription());
        assertEquals("test2", new Deadline("test2", TaskStatus.DONE, DATE_WITHOUT_TIME.toString())
                .getDescription());
        assertEquals("sample1", new Event("sample1", DATE_WITHOUT_TIME).getDescription());
        assertEquals("sample2", new Event("sample2", TaskStatus.NOT_DONE, DATE_WITH_TIME.toString())
                .getDescription());
        assertEquals("test3", new Todo("test3").getDescription());
        assertEquals("test4", new Todo("test4", TaskStatus.DONE).getDescription());
    }

    @Test
    public void getStatus() {
        assertEquals("0", new Deadline("test1", DATE_WITH_TIME).getStatus());
        assertEquals("1", new Deadline("test2", TaskStatus.DONE, DATE_WITHOUT_TIME.toString())
                .getStatus());
        assertEquals("0", new Event("sample1", DATE_WITHOUT_TIME).getStatus());
        assertEquals("0", new Event("sample2", TaskStatus.NOT_DONE, DATE_WITH_TIME.toString())
                .getStatus());
        assertEquals("0", new Todo("test3").getStatus());
        assertEquals("1", new Todo("test4", TaskStatus.DONE).getStatus());
    }

    @Test
    public void markAsDone() {
        Task deadline = new Deadline("test1", TaskStatus.NOT_DONE, DATE_WITHOUT_TIME.toString());
        assertEquals("0", deadline.getStatus());
        deadline.markAsDone();
        assertEquals("1", deadline.getStatus());

        Task todo = new Todo("test2");
        assertEquals("0", todo.getStatus());
        todo.markAsDone();
        assertEquals("1", todo.getStatus());
    }

    @Test
    public void getAt_localDateTime() {
        assertEquals(LocalDateTime.of(2020, 5, 5, 3, 14, 15, 926535898),
                new Event("sample1", DATE_WITHOUT_TIME).getAt());
        assertEquals(LocalDateTime.of(2021, 3, 17, 3, 45),
                new Event("sample2", TaskStatus.NOT_DONE, DATE_WITH_TIME.toString()).getAt());
    }

    @Test
    public void getAt_null() {
        assertNull(new Deadline("test1", DATE_WITH_TIME).getAt());
        assertNull(new Deadline("test2", TaskStatus.DONE, DATE_WITHOUT_TIME.toString()).getAt());
        assertNull(new Todo("test3").getAt());
        assertNull(new Todo("test4", TaskStatus.DONE).getAt());
    }

    @Test
    public void getBy_localDateTime() {
        assertEquals(LocalDateTime.of(2021, 3, 17, 3, 45),
                new Deadline("test1", DATE_WITH_TIME).getBy());
        assertEquals(LocalDateTime.of(2020, 5, 5, 3, 14, 15, 926535898),
                new Deadline("test2", TaskStatus.DONE, DATE_WITHOUT_TIME.toString()).getBy());
    }

    @Test
    public void getBy_null() {
        assertNull(new Event("sample1", DATE_WITHOUT_TIME).getBy());
        assertNull(new Event("sample2", TaskStatus.NOT_DONE, DATE_WITH_TIME.toString()).getBy());
        assertNull(new Todo("test3").getBy());
        assertNull(new Todo("test4", TaskStatus.DONE).getBy());
    }

    @Test
    public void setDescription() {
        Task deadline = new Deadline("test1", TaskStatus.NOT_DONE, DATE_WITHOUT_TIME.toString());
        assertEquals("test1", deadline.getDescription());
        deadline.setDescription("sample1");
        assertEquals("sample1", deadline.getDescription());

        Task todo = new Todo("test2");
        assertEquals("test2", todo.getDescription());
        todo.setDescription("sample2");
        assertEquals("sample2", todo.getDescription());
    }

    @Test
    public void setAt() {
        Task event = new Event("test1", TaskStatus.NOT_DONE, DATE_WITHOUT_TIME.toString());
        assertEquals(DATE_WITHOUT_TIME, event.getAt());
        event.setAt(DATE_WITH_TIME);
        assertEquals(DATE_WITH_TIME, event.getAt());
    }

    @Test
    public void setBy() {
        Task deadline = new Deadline("test1", TaskStatus.NOT_DONE, DATE_WITHOUT_TIME.toString());
        assertEquals(DATE_WITHOUT_TIME, deadline.getBy());
        deadline.setBy(DATE_WITH_TIME);
        assertEquals(DATE_WITH_TIME, deadline.getBy());
    }
}
