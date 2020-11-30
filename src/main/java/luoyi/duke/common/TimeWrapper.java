package luoyi.duke.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

import luoyi.duke.parser.TimeParser;

/**
 * Time wrapper class to store a time that maybe contains explicit time representation.
 */
public class TimeWrapper implements Comparable<TimeWrapper> {
    private final LocalDate date;
    private final LocalDateTime dateTime;
    private final String message;

    private TimeWrapper(LocalDate date, LocalDateTime dateTime, String message) {
        this.date = date;
        this.dateTime = dateTime;
        this.message = message;
    }

    /**
     * Returns a time wrapper.
     *
     * @param string String representation of time.
     * @return A time wrapper which may contain explicit time representation.
     */
    public static TimeWrapper getTimeWrapper(String string) {
        if (TimeParser.isDateTime(string)) {
            LocalDateTime ldt = TimeParser.parseDateTime(string);
            return new TimeWrapper(ldt.toLocalDate(), ldt, null);
        } else if (TimeParser.isDate(string)) {
            return new TimeWrapper(TimeParser.parseDate(string), null, null);
        }
        return new TimeWrapper(null, null, string);
    }

    /**
     * Returns true if both time wrapper represents the same time.
     *
     * @param obj An object.
     * @return True if both objects are TimeWrapper and represent the same time.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else if (!(obj instanceof TimeWrapper)) {
            return false;
        }
        TimeWrapper tw = (TimeWrapper) obj;
        if (this.dateTime != null && tw.dateTime != null) {
            return this.dateTime.equals(tw.dateTime);
        }
        if (this.date != null && tw.date != null) {
            return this.date.equals(tw.date);
        }
        if (this.message != null && tw.message != null) {
            return this.message.equals(tw.message);
        }
        return false;
    }

    @Override
    public String toString() {
        if (dateTime != null) {
            return dateTime.toString();
        }
        if (date != null) {
            return date.toString();
        }
        if (message != null) {
            return message;
        }
        return "No data found.";
    }

    @Override
    public int compareTo(TimeWrapper o) {
        if (this.dateTime != null) {
            if (o.dateTime != null) {
                return this.dateTime.compareTo(o.dateTime);
            } else if (o.date != null) {
                return this.dateTime.toLocalDate().compareTo(o.date);
            } else if (o.message != null) {
                return 1;
            }
        } else if (this.date != null) {
            if (o.dateTime != null) {
                return this.date.compareTo(o.dateTime.toLocalDate());
            } else if (o.date != null) {
                return this.date.compareTo(o.date);
            } else if (o.message != null) {
                return 1;
            }
        } else if (this.message != null) {
            if (o.dateTime != null || o.date != null) {
                return -1;
            } else if (o.message != null) {
                return this.message.compareTo(o.message);
            }
        }
        throw new RuntimeException("Invalid TimeWrapper Object!");
    }
}
