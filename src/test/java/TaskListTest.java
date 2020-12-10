import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testGetNumTask() {
        assertEquals(0, new TaskList().getNumTasks());
        List<Task> listOfTask = new ArrayList<>();
        listOfTask.add(new Todo("play", Optional.empty()));
        listOfTask.add(new Todo("study", Optional.empty()));
        assertEquals(2, new TaskList(listOfTask).getNumTasks());
    }

    @Test
    public void testGetSameDateTask() {
        class SimpleTask extends Task {
            private LocalDate date = LocalDate.parse("2020-08-08");
            SimpleTask() {
                super("test", Optional.empty());
            }
            @Override
            public Optional<LocalDate> getDate() {
                return Optional.of(date);
            }
        }
        List<Task> listOfTask = new ArrayList<>();
        listOfTask.add(new SimpleTask());
        assertEquals(listOfTask, new TaskList(listOfTask).getSameDateTasks("2020-08-08"));
        assertEquals(new ArrayList<Task>(), new TaskList(listOfTask).getSameDateTasks("2020-08-09"));
    }

    @Test
    public void testGetAllTasks() {
        class SimpleTask extends Task {
            private LocalDate date = LocalDate.parse("2020-08-08");
            SimpleTask() {
                super("test", Optional.empty());
            }
            @Override
            public Optional<LocalDate> getDate() {
                return Optional.of(date);
            }
        }
        List<Task> listOfTask = new ArrayList<>();
        listOfTask.add(new SimpleTask());
        listOfTask.add(new SimpleTask());
        assertEquals(listOfTask, new TaskList(new ArrayList<Task>(listOfTask)).getAllTasks());
        assertEquals(new ArrayList<Task>(), new TaskList().getAllTasks());
    }
}
