package test.java;

import luoyi.duke.data.task.Deadline;
import luoyi.duke.data.task.Event;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.data.task.ToDo;
import luoyi.duke.ui.Ui;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void greet_stringPrinted() {
        Ui.greet();
        String expectedOutput
                = "　　＼　　　　　／\n　　　＼∧∧∧／\n　　　＜　 Ｃ ＞\n　　　＜ 　Ａ ＞\n　　　＜　 Ｔ ＞\n"
                + "　 ───＜ Ｂ　 ＞───\n　　　＜ Ｏ　 ＞\n　　　＜ Ｔ　 ＞\n　　　／∨∨∨＼\n　　／ ∧_∧　　＼\n"
                + "　／　( ･ω･)　　 ＼\n／　＿(_つ/￣￣￣/　＼\n　　 　＼/＿＿＿/\n"
                + "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n"
                + "∴‥	Hi I'm Cat Bot.\n∴‥	What can I do for you?\n"
                + "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void bye_stringPrinted() {
        Ui.bye();
        String expectedOutput
                = "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n"
                + "∴‥\tBye! Hope to see you again!\n"
                + "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void displayTasks_emptyList_emptyListPromptPrinted() {
        TaskList list = new TaskList(new ArrayList<>());
        Ui.displayTasks(list);
        String expectedOutput = "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n"
                + "∴‥\tOops! Looks like there's no task in the list!\n"
                + "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void displayTasks_correctTaskList_listPrinted() {
        TaskList list = new TaskList(new ArrayList<>());
        list.add(ToDo.getToDo("Have lunch."));
        list.add(Deadline.getDeadline("Homework", "20201111"));
        list.add(Event.getEvent("Tutorial", "2020 11 11 11:22").markComplete());
        Ui.displayTasks(list);
        String expectedOutput = "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n"
                + "∴‥\tHere are the tasks in your list:\n"
                + "∴‥\t 1. [T][✗] Have lunch.\n"
                + "∴‥\t 2. [D][✗] Homework (by: 2020-11-11)\n"
                + "∴‥\t 3. [E][✓] Tutorial (at: 2020-11-11T11:22)\n"
                + "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void displayTasks_correctDate_matchingTaskPrinted() {
        TaskList list = setUpTestList();
        Ui.displayTasks(list, new int[]{2, 3});
        String expectedOutput = "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n"
                + "∴‥\tHere are the task on you are looking for:\n"
                + "∴‥\t 3. [E][✓] Tutorial (at: 2020-11-11T11:22)\n"
                + "∴‥\t 4. [E][✓] Lecture (at: 2020-11-12T10:45)\n"
                + "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void displayTasks_noMatch_noMatchPromptPrinted() {
        TaskList list = setUpTestList();
        Ui.displayTasks(list, new int[]{});
        String expectedOutput = "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n"
                + "∴‥\tOops! Looks like there's no matching task!\n"
                + "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    private TaskList setUpTestList() {
        TaskList list = new TaskList(new ArrayList<>());
        list.add(ToDo.getToDo("Have lunch."));
        list.add(Deadline.getDeadline("Homework", "20201111"));
        list.add(Event.getEvent("Tutorial", "2020 11 11 11:22").markComplete());
        list.add(Event.getEvent("Lecture", "2020 11 12 10:45").markComplete());
        return list;
    }

}

