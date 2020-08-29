import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void taskListTest() {
        duke.TaskList taskList = duke.TaskList.startList();
        assertEquals("Got it. I've added this task:\n" +
                        "\t[✘] eat food\n" +
                        "\tNow you have 1 tasks in the list.",
                taskList.addToList(new duke.Task("eat food")));
        try {
            assertEquals("Noted. I've removed this task:\n" +
                            "\t[✘] eat food\n" +
                            "\tNow you have 0 tasks in the list.",
                    taskList.remove(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void parserTest() {
        try {
            String[] arr = duke.Parser.parseInput("todo eat food");
            assertEquals("todo",
                    arr[0]);
            assertEquals("eat food",
                    arr[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
