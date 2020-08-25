package duke.dukeException;

/** This class handles the custom exceptions pertaining to Duke. */
public class DukeException extends Exception {

  /**
   * Constructor for Duke Exception.
   *
   * @param errorMsg the custom error message caught in Duke.
   */
  public DukeException(String errorMsg) {
    super(errorMsg);
  }

  /**
   * Returns Duke error message.
   *
   * @return String format of error message.
   */
  @Override
  public String toString() {
    return getMessage();
  }
}
