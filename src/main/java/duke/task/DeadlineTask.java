package duke.task;

import duke.exception.ParserException;
import duke.utils.Parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

  private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

  private final LocalDateTime by;

  public DeadlineTask(String desc, String by) throws ParserException {
    super(desc);
    this.by = Parser.parseDateTime(by);
  }

//  public String getBy() {
//    return by;
//  }


  @Override
  public String toString() {
    return String
        .format("%d | %s | %s | %s | %s", getId(), getIsDone() ? "DONE" : "NOT DONE", "DEADLINE",
            getDesc(), by.format(DT_FORMAT));
  }
}
