package duke.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DateTimeHelperTest {
    @Test
    public void processTimeStr_listOfTimeStr_correctConversion() {
        String[] listOfTests = new String[]{"2020-12-20 1900", "2021-11-30 2400","2000-01-01 2400"
                ,"1999-11-20 1300","1000-05-20 1000","2000-12-20 1100"};
        String[] expectedResults = new String[]{"07:00pm", "00:00am", "00:00am" ,"01:00pm", "10:00am", "11:00am"};
        for(int i = 0;i < listOfTests.length;i++) {
            DateTimeHelper dateTimeHelper = new DateTimeHelper(listOfTests[i]);
            dateTimeHelper.processInput();
            dateTimeHelper.processTimeStr();
            assertEquals(dateTimeHelper.getTime(), expectedResults[i]);
        }
    }
    @Test
    public void processDateStr_invalidInput_errorMessage() {
        String[] listOfTests = new String[]{"2020-14-20 1900", "2021-20-30 2400","2000-45-01 2400"
                ,"1999-20-20 1300","1000-20-20 1000","3000-34-20 1100"};
        for(int i = 0;i < listOfTests.length;i++) {
            DateTimeHelper dateTimeHelper = new DateTimeHelper(listOfTests[i]);
            dateTimeHelper.processInput();
            assertEquals(dateTimeHelper.processDateStr(), "error");
        }
    }

}
