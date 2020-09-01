package duke.exception;



public class InvalidArgumentException {
  String errorMessage;

  public InvalidArgumentException() {
    this.errorMessage = "Sorry, Duke does not recognize your command!";
  }

  public InvalidArgumentException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public String toString() {
    return errorMessage;
  }
}
