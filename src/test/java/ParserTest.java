package test.java;

import Duke.main.Parser;
import Duke.main.Time;
import Duke.task.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void runTodoTest() {
        Parser.run("todo reading");
        Parser.reloadTaskList();
        Task expected = new Todo("reading");
        boolean isExist = false;

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                index = i;
                System.out.println(i);
                System.out.println(index);
                isExist = true;
            }
        }

        Parser.run(String.format("delete %d", index + 1));
        assertTrue(isExist);
    }

    @Test
    public void runDeadlineTest() {
        Parser.run("deadline eating /by 2020-08-30");
        Task expected = new Deadline("eating", new Time("2020-08-30").toString());
        boolean isExist = false;

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
            }
        }

        Parser.run(String.format("delete %d", index + 1));
        assertTrue(isExist);
    }

    @Test
    public void runEventTest() {
        Parser.run("event working /by 2020-08-30");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        boolean isExist = false;

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isExist = true;
            }
        }

        Parser.run(String.format("delete %d", index + 1));
        assertTrue(isExist);
    }

    @Test
    public void runDeleteTest() {
        Parser.run("event working /by 2020-08-30");
        Parser.run("delete 1");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        boolean isDeleted = true;

        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isDeleted = false;
            }
        }

        assertTrue(isDeleted);
    }

    @Test
    public void runDoneTest() {
        Parser.run("event working /by 2020-08-30");
        Parser.run("done 1");
        Task expected = new Event("working", new Time("2020-08-30").toString());
        expected.setDone();
        boolean isDone = false;

        int index = 1;
        int size = Parser.taskList.getTaskList().size();
        for (int i = 0; i < size; i++) {
            Task currentTask = Parser.taskList.getTaskList().get(i);
            if (currentTask.toString().equals(expected.toString())) {
                isDone = true;
                index = i + 1;
            }
        }

        Parser.run(String.format("delete %d", index));
        assertTrue(isDone);
    }

}