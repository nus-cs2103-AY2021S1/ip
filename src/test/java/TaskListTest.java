import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void getTasksInfoTest() {
        ArrayList<String[]> taskDetails = new ArrayList<>();
        taskDetails.add(new String[]{"T", "1", "Read Book"});
        taskDetails.add(new String[]{"D", "0", "Complete assignment", "2019-02-10"});
        TaskList tasks = new TaskList(taskDetails);

        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("T|1|Read Book");
        expectedResult.add("D|0|Complete assignment|2019-02-10");

        assertEquals(expectedResult, tasks.getTasksInfo());
    }

    @Test
    public void getTasksDescriptionTest() {
        ArrayList<String[]> taskDetails = new ArrayList<>();
        taskDetails.add(new String[]{"T", "1", "Read Book"});
        taskDetails.add(new String[]{"D", "0", "Complete assignment", "2019-02-10"});
        TaskList tasks = new TaskList(taskDetails);

        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("[T][✓] Read Book");
        expectedResult.add("[D][ઝ] Complete assignment (by: Feb 10 2019)");

        assertEquals(expectedResult, tasks.getTasksDescription());
    }
}
