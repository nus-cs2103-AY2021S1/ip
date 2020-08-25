package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    LocalDate date1 = LocalDate.parse("2020-12-03");
    Deadline task1 = new Deadline("test", "Mar 12 2020 12.59pm", date1, "03/12/2020 1259", true);

    LocalDate date2 = LocalDate.parse("2020-09-05");
    Deadline task2 = new Deadline("cs2103 project", "Sep 9 2020 3.04am", date2, "05/09/2020 0304", false);

    @Test
    public void inputTest1(){
        assertEquals("[D][O]test" + "/by " + "03/12/2020 1259", task1.getInput());
    }

    @Test
    public void inputTest2(){
        assertEquals("[D][X]cs2103 project" + "/by " + "05/09/2020 0304", task2.getInput());
    }

    @Test
    public void itemTest1(){
        assertEquals("[D][O]test" + "(by: " + "Mar 12 2020 12.59pm)", task1.getItem());
    }

    @Test
    public void itemTest2(){
        assertEquals("[D][X]cs2103 project" + "(by: " + "Sep 9 2020 3.04am)", task2.getItem());
    }
}
