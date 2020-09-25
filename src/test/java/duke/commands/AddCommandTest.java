package duke.commands;

import static duke.util.DateFormatter.formatDateTime;
import static duke.util.Keyword.KEYWORD_DEADLINE_FORMAT;
import static duke.util.Keyword.KEYWORD_EVENT_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exception.InvalidFormatDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

// Reused from https://www.youtube.com/watch?v=HjvEkexsrWk&ab_channel=SivaReddy
public class AddCommandTest {

    private Method method;
    private Ui ui;
    private TaskList tasks;

    @BeforeEach
    void init() {
        try {
            method = AddCommand.class.getDeclaredMethod("addTask", String.class, String.class, Ui.class,
                    TaskList.class);
            method.setAccessible(true);
            ui = new Ui();
            tasks = new TaskList();
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for add task (event)")
    public void addTask_addingEvent_success() {
        try {
            String type = "event";
            String descriptionAndDateTime = "project /at 2020-12-11";
            String[] descriptionAndDateTimeArray = descriptionAndDateTime.split(KEYWORD_EVENT_FORMAT, 2);
            Task task = new Event(descriptionAndDateTimeArray[0], formatDateTime(descriptionAndDateTimeArray[1]));
            String expected = "Got it. I've added this task:" + "\n" + task.toString() + "\n"
                    + "Now you have 1 tasks in the list." + "\n\n";
            AddCommand command = new AddCommand(new String[]{type, descriptionAndDateTime});
            String dukeReply = (String) method.invoke(command, type, descriptionAndDateTime, ui, tasks);
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException | InvalidFormatDateException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for add task (deadline)")
    public void addTask_addingDeadline_success() {
        try {
            String type = "deadline";
            String descriptionAndDateTime = "project /by 2020-12-11";
            String[] descriptionAndDateTimeArray = descriptionAndDateTime.split(KEYWORD_DEADLINE_FORMAT, 2);
            Task task = new Deadline(descriptionAndDateTimeArray[0], formatDateTime(descriptionAndDateTimeArray[1]));
            String expected = "Got it. I've added this task:" + "\n" + task.toString() + "\n"
                    + "Now you have 1 tasks in the list." + "\n\n";
            AddCommand command = new AddCommand(new String[]{type, descriptionAndDateTime});
            String dukeReply = (String) method.invoke(command, type, descriptionAndDateTime, ui, tasks);
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException | InvalidFormatDateException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Valid format for add task (todo)")
    public void addTask_addingToDo_success() {
        try {
            String type = "todo";
            String description = "task 1";
            Task task = new ToDo(description);
            String expected = "Got it. I've added this task:" + "\n" + task.toString() + "\n"
                    + "Now you have 1 tasks in the list." + "\n\n";
            AddCommand command = new AddCommand(new String[]{type, description});
            String dukeReply = (String) method.invoke(command, type, description, ui, tasks);
            assertEquals(dukeReply, expected);
        } catch (InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        }
    }
}
