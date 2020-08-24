package task;

import java.time.format.DateTimeFormatter;

enum DateTimeFormat {
    FORMAT1("d-M-yy"),
    FORMAT2("d/M/yy"),
    FORMAT3("d-M-yyyy"),
    FORMAT4("d/M/yyyy"),
    FORMAT5("d MMM yyyy");

    private final String pattern;

    DateTimeFormat(String pattern) {
        this.pattern = pattern;
    }

    DateTimeFormatter toDateFormat() {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
