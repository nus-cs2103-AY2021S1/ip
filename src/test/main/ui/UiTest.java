package main.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.exception.InvalidOptionException;
import main.task.Deadline;
import main.task.Event;
import main.task.TaskList;
import main.task.Todo;

public class UiTest {
    private final Ui ui = new Ui();

    @Nested
    @DisplayName("exit message")
    class Exit {
        @Test
        @DisplayName("should return the exit message")
        public void printExitMessage_noInput_exitMessage() {
            assertEquals("Bye. Hope to see you again soon!",
                    ui.printExitMessage());
        }
    }

    @Nested
    @DisplayName("greeting message")
    class Greeting {
        @Test
        @DisplayName("should return the greeting message")
        public void printGreetingMessage_noInput_greetingMessage() {
            assertEquals("Hello! I'm Stuff\nWhat can I do for you?",
                    ui.printGreetingMessage());
        }
    }

    @Nested
    @DisplayName("list message")
    class List {
        @Test
        @DisplayName("should return the message with the list of tasks")
        public void printTaskList_taskList_messageWithListOfTasks()
                throws InvalidOptionException {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("task 1", new String[0]));
            tasks.add(new Deadline(
                    "task 2",
                    "",
                    "1993-12-06T10:10",
                    true,
                    new String[0]
            ));
            tasks.add(new Event(
                    "task 3",
                    LocalDateTime.of(3121, 12, 29, 23, 54),
                    new HashSet<>(),
                    new String[0]
            ));
            assertEquals("1. [T][\u2718] task 1\n2. [D][\u2713] task 2\n"
                            + "(by: Monday, 06 Dec 1993, 10:10AM)\n"
                            + "3. [E][\u2718] task 3\n(at: Thursday, 29 Dec 3121,"
                            + " 11:54PM)\n",
                    ui.printTaskList(tasks));
        }

