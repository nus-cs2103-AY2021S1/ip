package mattbot.parser;

import java.time.LocalDate;

import mattbot.command.ViewScheduleCommand;


public class ParseViewSchedules extends Parse {
    public static String execute2(LocalDate timeDate) {
        return ViewScheduleCommand.execute2(timeDate);
    }
}
