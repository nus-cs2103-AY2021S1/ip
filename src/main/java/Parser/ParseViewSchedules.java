package Parser;

import Command.ViewScheduleCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParseViewSchedules extends Parse {
    public static String execute2(LocalDate timeDate) {
        return ViewScheduleCommand.execute2(timeDate);
    }

//    public static String execute3() {
//        return ViewScheduleCommand.execute3();
//    }
}
