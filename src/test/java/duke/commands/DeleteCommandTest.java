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

public class DeleteCommandTest {

    private Method method;
    private Ui ui;
    private TaskList tasks;

    @BeforeEach
    void init() {
        try {
            method = DeleteCommand.class.getDeclaredMethod("deleteTask", int.class, Ui.class, TaskList.class);
            method.setAccessible(true);
            ui = new Ui();
            tasks = new TaskList();
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for delete (delete 1)")
    public void deleteTask_deletionOfTaskAtIndexOne_success() {
        try {
            tasks.add(new ToDo("hello world"));
            String type = "delete";
            String description = "1";
            Task task = tasks.get(0);
            DeleteCommand command = new DeleteCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, parseInt(description), ui, tasks);
            String expected = "Noted. I've removed this task:" + "\n" + task.toString() + "\n"
                    + "Now you have 0 tasks in the list." + "\n\n";
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for delete (delete 5)")
    public void deleteTask_deletionOfTaskAtIndexFive_success() {
        try {
            for (int i = 0; i < 5; i++) {
                tasks.add(new ToDo("hello world"));
            }
            String type = "delete";
            String description = "5";
            Task task = tasks.get(4);
            DeleteCommand command = new DeleteCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, parseInt(description), ui, tasks);
            String expected = "Noted. I've removed this task:" + "\n" + task.toString() + "\n"
                    + "Now you have 4 tasks in the list." + "\n\n";
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
