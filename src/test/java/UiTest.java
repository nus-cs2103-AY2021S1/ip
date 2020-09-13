import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.task.Deadline;
import main.java.com.jacob.duke.task.Event;
import main.java.com.jacob.duke.task.Task;
import main.java.com.jacob.duke.task.Todo;

public class UiTest {

    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    @Test
    public void showDone_omo_success() {
        String result = new Ui().showDone("Omo");
        assertEquals(" Nice! I've marked this task as done: \nOmo", result);
    }

    @Test
    public void showDone_kek_success() {
        String result = new Ui().showDone("Kek");
        assertEquals(" Nice! I've marked this task as done: \nKek", result);
    }

    @Test
    public void sayBye_null_success() {
        String result = new Ui().sayBye();
        assertEquals(" Bye. Hope to see you again soon.", result);
    }

    @Test
    public void showNewTaskAddedToEmptyList_dummyTask_success() {
        Task task = new Task("Dummy Task");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        String result = new Ui().showNewTaskAdded("Dummy Task", taskList);
        assertEquals(" Got it. I've added this task: \n   Dummy Task\n Now you have 1 tasks in the list.\n",
                result);
    }

    @Test
    public void showTaskDeleted_dummyTask_success() {
        List<Task> taskList = new ArrayList<>();
        String result = new Ui().showTaskDeleted("Dummy Task", taskList);
        assertEquals(" Noted. I've removed this task:\n    Dummy Task\n Now you have 0 tasks in the list.\n",
                result);
    }

    @Test
    public void showFullList_noTasks_success() {
        List<Task> taskList = new ArrayList<>();
        String result = new Ui().showFullList(taskList);
        assertEquals(" Here are the tasks in your list:\n",
                result);
    }
    @Test
    public void showFilteredDateTimeList_checkDeadline_success() {
        Deadline deadline = new Deadline("Dummy Deadline", "2019-10-15 1800");
        List<Task> taskList = new ArrayList<>();
        taskList.add(deadline);
        taskList.add(new Todo("funny"));
        String result;
        try {
            String s = "Here are the tasks in your filtered list:  1.   [D][X] Dummy Deadline Oct 15 2019 6PM";
            s = s.replaceAll("\n", "").replaceAll("\r", "");
            result = new Ui().showFilteredDateTimeList("list-due 2019-10-15 1800", taskList);
            String cleanOutput = result.replaceAll("\n", "")
                    .replaceAll("\r", "");
            assertEquals(s, cleanOutput.trim());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }


    }
    @Test
    public void showFilteredDateTimeList_checkEvent_success() {
        Event event = new Event("Dummy Event", "2019-10-15 1800");
        List<Task> taskList = new ArrayList<>();
        taskList.add(event);
        taskList.add(new Todo("funny"));
        try {
            String result = new Ui().showFilteredDateTimeList("list-due 2019-10-15 1800", taskList);
            String s = "Here are the tasks in your filtered list:  1.   [E][X] Dummy Event Oct 15 2019 6PM";
            s = s.replaceAll("\n", "").replaceAll("\r", "");
            final String cleanOutput = result.replaceAll("\n", "")
                    .replaceAll("\r", "");
            assertEquals(s, cleanOutput.trim());
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
}
