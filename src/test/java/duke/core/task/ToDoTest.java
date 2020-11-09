package duke.core.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.core.parser.CsvToTask;

class ToDoTest {

    @Test
    void hashCode_equals() {
        Task case1 = new ToDo("test");
        Task case2 = new ToDo("test");
        assertEquals(case1.hashCode(), case2.hashCode());
    }

    @Test
    void hashCode_notEquals() {
        Task case1 = new ToDo(true, "test");
        Task case2 = new ToDo(false, "test");
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new ToDo(true, "test case1");
        case2 = new ToDo(true, "test case2");
        assertNotEquals(case1.hashCode(), case2.hashCode());
    }

    @Test
    void toCsv_fromCsv() {
        try {
            Task task1 = new ToDo("test");
            Task task2 = CsvToTask.TODO.parse(task1.toCsv());
            assertEquals(task1.hashCode(), task2.hashCode());
        } catch (Exception e) {
            // Failed to convert csv to to-do
            fail();

        }
    }

}
