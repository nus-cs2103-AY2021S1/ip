import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The Bot class is the main brain of the program. It takes in the user input via the Scanner object
 * and coordinates with the Parser, Storage, Storage and Printer class to carry out the appropriate
 * output.
 */

public class Bot {

    Printer printer = new Printer(); //ui
    Parser parser = new Parser();
    Storage storage = new Storage("./data/duke.txt");
    TaskList taskList = new TaskList(storage.load());
    LocalDate localDate;

    /**
     * Prints the greeting when created.
     */
    public Bot() {
        printer.greeting();
    }

    /**
     * Checks for the next line as inputted by the user. Parses the message through parser.getDetails(
     * message) which returns an array of size 3 containing the type of message, detail and the date.
     * Date will be null if the message is not of Deadline or Event. The function will then carry out
     * the corresponding action depending on the type of message by interacting with the TaskList
     * object. The function will also pass the storage and printer object so that TaskList can save
     * the data into duke.txt after completion and also print the corresponding message. Catches
     * undefined and wrong messages exception. Type of commands supported are [bye, list, done, todo,
     * event, deadline, delete, find].
     */
    public String serve(String input) {
        try {
            String s = input;
            String output = "";
            String[] parsedInfo = parser.getDetails(s);
            String command = parsedInfo[0];
            String commandDetail = parsedInfo[1];
            String dateInfo = parsedInfo[2];
            switch (command) {
                case ("bye"):
                    output = printer.farewell();
                    return output;
                case ("list"):
                    output = taskList.printReturns();
                    return output;
                case ("done"):
                    output = taskList.doneListings(Integer.valueOf(commandDetail), printer, storage);
                    return output;
                case ("todo"):
                    if (commandDetail == null) {
                        throw new NoDescriptionException("todo");
                    } else {
                        output = taskList.addListings(parsedInfo, printer, storage);
                    }
                    return output;
                case ("deadline"):
                    if (commandDetail == null || dateInfo == null) {
                        throw new NoDescriptionException("deadline");
                    } else {
                        localDate.parse(dateInfo);
                        output = taskList.addListings(parsedInfo, printer, storage);
                    }
                    return output;
                case ("event"):
                    if (commandDetail == null || dateInfo == null) {
                        throw new NoDescriptionException("event");
                    } else {
                        output = taskList.addListings(parsedInfo, printer, storage);
                    }
                    return output;
                case ("delete"):
                    Integer deleteNumber = Integer.valueOf(parsedInfo[1]) - 1;
                    if (deleteNumber < 0) {
                        throw new invalidDeleteNumberException();
                    }
                    //assert deleteNumber >= 1 : "Invalid Number";
                    output = taskList.deleteListing(deleteNumber, printer, storage);
                    return output;
                case ("find"):
                    output = taskList.find(commandDetail);
                    return output;
                case ("tag"):
                    if (commandDetail == null & dateInfo == null) {
                        throw new invalidTagException();
                    }
                    Integer tagNumber = Integer.valueOf(commandDetail) - 1; //catch bug
                    String tagDetail = dateInfo;
                    output = taskList.tagListing(tagNumber, tagDetail, printer, storage);
                    return output;
                default:
                    throw new UndefinedException();
            }
        } catch (DateTimeParseException e) { // wrong dates
            return printer.dateTimeParseExceptionMessage();
        } catch (NoDescriptionException e) { //incomplete messages
            return printer.noDescriptionMessage(e.s);
        } catch (UndefinedException e) { //unknown commands
            return printer.undefinedExceptionMessage();
        } catch (invalidDeleteNumberException e) { //invalid date number
            return printer.invalidDeleteNumberExceptionMessage();
        } catch (java.lang.NumberFormatException e) { //parsed a date number that is not a number
            return printer.invalidDeleteNumberExceptionMessage();
        } catch (invalidTagException e) {
            return printer.invalidTagExceptionMessage();
        } catch (AssertionError e) { //assertion
            return printer.assertionErrorMessage();
        }

    }

}
