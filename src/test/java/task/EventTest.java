package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    LocalDate date1 = LocalDate.parse("2020-12-03");
    Event task1 = new Event("test", "Mar 12 2020 12.59pm", date1, "03/12/2020 1259", true);

    LocalDate date2 = LocalDate.parse("2020-09-05");
    Event task2 = new Event("project", "Sep 9 2020 3.04am", date2, "05/09/2020 0304", false);

    @Test
    public void inputTest1(){
        assertEquals("[E][O]test" + "/at " + "03/12/2020 1259", task1.getInput());
    }

    @Test
    public void inputTest2(){
        assertEquals("[E][X]project" + "/at " + "05/09/2020 0304", task2.getInput());
    }

    @Test
    public void itemTest1(){
        assertEquals("[E][O]test" + "(at: " + "Mar 12 2020 12.59pm)", task1.getItem());
    }

    @Test
    public void itemTest2(){
        assertEquals("[E][X]project" + "(at: " + "Sep 9 2020 3.04am)", task2.getItem());
    }
}
