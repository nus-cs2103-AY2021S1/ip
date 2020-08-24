package duke.command;

/** CommandType represents all the valid commands available */
public enum CommandType {
  LIST_DATE("date"),
  LIST("list"),
  DEADLINE("deadline"),
  TODO("todo"),
  EVENT("event"),
  DONE("done"),
  BYE("bye"),
  DELETE("delete");

  private String name;

  CommandType(String name) {
    this.name = name;
  }

  /**
   * Returns true if an only if the command string matches the command type
   *
   * @param commandString to be checked
   * @return true if an only if the command string matches the command type
   */
  public boolean is(String commandString) {
    return commandString.startsWith(this.name);
  }

  @Override
  public String toString() {
    return this.name;
  }
}
