import java.util.Scanner;

public class Bot {

  /**
   *Prints the greeting when created
   */
  public Bot() {
    printer.greeting();
  }

  Printer printer = new Printer(); //ui
  Parser parser = new Parser();
  Storage storage = new Storage("./data/duke.txt");
  TaskList taskList = new TaskList(storage.load());
  Scanner sc = new Scanner(System.in);

  /**
   * Checks for the next line as inputted by the user. Parses the message through parser.getDetails(
   * message) which returns an array of size 3 containing the type of message, detail and the date.
   * Date will be null if the message is not of Deadline or Event. The function will then carry out
   * the corresponding action depending on the type of message by interacting with the
   * taskListing object. The function will also pass the storage and printer object so that
   * taskList can save the data into duke.txt after completion and also print the corresponding
   * message. Catches undefined and wrong messages exception.
   * Type of commands supported are [bye, list, done, todo, event, deadline, delete, find]
   */
  public void serve() {

    while (sc.hasNextLine()) {
      try {
        String s = sc.nextLine();
        String[] parsedInfo = parser.getDetails(s);
        String command = parsedInfo[0];
        String commandDetail = parsedInfo[1];
        String dateInfo = parsedInfo[2];
        switch (command) {
          case ("bye"):
            printer.farewell();
            return;
          case ("list"):
            taskList.printReturns();
            break;
          case ("done"):
            taskList.doneListings(Integer.valueOf(commandDetail), printer, storage);
            break;
          case ("todo"):
            if (commandDetail == null) {
              throw new NoDescriptionException("todo");
            } else {
              taskList.addListings(parsedInfo, printer, storage);
            }
            break;
          case ("deadline"):
            if (commandDetail == null || dateInfo == null) {
              throw new NoDescriptionException("deadline");
            } else {
              taskList.addListings(parsedInfo, printer, storage);
            }
            break;
          case ("event"):
            if (commandDetail == null || dateInfo == null) {
              throw new NoDescriptionException("event");
            } else {
              taskList.addListings(parsedInfo, printer, storage);
            }
            break;
          case ("delete"):
            Integer number = Integer.valueOf(parsedInfo[1]) - 1;
            taskList.deleteListing(number, printer, storage);
            break;
          case ("find"):
            taskList.find(commandDetail);
            break;
          default:
            throw new UndefinedException();
        }
      } catch (NoDescriptionException e) { //incomplete messages
        printer.noDescriptionMessage(e.s);
      } catch (UndefinedException e) { //unknown commands
        printer.undefinedExceptionMessage();
      }
    }
  }

}
