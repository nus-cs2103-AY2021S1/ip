package dukeclass;

import dukeexception.InvalidInputException;

import java.util.Arrays;
import java.util.Scanner;

public class Parser {


    /**
     * Parse the input String by the user and returns a task.
     *
     * @param userInput A single line of entry from the user.
     * @return Task of a specific type e.g. todo, deadline.
     * @throws InvalidInputException  If the user did not enter the entry in the correct format.
     */
    public static Task parseTask(String userInput) throws InvalidInputException {
        String[] splitString = userInput.split(" ");
        int splitStringLength = splitString.length;


        if (splitString[0].equals("todo")) {

            if (splitString.length == 1) {
                throw new InvalidInputException("Description of To Do cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                return new Todo(String.join(" ", taskStringArray));
            }

        } else if (splitString[0].equals("deadline")) {

            if (splitString.length == 1) {
                throw new InvalidInputException("Description of Deadline cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                String toSplit = String.join(" ", taskStringArray);
                int indexOfSlash = toSplit.indexOf("/");
                if (indexOfSlash == -1) {
                    throw new InvalidInputException("Date/time for Deadline cannot be empty/not recognised");
                } else {
                    int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
                    return new Deadline(toSplit.substring(0, indexOfSlash),
                            toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                            toSplit.substring(indexOfNextWord + 1));
                }
            }

        } else if (splitString[0].equals("event")) {
            if (splitString.length == 1) {
                throw new InvalidInputException("Description of Event cannot be empty");
            } else {
                String[] taskStringArray = Arrays.copyOfRange(splitString, 1, splitStringLength);
                String toSplit = String.join(" ", taskStringArray);
                int indexOfSlash = toSplit.indexOf("/");
                if (indexOfSlash == -1) {
                    throw new InvalidInputException("Date/time for Event cannot be empty/not recognised");
                } else {
                    int indexOfNextWord = toSplit.indexOf(" ", indexOfSlash);
                    return new Event(toSplit.substring(0, indexOfSlash - 1),
                            toSplit.substring(indexOfSlash + 1, indexOfNextWord),
                            toSplit.substring(indexOfNextWord + 1));
                }
            }
        } else {
            throw new InvalidInputException("Unrecognized command");
        }
    }

    /**
     * Continually parse the input String by the user until an ending command is given.
     *
     * @param list of tasks.
     * @return list of a tasks
     * @throws InvalidInputException  If the user did not enter the entry in the correct format.
     */
    public static String parseCommands(TaskList list, String userInput) throws InvalidInputException {

        try {
            String[] splitString = userInput.split(" ");

            assert splitString.length > 0;

            if (splitString[0].equals("help")) {

                return Ui.helpMessage();

            } else if (splitString[0].equals("list")) {

                return Ui.printTaskList(list);

            } else if (splitString[0].equals("done")) {
                //print OG list
                // can add error handling exception in case out of bounds
                // can add error handling for exception already done
                int index = Integer.parseInt(splitString[1]);
                list.get(index - 1).setStatus(true);

                return Ui.printTaskList(list);

            } else if (splitString[0].equals("delete")) {
                // can add error handling exception in case out of bounds
                int index = Integer.parseInt(splitString[1]);
                list.remove(index - 1);

                return Ui.printTaskList(list);

            } else if (splitString[0].equals("find")) {

                TaskList tempList = new TaskList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).toString().contains(splitString[1])) {
                        tempList.add(list.get(i));
                    }
                }
                return tempList.toString();

            } else if (splitString[0].equals("snooze")) {

                if (splitString.length == 2) {
                    int index = Integer.parseInt(splitString[1]) - 1;
                    Task newTask = list.get(index).snoozeTask();
                    list.set(index, newTask);

                } else if (splitString.length == 3) {
                    int index = Integer.parseInt(splitString[1]) - 1;
                    int days = Integer.parseInt(splitString[2]);
                    Task newTask = list.get(index).snoozeTask(days);
                    list.set(index, newTask);

                } else {
                    //invalid input exception
                    assert splitString.length < 2 || splitString.length > 3;
                }

                return Ui.printTaskList(list);

            } else {
                Task currTask = Parser.parseTask(userInput);
                list.add(currTask);
                return Ui.printTask(currTask);

            }
            //assert false;

        } catch (Exception e) {
            return Ui.unknownInputErrorMessage(e);
        }

    }
}
