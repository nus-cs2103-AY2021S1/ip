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

public class ReminderCommandTest {

    private Method method;
    private Ui ui;
    private TaskList tasks;

    @BeforeEach
    void init() {
        try {
            method = ReminderCommand.class.getDeclaredMethod("setReminder", String.class, TaskList.class);
            method.setAccessible(true);
            ui = new Ui();
            tasks = new TaskList();
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for remind (remind 1 y)")
    public void setReminder_activateReminderForTaskAtIndexOne_success() {
        try {
            tasks.add(new ToDo("hello world"));
            String type = "remind";
            String description = "1 y";
            Task task = tasks.get(0);
            ReminderCommand command = new ReminderCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, description, tasks);
            String expected = String.format("The reminder of this task %s, has been activated",
                    task.getDescription());
            assertEquals(expected, dukeReply);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for remind (remind 4 n)")
    public void setReminder_deactivateReminderForTaskAtIndexFour_success() {
        try {
            for (int i = 0; i < 5; i++) {
                tasks.add(new ToDo("hello world " + i));
            }
            String type = "remind";
            String description = "4 n";
            Task task = tasks.get(3);
            ReminderCommand command = new ReminderCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, description, tasks);
            String expected = String.format("The reminder of this task %s, has been deactivated",
                    task.getDescription());
            assertEquals(expected, dukeReply);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
