package duke.exception;

public class EmptyArgumentException {
  String errorMessage;

  public EmptyArgumentException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public String toString() {
    return errorMessage;
  }
}
