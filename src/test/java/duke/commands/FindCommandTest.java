package duke.commands;

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

public class FindCommandTest {

    private Method method;
    private Ui ui;
    private TaskList tasks;

    @BeforeEach
    void init() {
        try {
            method = FindCommand.class.getDeclaredMethod("findTasks", TaskList.class, String.class, Ui.class);
            method.setAccessible(true);
            ui = new Ui();
            tasks = new TaskList();
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for find (find duke)")
    public void findTasks_findTasksThatContainsDuke_success() {
        try {
            tasks.add(new ToDo("hello world"));
            String type = "done";
            String description = "duke";
            FindCommand command = new FindCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, tasks, description, ui);
            String expected = "No available task matches duke" + "\n\n";
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for find (find 4)")
    public void findTasks_findTasksThatContainsNumberFour_success() {
        try {
            for (int i = 0; i < 5; i++) {
                tasks.add(new ToDo("hello world " + i));
            }
            String type = "done";
            String description = "4";
            Task task = tasks.get(4);
            FindCommand command = new FindCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, tasks, description, ui);
            String expected = "Here are the matching tasks in your list:" + "\n" + task.toString() + "\n\n";
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
