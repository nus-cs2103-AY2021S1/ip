package botbot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class BotbotDateTimeFormatterTest {
    private static final LocalDateTime DATE_WITHOUT_TIME = LocalDateTime.of(2020, 9, 16, 3, 14, 15, 926535898);
    private static final LocalDateTime DATE_WITH_HOUR = LocalDateTime.of(2021, 3, 17, 3, 0);
    private static final LocalDateTime DATE_WITH_HOUR_MINS = LocalDateTime.of(2018, 4, 3, 16, 25);

    @Test
    public void convertDateTimeToStr() {
        assertEquals("16 Sep 2020", BotbotDateTimeFormatter.convertDateTimeToStr(DATE_WITHOUT_TIME));
        assertEquals("17 Mar 2021 3am", BotbotDateTimeFormatter.convertDateTimeToStr(DATE_WITH_HOUR));
        assertEquals("3 Apr 2018 4.25pm", BotbotDateTimeFormatter.convertDateTimeToStr(DATE_WITH_HOUR_MINS));
    }
}
