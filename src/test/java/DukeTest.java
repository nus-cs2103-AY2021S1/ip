import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    ListOfItems listOfItems = new ListOfItems();
    HandleFile handleFile = new HandleFile(listOfItems);
    Parser parser = new Parser(listOfItems, handleFile);

    @Test
    void testTodo() {
        parser.run("todo a");
        String result = listOfItems.list.get(0).toString();
        assertEquals("[T][✘] a", result);
        parser.run("delete 1");
    }

    @Test
    void testDeadlineDate() {
        parser.run("deadline hw /by 11/11/2020");
        String result = listOfItems.list.get(0).toString();
        assertEquals("[D][✘] hw (by 11 Nov 2020)", result);
        parser.run("delete 1");
    }

    @Test
    void testDeadlineDateTime() {
        parser.run("deadline hw /by 11/11/2020 2359");
        String result = listOfItems.list.get(0).toString();
        assertEquals("[D][✘] hw (by 11 Nov 2020, 11:59PM)", result);
        parser.run("delete 1");
    }

    @Test
    void testEvent() {
        parser.run("event meeting /from 12/12/2020 1000-1200");
        String result = listOfItems.list.get(0).toString();
        assertEquals("[E][✘] meeting (from 12 Dec 2020, 10:00AM - 12:00PM)", result);
        parser.run("delete 1");
    }

    @Test
    void testDone() {
        parser.run("todo a");
        parser.run("done 1");
        String result = listOfItems.list.get(0).toString();
        assertEquals("[T][✓] a", result);
        parser.run("delete 1");
    }
}
