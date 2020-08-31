package dukeclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {



    /**
     * Parse the input String by the user and returns a task.
     *
     * @param rawInput A single line of entry from the user.
     * @return Task of a specific type e.g. todo, deadline.
     * @throws InvalidInputException  If the user did not enter the entry in the correct format.
     */
    public static Task parseTask(String rawInput) throws InvalidInputException{
        String[] splitString = rawInput.split(" ");
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

    public static TaskList parseCommands(TaskList list) throws InvalidInputException {

        Scanner sc = new Scanner(System.in);

        String rawInput = sc.nextLine();

        while (!rawInput.equals("bye")) {

            try {
                String[] splitString = rawInput.split(" ");


                if (splitString[0].equals("list")) {

                    System.out.println(Ui.printTaskList(list));

                } else if (splitString[0].equals("done")) {
                    //print OG list
                    // can add error handling exception in case out of bounds
                    // can add error handling for exception already done
                    int index = Integer.parseInt(splitString[1]);
                    list.get(index - 1).setStatus(true);

                    System.out.println(Ui.printTaskList(list));

                } else if (splitString[0].equals("delete")) {
                    // can add error handling exception in case out of bounds
                    int index = Integer.parseInt(splitString[1]);
                    list.remove(index - 1);

                    System.out.println(Ui.printTaskList(list));

                } else if (splitString[0].equals("find")) {

                    TaskList tempList = new TaskList();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).toString().contains(splitString[1])) {
                            tempList.add(list.get(i));
                        }
                    }
                    System.out.println(tempList.toString());

                } else {
                    Task currTask = Parser.parseTask(rawInput);
                    list.add(currTask);
                    System.out.println(Ui.printTask(currTask));

                }

            } catch (Exception e) {
                System.out.println(Ui.unknownInputErrorMessage(e));
            }

            rawInput = sc.nextLine();
        }

        sc.close();

        return list;

    }
}
