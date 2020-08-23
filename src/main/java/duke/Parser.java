package duke;

import duke.Command.Command;
import duke.Command.DeadlineCommand;
import duke.Command.DeleteCommand;
import duke.Command.DoneCommand;
import duke.Command.EventCommand;
import duke.Command.ExitCommand;
import duke.Command.InvalidCommand;
import duke.Command.ListCommand;
import duke.Command.ToDoCommand;
import duke.Command.FindCommand;

import duke.Exception.DeadlineException;
import duke.Exception.DeletionException;
import duke.Exception.DoneException;
import duke.Exception.DukeException;
import duke.Exception.EventException;
import duke.Exception.ToDoException;
import duke.Exception.FindException;

public class Parser {

    /**
     * Returns a Command object depending on user's input
     * @param input User's input via standard input
     * @return Command object
     * @throws DukeException If input is wrong/not recognised
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            // when user inputs bye

            return new ExitCommand(input);
        } else if (input.equals("list")) {
            // when user wants to print list of task

            return new ListCommand(input);
        } else if (input.startsWith("done")) {
            // when user completes task

            String[] temp = input.split(" ");
            if (temp.length == 1) {
                throw new DoneException("Please input number after done! Example of input would be 'done 2' which checks item number 2 from list");
            }
            if (temp.length > 2) {
                throw new DoneException("Too many arguements! Example of input would be 'done 2' which checks item number 2 from list");
            }
            int listNumber = Integer.parseInt(temp[1]);
            return new DoneCommand(input, listNumber);
        } else if (input.startsWith("deadline")) {
            // when user inputs a deadline

            String[] temp = input.split("/");
            String[] commandTemp = temp[0].split(" ");
            String command = commandTemp[0];
            if (temp.length != 2) {
                if (temp.length > 2) {
                    // if user ended up using a dd/mm/yyyy HHHH format
                    throw new DeadlineException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                            "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
                } else {
                    throw new DeadlineException("Both time description and description of a deadline must be filled!");
                }
            }

            String description = "";
            for (int i = 1; i < commandTemp.length; i++) {
                description += commandTemp[i] + " ";
            }


            String[] timeTemp = temp[1].split(" ");

            if (timeTemp.length != 3) {
                // timeTemp[0] = timeCommand which is a by or at
                // timeTemp[1] = DDMMYYYY
                // timeTemp[2] = HHHH
                throw new DeadlineException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                        "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            }

            String timeCommand = timeTemp[0];
            String DDMMYYYY = timeTemp[1];
            String[] DDMMYYYtemp = DDMMYYYY.split("-");
            String HHHH = timeTemp[2];

            if (DDMMYYYtemp.length != 3 || HHHH.length() != 4) {
                throw new DeadlineException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                        "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            }

            int day = Integer.parseInt(DDMMYYYtemp[0]);
            int month = Integer.parseInt(DDMMYYYtemp[1]);
            int year = Integer.parseInt(DDMMYYYtemp[2]);
            int hour = Integer.parseInt(HHHH.substring(0, 2));
            int min = Integer.parseInt(HHHH.substring(2, 4));

            return new DeadlineCommand(description, day, month, year, hour, min);
        } else if (input.startsWith("event")) {
            // when user inputs an event

            String[] temp = input.split("/");
            String[] commandTemp = temp[0].split(" ");
            String command = commandTemp[0];
            if (temp.length != 2) {
                if (temp.length > 2) {
                    // if user ended up using a dd/mm/yyyy HHHH format
                    throw new EventException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                            "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
                } else {
                    throw new EventException("Both time description and description of a event must be filled!");
                }
            }

            String description = "";
            for (int i = 1; i < commandTemp.length; i++) {
                description += commandTemp[i] + " ";
            }


            String[] timeTemp = temp[1].split(" ");

            if (timeTemp.length != 3) {
                // timeTemp[0] = timeCommand which is a by or at
                // timeTemp[1] = DDMMYYYY
                // timeTemp[2] = HHHH
                throw new EventException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                        "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            }

            String timeCommand = timeTemp[0];
            String DDMMYYYY = timeTemp[1];
            String[] DDMMYYYtemp = DDMMYYYY.split("-");
            String HHHH = timeTemp[2];

            if (DDMMYYYtemp.length != 3 || HHHH.length() != 4) {
                throw new EventException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                        "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
            }

            int day = Integer.parseInt(DDMMYYYtemp[0]);
            int month = Integer.parseInt(DDMMYYYtemp[1]);
            int year = Integer.parseInt(DDMMYYYtemp[2]);
            int hour = Integer.parseInt(HHHH.substring(0, 2));
            int min = Integer.parseInt(HHHH.substring(2, 4));

            return new EventCommand(description, day, month, year, hour, min);
        } else if (input.startsWith("todo")) {
            // when user inputs a todo

            String[] temp = input.split(" ");

            String command = temp[0];
            String description = "";

            for(int i = 1; i < temp.length; i++) {
                description += temp[i] + " ";
            }

            if (description.equals("")) {
                throw new ToDoException("The description of a todo cannot be empty!");
            }

            return new ToDoCommand(description);
        } else if (input.startsWith("delete")) {
            // if input starts with delete

            String[] temp = input.split(" ");
            if (temp.length == 1) {
                throw new DeletionException("Please input index after delete! Example of input would be 'delete 1' which deletes 1st item from list");
            }
            if (temp.length > 2) {
                throw new DeletionException("Too many arguments! Example of input would be 'delete 1' which deletes 1st item from list");
            }

            String command = temp[0];
            int index = Integer.parseInt(temp[1]) - 1;

            return new DeleteCommand(input, index);
        } else if (input.startsWith("find")) {
            // when user inputs find
            String[] temp = input.split(" ");
            if (temp.length == 1) {
                throw new FindException("Please input keyword after done! "
                        + "Example of input would be 'find book' which returns tasks with book in it");
            }

            // command == find
            String command = temp[0];
            String keyword = "";

            for (int i = 1; i < temp.length; i++) {
                if (i == temp.length - 1) {
                    keyword += temp[i];
                } else {
                    keyword += temp[i] + " ";
                }
            }

            return new FindCommand(keyword);
        } else {
            // invalid input
            return new InvalidCommand(input);
        }
    }
}
