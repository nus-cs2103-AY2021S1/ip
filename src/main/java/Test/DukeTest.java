package Test;

import com.Duke.TaskManager.TaskList;
import com.Duke.Tasks.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void toDoTest() {
        try {
            ToDo task = new ToDo("Do 2103 IP", false);
            assertEquals(task.toString(), "[T][\u2718] Do 2103 IP");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void eventTest() {
        try {
            Event task = new Event("Dinner Party", LocalTime.parse("15:30"), LocalTime.parse("17:30"), false, LocalDate.parse("2011-11-19") );
            assertEquals(task.toString(), "[E][\u2718] Dinner Party(on: Nov 19 2011 at: 15:30 - 17:30)");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void deadlineTest() {
        try {
            Deadline task = new Deadline("Graduation", LocalDate.parse("2011-11-19"), false);
            assertEquals(task.toString(), "[D][\u2718] Graduation(by: Nov 19 2011)");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void doWithinTest(){
        try{
            DoWithin task = new DoWithin("Assignment", LocalDate.parse("2020-09-15"), LocalDate.parse("2020-09-18"), false);
            assertEquals(task.toString(), "[W][\u2718] Assignment(within: Sep 15 2020 - Sep 18 2020)");
        }catch (Exception ignored){
        }
    }

    @Test
    public void deleteTest(){
        try {
            TaskList ls = new TaskList(new ArrayList<>());
            ToDo task = new ToDo("Do 2103 IP", false);
            ls.add(task);
            assertEquals(ls.delete(1), "Noted. I've removed this task: \n"+task.toString());
        }catch(Exception ignored){
        }
    }
}

