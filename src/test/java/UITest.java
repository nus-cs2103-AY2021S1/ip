import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class UITest {

    @Test
    public void testByeMessage() {
        UI ui = new UI();
        assertEquals(ui.printByeMessage(), "Bye. Hope to see you again soon!");
    }

    @Test
    public void testPrintMarkAsDone() {
        ToDo toDoTask = new ToDo("run");
        toDoTask.markAsDone();
        assertEquals(UI.printMarkAsDone(toDoTask), "Nice! I've marked this task as done:\n[T][\u2713] run\n");
    }

    @Test
    public void testKeywordTasks() throws DukeNoMatchesExcpetion {
        ArrayList<Task> tasks = new ArrayList<>();
        UI ui = new UI();
        ToDo toDoEvent = new ToDo("run");
        tasks.add(toDoEvent);
        String actual = "Here are the matching tasks in your list:\n" + "1.[T][\u2718] run\n";
        assertEquals(actual, ui.printKeywordTasks("run", tasks));
    }

    @Test
    public void testPrintTag() {
        ArrayList<Task> tasks = new ArrayList<>();
        UI ui = new UI();
        ToDo toDoEvent = new ToDo("run");
        toDoEvent.makeTag("lol");
        tasks.add(toDoEvent);
        String actual = "Nice! I've tagged this task with: " + "#lol\n" + "[T][\u2718] run #lol";
        assertEquals(actual , ui.printTag("tag 1 lol", tasks));


    }



}