package duke.runner;

import duke.command.Command;
import duke.duke.Duke;
import duke.exception.ParserException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.view.cli.CLI;
import java.util.Scanner;

/**
 * Class that handles the "event loop" of the CLI program, terminating when a termination command is
 * detected.
 */
public class Runner {

  public static void main(String[] args) {
    run();
  }

  /*
   * Prints hello, goodbye, nd output of command executions. Also runs the event loop.
   */
  public static void run() {
    Scanner sc = new Scanner(System.in);
    Duke duke = new Duke(new Storage("data/data.txt"));
    duke.addObserver(new CLI());

    String input;
    while (true) {
      try {
        input = sc.nextLine();
        Command command = Parser.parseCommand(input);
        command.execute(duke);
        if (command.isExit()) {
          break;
        }
      } catch (Exception e) { // TODO: break up exception catching
        System.out.println(e.getMessage());
      }
    }
  }
}
