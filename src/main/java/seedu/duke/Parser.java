package seedu.duke;

import java.security.InvalidParameterException;

/**
 * Parses user input string to objects.
 */
public class Parser {

    /**
     * Default constructor.
     */
    public Parser() {
    }

    /**
     * Parses user input string represent Todo/Deadline/Event object to actual object.
     *
     * @param resource User input string.
     * @return Represented object of user input string.
     * @throws InvalidParameterException If user input string is in wrong format.
     */
    public Task parseTask(String resource) throws InvalidParameterException {
        Task result;
        if (resource.startsWith("todo")) {
            try {
                if (resource.substring(5).length() == 0) {
                    throw new Exception();
                }
                Todo newTodo = new Todo(resource.substring(5));
                result = newTodo;

            } catch (Exception e) {
                throw new InvalidParameterException();
            }
        } else if (resource.startsWith("deadline")) {
            try {
                String[] splited = resource.substring(9).split("/");
                Deadline newDeadline = new Deadline(splited[0], splited[1].replace(" ", ""));
                result = newDeadline;
            } catch (Exception e) {
                throw new InvalidParameterException();
            }
        } else if (resource.startsWith("event")) {
            try {
                String[] splited = resource.substring(6).split("/");
                Event newEvent = new Event(splited[0], splited[1].replace(" ", ""));
                result = newEvent;
            } catch (Exception e) {
                throw new InvalidParameterException();
            }
        } else {
            throw new InvalidParameterException();
        }
        assert result != null; //Ensure parser task result is not null.
        return result;
    }

    /**
     * Parses the done order from user.
     * Gives the index of target task.
     *
     * @param resource User input string representing done order.
     * @param taskList TaskList object of the program.
     * @return Index of target task in taskList.
     * @throws IndexOutOfBoundsException If user inputs an index not in task list.
     * @throws Exception                 If user inputs done order in wrong format.
     */
    public int parseDoneOrder(String resource, TaskList taskList) throws IndexOutOfBoundsException, Exception {
        if (resource.length() <= 4) {
            throw new Exception();
        }
        String numberString = resource.substring(5).replace(" ", "");
        try {
            int doneNo = Integer.parseInt(numberString);
            if (doneNo <= 0 || doneNo - 1 >= taskList.taskCount()) {
                throw new IndexOutOfBoundsException();
            } else {
                return doneNo - 1;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Parses the delete order from user.
     * Gives the index of target task.
     *
     * @param resource User input string representing delete order.
     * @param taskList TaskList object of the program.
     * @return Index of deleting task in taskList.
     * @throws IndexOutOfBoundsException If user inputs an index not in task list.
     * @throws Exception                 If user inputs deleting order in wrong format.
     */
    public int parseDeleteOrder(String resource, TaskList taskList) throws IndexOutOfBoundsException, Exception {
        if (resource.length() <= 6) {
            throw new Exception();
        }
        String numberString = resource.substring(7).replace(" ", "");
        try {
            int deletingNumber = Integer.parseInt(numberString);
            if (deletingNumber <= 0 || deletingNumber - 1 >= taskList.taskCount()) {
                throw new IndexOutOfBoundsException();
            } else {
                return deletingNumber - 1;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Parses the find Order from user.
     * Gives the keyword.
     *
     * @param resource User input string representing find order.
     * @return Keyword user is searching for.
     * @throws InvalidParameterException If there is no keyword followed.
     */
    public String parseFindOrder(String resource) throws InvalidParameterException {
        if (resource.length() <= 5) {
            throw new InvalidParameterException();
        }
        String findingKeyWord = resource.substring(5);
        assert !findingKeyWord.isEmpty(); //Ensure the keyword is not empty.
        return findingKeyWord;
    }

    public String parseSwitch(String userInput) throws InvalidParameterException {
        String result = userInput.substring(7);
        if (result.length() <= 0) {
            throw new InvalidParameterException();
        }
        return "./data/" + result + ".txt";
    }
}
