/**
 * Parser deals with deciphering the user commands.
 */
public class Parser {

    protected ListOfItems listOfItems;

    /**
     * Constructor initialises a new Parser object.
     *
     * @param list list of tasks.
     */
    public Parser(ListOfItems list) {
        this.listOfItems = list;
    }

    //TODO
    /**
     * Handles the user input and direct the input to the respective method in ListOfItems.
     * If it is not a valid command, a Duke Exception will be thrown.
     *
     * @param input user input.
     */
    protected String run(String input) {
        try {
            if (input.equals("list")) {
                return listOfItems.getList();
            } else if (input.contains("done")) {
                return listOfItems.doneItem(input);
            } else if (input.contains("delete")) {
                return listOfItems.deleteItem(input);
            } else if (input.contains("items due by")) {
                // check items due on a specific date
                return listOfItems.checkBy(input);
            } else if (input.contains("items due before")) {
                // check items due before a specific date + time
                return listOfItems.checkBefore(input);
            } else if (input.contains("find")) {
                return listOfItems.find(input);
            } else {
                return listOfItems.addItem(input);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

