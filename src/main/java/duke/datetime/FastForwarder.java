package duke.datetime;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * Single Method Interface for a function that fast forwards a LocalDateTime object by a fixed amount of time.
 */
public interface FastForwarder extends Function<LocalDateTime, LocalDateTime> {
}
