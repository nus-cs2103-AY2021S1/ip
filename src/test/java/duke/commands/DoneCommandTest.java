package duke.commands;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class DoneCommandTest {

    private Method method;
    private Ui ui;
    private TaskList tasks;

    @BeforeEach
    void init() {
        try {
            method = DoneCommand.class.getDeclaredMethod("mark", int.class, Ui.class, TaskList.class);
            method.setAccessible(true);
            ui = new Ui();
            tasks = new TaskList();
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for done (done 1)")
    public void mark_markTaskAtIndexOne_success() {
        try {
            tasks.add(new ToDo("hello world"));
            String type = "done";
            String description = "1";
            Task task = tasks.get(0);
            DoneCommand command = new DoneCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, parseInt(description), ui, tasks);
            String expected = "Nice! I've marked this task as done:" + "\n" + task.toString() + "\n";
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for done (done 5)")
    public void mark_markTaskAtIndexFive_success() {
        try {
            for (int i = 0; i < 5; i++) {
                tasks.add(new ToDo("hello world"));
            }
            String type = "done";
            String description = "5";
            Task task = tasks.get(4);
            DoneCommand command = new DoneCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, parseInt(description), ui, tasks);
            String expected = "Nice! I've marked this task as done:" + "\n" + task.toString() + "\n";
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
