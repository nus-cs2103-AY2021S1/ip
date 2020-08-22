import org.junit.Assert;
import org.junit.Test;


public class DukeTest {

    // to test correctness of task output
    @Test
    public void TodoTest() {
        ToDo todo = new ToDo("return book");
        Assert.assertEquals(todo.toString(), "[T][\u2718] return book");
    }

    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("return book", "2020-08-22");
        Assert.assertEquals(deadline.toString(), "[D][\u2718] return book (by: Aug 22 2020)");
    }

    @Test
    public void eventTest() {
        Event event = new Event("return book", "2020-08-22 14:00-16:00");
        Assert.assertEquals(event.toString(), "[E][\u2718] return book (at: Aug 22 2020 14:00 - 16:00)");
    }
}
