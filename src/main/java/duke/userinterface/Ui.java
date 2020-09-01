package duke.userinterface;

import duke.storage.Storage;
import duke.task.TaskList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ui {
  TaskList taskList;
  Parser parser;
  static final char LINE = '*';

  public Ui(TaskList taskList, Storage storage) {
    this.taskList = taskList;
    this.parser = new Parser(taskList, storage);
    start();
  }

  /** Handles I/O interaction with users and passes the command to the duke.userinterface.Parser. */
  public String handleInteraction(String input) {
    printTopLine();
    String output = parser.handleCommand(input);
    printBottomLine();
    if (output == "") {
      return "Sorry! Command not recognized \n";
    } else {
      return output + "\n";
    }
  }

  /** Prints a welcome message to the user. */
  void start() {
    printTopLine();
    System.out.println("Hello! I'm Duke \n What can I do for you?");
    printBottomLine();
  }

  /** Prints the top line, e.g. ******************* */
  void printTopLine() {
    Stream.generate(() -> LINE).limit(50).forEach(System.out::print); // _ _ _ _ _
    System.out.println();
  }

  /** Prints the bottom line e.g. **************** */
  void printBottomLine() {
    System.out.println();
    Stream.generate(() -> LINE).limit(50).forEach(System.out::print); // _ _ _ _ _
    System.out.println();
  }
}
