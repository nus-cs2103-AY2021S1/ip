import java.util.ArrayList;
import java.util.Scanner;

public class Bot { //basically duke.java at this point
    public Bot() {
        printer.greeting();
    }

    Printer printer = new Printer(); //ui
    Parser parser = new Parser();
    Storage storage = new Storage("./data/duke.txt");
    TaskList taskList = new TaskList(storage.load());
    Scanner sc = new Scanner(System.in);

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
                    default:
                        throw new UndefinedException();
                }
            } catch (NoDescriptionException e) {
                printer.noDescriptionMessage(e.s);
            } catch (UndefinedException e) {
                printer.undefinedExceptionMessage();
            }
        }
    }

}
