/** Parser class Responsible for parsing user commands */
public class Parser {

  /**
   * Takes in user commands in a tokenized string form Updates tasklist accordingly
   *
   * @param inputs User inputs
   * @param tasks TaskList
   */
  public void parseCommand(String[] inputs, TaskList tasks) {
    assert(inputs.length > 0) ;
    String command = inputs[0];
    switch (command) {
      case "list":
        tasks.list();
        break;
      case "done":
        try {
          tasks.handleDone(inputs);
        } catch (DukeException e) {
          System.out.println(e);
        }
        break;
      case "deadline":
        tasks.handleDeadline(inputs);
        break;
      case "event":
        tasks.handleEvent(inputs);
        break;
      case "todo":
        try {
          tasks.handleToDo(inputs);
        } catch (DukeException e) {
          System.out.println(e);
        }
        break;
      case "delete":
        try {
          tasks.handleDelete(inputs);
        } catch (DukeException e) {
          System.out.println(e);
        }
        break;
      case "find":
        tasks.handleFind(inputs);
        break;
      default:
        try {
          throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } catch (DukeException e) {
          System.out.println(e);
        }
    }
  }

  public String parseCommandUi(String[] inputs, TaskList tasks) {
    assert(inputs.length > 0) ;
    String command = inputs[0];
    switch (command) {
      case "list":
        return tasks.list();
      case "done":
        try {
          return tasks.handleDone(inputs);
        } catch (DukeException e) {
          System.out.println(e);
          return e.getMessage();
        }
      case "deadline":
        return tasks.handleDeadline(inputs);
      case "event":
        return tasks.handleEvent(inputs);
      case "todo":
        try {
          return tasks.handleToDo(inputs);
        } catch (DukeException e) {
          System.out.println(e);
          return e.getMessage();
        }
      case "delete":
        try {
          return tasks.handleDelete(inputs);
        } catch (DukeException e) {
          System.out.println(e);
          return e.getMessage();
        }
      case "find":
        return tasks.handleFind(inputs);
      case "update":
        return tasks.handleUpdate(inputs);
      case "bye":
        return "Bye. Hope to see you again soon!";
      default:
        try {
          throw new DukeException("Meow Meow does not understand");
        } catch (DukeException e) {
          System.out.println(e);
          return e.getMessage();
        }
    }
  }
}
