package duke.scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import java.util.Scanner;

/** CommandScanner that scans for commands */
public class CommandScanner {
  private Scanner sc;

  public CommandScanner() {
    this.sc = new Scanner(System.in);
  }

  /**
   * Returns next command in the user input
   *
   * @return a Command object that represent the next command in the user input
   * @throws DukeException when the command is invalid
   */
  public Command nextCommand() throws DukeException {
    return Parser.parseCommandString(this.sc.nextLine());
  }
}
