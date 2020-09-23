package duke.commands;

import static duke.util.Keyword.KEYWORD_CROSS;
import static duke.util.Keyword.KEYWORD_LIST_EMPTY_MSG;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class ListCommandTest {

    private Method method;
    private Ui ui;
    private TaskList tasks;

    @BeforeEach
    void init() {
        try {
            method = ListCommand.class.getDeclaredMethod("showListTasks", TaskList.class, Ui.class);
            method.setAccessible(true);
            ui = new Ui();
            tasks = new TaskList();
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Calling list on empty list")
    public void showListTasks_emptyList_success() {
        try {
            ListCommand command = new ListCommand(new String[]{"list"});
            String dukeReply = (String) method.invoke(command, tasks, ui);
            String expected = KEYWORD_LIST_EMPTY_MSG + "\n";
            assertEquals(dukeReply, expected);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Calling list on non-empty list")
    public void showListTasks_nonEmptyList_success() {
        try {
            // generate 5 task into the tasklist
            for (int i = 0; i < 5; i++) {
                tasks.add(new ToDo("hello world" + i));
            }
            ListCommand command = new ListCommand(new String[]{"list"});
            String cross = "[" + KEYWORD_CROSS + "]";
            String dukeReply = (String) method.invoke(command, tasks, ui);
            String expected = "Here are the tasks in your list:\n"
                + "1. [T]" + cross + " hello world0\n"
                + "2. [T]" + cross + " hello world1\n"
                + "3. [T]" + cross + " hello world2\n"
                + "4. [T]" + cross + " hello world3\n"
                + "5. [T]" + cross + " hello world4\n\n";
            assertEquals(dukeReply, expected);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
