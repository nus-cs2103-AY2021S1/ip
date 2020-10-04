package duke.task;

import duke.exception.ParserException;
import duke.utils.Parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

  private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

  private final LocalDateTime at;

  public EventTask(String desc, String at) throws ParserException {
    super(desc);
    this.at = Parser.parseDateTime(at);
  }

//  public String getAt() {
//    return at;
//  }


  @Override
  public String toString() {
    return String
        .format("%d | %s | %s | %s | %s", getId(), getIsDone() ? "DONE" : "NOT DONE", "EVENT",
            getDesc(), at.format(DT_FORMAT));
  }
}
