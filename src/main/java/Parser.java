public class Parser {
    public static final String LINE = "_______________________________________\n";

    public static void parse(String userInput) {
        String[] inputSplit = userInput.split(" ", 2);
        String userCommand = inputSplit[0];
        try {
            if (userCommand.equals("bye")) {  // For exiting the program
                Duke.running = false;
                Duke.storage.save();
            } else if (userCommand.equals("list")) {  // For viewing items in to do list
                Duke.ui.handleList();
            } else if (userCommand.equals("done")) {  // For marking items in the to do list as done
                try {
                    Duke.ui.handleDone(inputSplit[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(LINE + "Invalid input! Please specify which task you have completed! \n" + LINE);
                }
            } else if (userCommand.equals("todo")) { // Add new to do task
                try {
                    Duke.ui.handleTodo(inputSplit[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(LINE + "Invalid input! Please specify your todo description! \n" + LINE);
                }
            } else if (userCommand.equals("deadline")) { // Add new deadline
                try {
                    Duke.ui.handleDeadline(inputSplit[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(LINE + "Invalid input! Please specify your deadline description and details! \n" + LINE);
                }

            } else if (userCommand.equals("event")) { // Add new event
                try {
                    Duke.ui.handleEvent(inputSplit[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(LINE + "Invalid input! Please specify your event description and details! \n" + LINE);
                }

            } else if (userCommand.equals("delete")) { // Delete task
                try {
                    Duke.ui.handleDelete(inputSplit[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(LINE + "Invalid input! Please specify which task you want to delete! \n" + LINE);
                }
            } else if (userCommand.equals("filter")) { // Filter taskList
                try {
                    Duke.ui.handleFilter(inputSplit[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(LINE + "Invalid input! Please specify which date you want to filter! \n" + LINE);
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
