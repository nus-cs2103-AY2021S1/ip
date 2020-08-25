package main.java.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    void addTask_success() {
        try {
            TaskList tk = new TaskList();
            tk.addDeadLine("Assignment 1", "2020-09-08 06:00");
            tk.addEvent("Assignment 1", "2020-09-08 06:00 08:00");
            tk.addToDo("Assignment 1");
            String expectedAnswer = "1.[D][ ] Assignment 1 (by: Sep 08 2020 06:00)\n" +
                    "2.[E][ ] Assignment 1 (at: Sep 08 2020 06:00 - 08:00)\n" +
                    "3.[T][ ] Assignment 1";
            assertEquals(tk.toString(), expectedAnswer);
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    void addTask_invalidArgument() {
        try {
            TaskList tk = new TaskList();
            tk.addDeadLine("Assignment 1", "2020-09-08 06:00");
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Error: Please key in as: \n " +
                    "event [title] /by YYYY-MM-DD HH:MM");
        }
    }

    @Test
    void doTask_success() {
        try {
            TaskList tk = new TaskList();
            tk.addDeadLine("Assignment 1", "2020-09-08 06:00");
            tk.addEvent("Assignment 1", "2020-09-08 06:00 08:00");
            tk.addToDo("Assignment 1");
            tk.doTask(1);
            tk.doTask(3);
            String expectedAnswer = "1.[D][X] Assignment 1 (by: Sep 08 2020 06:00)\n" +
                    "2.[E][ ] Assignment 1 (at: Sep 08 2020 06:00 - 08:00)\n" +
                    "3.[T][X] Assignment 1";
            assertEquals(tk.toString(), expectedAnswer);
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    void doTask_setDoneTaskDone_fail() {
        try {
            TaskList tk = new TaskList();
            tk.addDeadLine("Assignment 1", "2020-09-08 06:00");
            tk.doTask(1);
            tk.doTask(1);
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Task is already done");
        }
    }

    @Test
    void toSaveFormat() {
        try {
            TaskList tk = new TaskList();
            tk.addDeadLine("return book", "2020-06-06 23:59");
            tk.addEvent("project meeting", "2020-08-06 14:00 16:00");
            tk.addToDo("read book");
            tk.doTask(1);
            tk.doTask(3);
            String expectedAnswer = "D | 1 | return book | 2020-06-06 23:59\n" +
                    "E | 0 | project meeting | 2020-08-06 14:00 16:00\n" +
                    "T | 1 | read book\n";
            assertEquals(tk.toSaveFormat(), expectedAnswer);
        } catch (DukeException err) {
            fail();
        }
    }

    @Test
    void deleteTask() {
        try {
            TaskList tk = new TaskList();
            tk.addDeadLine("Assignment 1", "2020-09-08 06:00");
            tk.addEvent("Assignment 2", "2020-09-08 06:00 08:00");
            tk.addToDo("Assignment 1");
            tk.doTask(1);
            tk.doTask(3);
            tk.deleteTask(3);
            System.out.println(tk.toString());
            String expectedAnswer = "1.[D][X] Assignment 1 (by: Sep 08 2020 06:00)\n" +
                    "2.[E][ ] Assignment 2 (at: Sep 08 2020 06:00 - 08:00)";
            assertEquals(tk.toString(), expectedAnswer);
        } catch (DukeException err) {
            fail();
        }
    }
}