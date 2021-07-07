package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private static final String TODOREAD = "todo read";
    private static final String EVENTREAD = "event read /at 2am";
    private static final String DEADLINEREAD = "deadline read /by 2020-09-19 02:00";

    public static TaskList setUpTaskList() {
        TaskList tl = new TaskList();
        try {
            Task todoRead = Parser.parseTask(TODOREAD);
            Task eventRead = Parser.parseTask(EVENTREAD);
            Task deadlineRead = Parser.parseTask(DEADLINEREAD);

            tl.addTask(todoRead);
            tl.addTask(eventRead);
            tl.addTask(deadlineRead);
        } catch (Exception e) {
            System.out.println(e);
        }
        return tl;
    }

    @Test
    public void initTest() {
        TaskList tl = new TaskList();
        assertEquals(tl.getSize(), 0);
    }

    @Test
    public void summarizeTest() {
        TaskList tl = setUpTaskList();
        String run = tl.summarize();

        StringBuilder expected = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tl.getSize(); i++) {
            expected.append(String.format("%d.%s\n", i + 1, tl.getTask(i).toString()));
        }
        assertEquals(run, expected.toString());
    }
}