        @Test
        @DisplayName("should return the message with alternate list of tasks")
        public void printTaskList_altTaskList_messageWithListOfTasks() {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("test task", new String[0]));
            assertEquals("1. [T][\u2718] test task\n", ui.printTaskList(tasks));
        }

        @Test
        @DisplayName("should return no tasks yet message if no tasks in list")
        public void printTaskList_emptyTaskList_noTasksMessage() {
            TaskList tasks = new TaskList();
            assertEquals("There are no tasks yet!", ui.printTaskList(tasks));
        }
    }

    @Nested
    @DisplayName("find message")
    class Find {
        @Test
        @DisplayName("should return the message with the list of tasks")
        public void printFoundList_taskList_messageWithListOfTasks()
                throws InvalidOptionException {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("task 1", new String[0]));
            tasks.add(new Deadline(
                    "task 2",
                    "",
                    "1993-12-06T10:10",
                    true,
                    new String[0]
            ));
            tasks.add(new Event(
                    "task 3",
                    LocalDateTime.of(3121, 12, 29, 23, 54),
                    new HashSet<>(),
                    new String[0]
            ));
            assertEquals("Here are the matching tasks in your list:\n"
                            + "1. [T][\u2718] task 1\n2. [D][\u2713] task 2\n"
                            + "(by: Monday, 06 Dec 1993, 10:10AM)\n"
                            + "3. [E][\u2718] task 3\n(at: Thursday, 29 Dec 3121,"
                            + " 11:54PM)\n",
                    ui.printFoundList(tasks));
        }

        @Test
        @DisplayName("should return the message with alternate list of tasks")
        public void printFoundList_altTaskList_messageWithListOfTasks() {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("test task", new String[0]));
            assertEquals("Here are the matching tasks in your list:\n"
                            + "1. [T][\u2718] test task\n",
                    ui.printFoundList(tasks));
        }

        @Test
        @DisplayName("should return no tasks found message if no tasks in list")
        public void printFoundList_emptyTaskList_noTasksMessage() {
            TaskList tasks = new TaskList();
            assertEquals("There are no tasks found!", ui.printFoundList(tasks));
        }
    }

    @Nested
    @DisplayName("add task success message")
    class AddTaskSuccess {
        @Test
        @DisplayName("should return message indicating todo has been added")
        public void printAddSuccess_todo_addSuccessMessage() {
            assertEquals("Got it. I've added this task:\n[T][\u2718] task 1\n"
                            + "Now you have 3 tasks in the list.",
                    ui.printAddSuccess(new Todo("task 1", new String[0]), 3));
        }

        @Test
        @DisplayName("should return message indicating deadline has been added")
        public void printAddSuccess_deadline_addSuccessMessage()
                throws InvalidOptionException {
            assertEquals("Got it. I've added this task:\n[D][\u2713] task 2\n"
                            + "(by: Monday, 06 Dec 1993, 10:10AM)\n"
                            + "Now you have 0 tasks in the list.",
                    ui.printAddSuccess(
                            new Deadline(
                                    "task 2",
                                    "",
                                    "1993-12-06T10:10",
                                    true,
                                    new String[0]
                            ),
                            0
                    ));
        }

        @Test
        @DisplayName("should return message indicating event has been added")
        public void printAddSuccess_event_addSuccessMessage() {
            assertEquals("Got it. I've added this task:\n[E][\u2718] task 3\n"
                            + "(at: Thursday, 29 Dec 3121, 11:54PM)\n"
                            + "Now you have 5 tasks in the list.",
                    ui.printAddSuccess(new Event(
                            "task 3",
                            LocalDateTime.of(3121, 12, 29, 23, 54),
                            new HashSet<>(),
                            new String[0]
                    ), 5));
        }

        @Test
        @DisplayName("should return message with word \"task\" in singular form")
        public void printAddSuccess_taskNumOne_singularTaskMessage() {
            assertEquals("Got it. I've added this task:\n[T][\u2718] task 1\n"
                            + "Now you have 1 task in the list.",
                    ui.printAddSuccess(new Todo("task 1", new String[0]), 1));
        }
    }

    @Nested
    @DisplayName("remove task success message")
    class RemoveTaskSuccess {
        @Test
        @DisplayName("should return message indicating todo has been removed")
        public void printRemoveSuccess_todo_addSuccessMessage() {
            assertEquals("Noted. I've removed this task:\n[T][\u2718] task 1\n"
                            + "Now you have 3 tasks in the list.",
                    ui.printRemoveSuccess(new Todo("task 1", new String[0]), 3));
        }

        @Test
        @DisplayName("should return message indicating deadline has been removed")
        public void printRemoveSuccess_deadline_addSuccessMessage()
                throws InvalidOptionException {
            assertEquals("Noted. I've removed this task:\n[D][\u2713] task 2\n"
                            + "(by: Monday, 06 Dec 1993, 10:10AM)\n"
                            + "Now you have 0 tasks in the list.",
                    ui.printRemoveSuccess(
                            new Deadline(
                                    "task 2",
                                    "",
                                    "1993-12-06T10:10",
                                    true,
                                    new String[0]
                            ),
                            0
                    ));
        }

        @Test
        @DisplayName("should return message indicating event has been removed")
        public void printRemoveSuccess_event_addSuccessMessage() {
            assertEquals("Noted. I've removed this task:\n[E][\u2718] task 3\n"
                            + "(at: Thursday, 29 Dec 3121, 11:54PM)\n"
                            + "Now you have 5 tasks in the list.",
                    ui.printRemoveSuccess(new Event(
                            "task 3",
                            LocalDateTime.of(3121, 12, 29, 23, 54),
                            new HashSet<>(),
                            new String[0]
                            ),
                            5
                    ));
        }

        @Test
        @DisplayName("should return message with word \"task\" in singular form")
        public void printRemoveSuccess_taskNumOne_singularTaskMessage() {
            assertEquals("Noted. I've removed this task:\n[T][\u2718] task 1\n"
                            + "Now you have 1 task in the list.",
                    ui.printRemoveSuccess(new Todo("task 1", new String[0]), 1));
        }
    }

    @Nested
    @DisplayName("done success message")
    class DoneSuccess {
        @Test
        @DisplayName("should return message indicating todo as done")
        public void printDoneSuccess_todo_doneSuccessMessage() {
            assertEquals("Nice! I've marked this task as done:\n[T][\u2718] task 1",
                    ui.printDoneSuccess(new Todo("task 1", new String[0])));
        }

        @Test
        @DisplayName("should return message indicating deadline as done")
        public void printDoneSuccess_deadline_doneSuccessMessage()
                throws InvalidOptionException {
            assertEquals("Nice! I've marked this task as done:\n"
                            + "[D][\u2713] task 2\n(by: Monday, 06 Dec 1993, 10:10AM)",
                    ui.printDoneSuccess(
                            new Deadline(
                                    "task 2",
                                    "",
                                    "1993-12-06T10:10",
                                    true,
                                    new String[0]
                            )));
        }

        @Test
        @DisplayName("should return message indicating event as done")
        public void printDoneSuccess_event_doneSuccessMessage() {
            assertEquals("Nice! I've marked this task as done:\n"
                            + "[E][\u2718] task 3\n(at: Thursday, 29 Dec 3121, 11:54PM)",
                    ui.printDoneSuccess(new Event(
                            "task 3",
                            LocalDateTime.of(3121, 12, 29, 23, 54),
                            new HashSet<>(),
                            new String[0]
                    )));
        }
    }

    @Nested
    @DisplayName("error message")
    class Error {
        @Test
        @DisplayName("should return generic error message")
        public void printErrorMessage_noInput_errorMessage() {
            assertEquals("Seems like something went wrong!", ui.printErrorMessage());
        }
    }
}
