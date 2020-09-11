package duke.datetime;

import java.time.LocalDateTime;
import java.util.function.Function;

public interface FastForwarder extends Function<LocalDateTime, LocalDateTime> {
}
