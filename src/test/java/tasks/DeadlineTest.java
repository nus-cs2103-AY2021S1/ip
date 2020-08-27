package tasks;

import exceptions.DukeDateTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DeadlineTest {
    @Test
    public void testDeadlineClassThrowsDukeDateTimeException(){
        assertThrows(DukeDateTimeException.class,()->new Deadline("description", "fail"));
    }

    
    @Test 
    public void testDeadlineClassAutoCorrectsBlankDateField(){
        try{
            Deadline deadline = new Deadline("random_desc","");
            assertEquals(deadline.getDateby(),LocalDate.now().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDateTimeDayArithmetric(){
        try{
            Deadline deadline = new Deadline("random_desc","");
            assertEquals(deadline.timeLeft(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
