package duke.task;

import java.time.format.DateTimeFormatter;

enum DateFormat {
    FORMAT1("d-M-yy"),
    FORMAT2("d/M/yy"),
    FORMAT3("d-M-yyyy"),
    FORMAT4("d/M/yyyy"),
    FORMAT5("d MMM yyyy"),
    FORMAT6("yyyy-MM-dd");

    private final String pattern;

    DateFormat(String pattern) {
        this.pattern = pattern;
    }

    DateTimeFormatter toDateFormat() {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
