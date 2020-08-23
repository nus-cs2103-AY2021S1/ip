import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testGetNumTask(){
        assertEquals(0, new TaskList().getNumTasks());
        List<Task> listOfTask = new ArrayList<>();
        listOfTask.add(new Todo("play"));
        listOfTask.add(new Todo("study"));
        assertEquals(2, new TaskList(listOfTask).getNumTasks());
    }

    public void testGetSameDateTask(){
        class SimpleTask extends Task {
            LocalDate date = LocalDate.parse("2020-08-08");
            SimpleTask(){
                super("test");
            }
            @Override
            public Optional<LocalDate> getDate(){
                return Optional.of(date);
            }
        }
        List<Task> listOfTask = new ArrayList<>();
        listOfTask.add(new SimpleTask());
        assertEquals(listOfTask, new TaskList(listOfTask).getSameDateTasks("2020-08-08"));
        assertEquals(new ArrayList<Task>(), new TaskList(listOfTask).getSameDateTasks("2020-08-09"));
    }
}
