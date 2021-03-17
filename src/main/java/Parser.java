import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.EmptyTodoException;
import exceptions.InvalidDateException;
import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidNumberException;
import exceptions.InvalidTaskException;
import exceptions.UnknownCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

/**
 * class consisting of static methods which are used to make sense of the user commands
 */
public class Parser {
    /**
     * Parse the user input and get the right response
     *
     * @param input String inputted by the user
     * @param list A TaskList containing all the tasks inputted by the user
     * @return String containing the response from duke
     */
    public static String getResponse(String input, TaskList list) {
        input = input.trim();
        try {
            validity(input);
        } catch (UnknownCommandException ex) {
            return "Error! Unknown command";
        }
        String[] inputs = input.split(" ");
        assert inputs.length != 0 : "Input cannot be empty";
        String str;
        switch(inputs[0]) {
        case "todo":
            try {
                str = getTodo(input, list);
            } catch (EmptyTodoException ex) {
                return "Error! Empty todo provided";
            }
            break;
        case "event":
            try {
                str = getEventTest(input, list);
            } catch (InvalidEventException e) {
                return "Error! Invalid event provided";
            }
            break;
        case "deadline":
            try {
                str = getDeadline(input, list);
            } catch (InvalidDeadlineException e) {
                return "Error! Invalid deadline given";
            }
            break;
        case "delete":
            if (inputs.length != 2) {
                str = "Error! Incorrect format provided";
            } else {
                str = delete(inputs[1], list);
            }
            break;
        case "find":
            try {
                str = find(input, list);
            } catch (InvalidNumberException e) {
                return "Error! No searches found";
            }
            break;
        case "list":
            str = list.toString();
            break;
        case "bye":
            str = "Bye!! Hope to see you soon";
            break;
        case "number":
            str = "Number of tasks to do is " + list.getSize();
            break;
        case "update":
            try {
                str = changeTask(input, list);
            } catch (InvalidTaskException ex) {
                return "Error! Invalid task provided";
            }
            break;
        default:
            if (inputs.length != 2) {
                return "Error! Invalid number of arguments";
            }
            str = update(inputs[1], list);
        }
        Storage.save(list);
        return str;
    }

    /**
     *
     * @param request
     * @param list
     * @return
     */
    public static String changeTask(String request, TaskList list) throws InvalidTaskException {
        assert !request.equals("") : "An empty string is not allowed";
        String[] requests = request.split(" ");
        if (requests.length < 4) {
            throw new InvalidTaskException();
        }
        int index = 0;
        try {
            index = Integer.parseInt(requests[1]);
            isNumeric(index, list);
        } catch (NumberFormatException e) {
            return "Error! You have not provided a valid number";
        } catch (InvalidNumberException e) {
            return "Error! The number provided was greater or lesser than the number of items in the list";
        }
        Task task = list.get(index);
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < requests.length; i++) {
            sb.append(requests[i]);
            sb.append(" ");
        }
        String updatedString = task + " has been updated to ";
        String newWork = sb.toString();
        if (requests[2].equals("date") && task.istodo()) {
            return "Not possible to change the date of a todo";
        }
        if (requests[2].equals("date")) {
            try {
                LocalDateTime date = parseDate(requests[3] + " " + requests[4]);
                task.setDate(date);
                System.out.println(task.toString());
            } catch (InvalidDateException ex) {
                return "Error! Invalid date format used";
            }
        } else {
            task.setWork(newWork);
        }

        return updatedString + task.toString();
    }

    /**
     * @param str
     * @return
     */
    public static LocalDateTime parseDate(String str) throws InvalidDateException {
        str = str.trim();
        String datestr = str.replaceAll("-", "/");
        String[] datearr = datestr.split("/");
        System.out.println(datestr);
        if (datearr.length < 2) {
            throw new InvalidDateException();
        }
        if (datearr[0].length() < 2) {
            datestr = "0" + datestr;
            datearr[0] = "0" + datearr[0];
        }
        if (datearr[1].length() < 2) {
            datestr = datearr[0] + "/0" + datearr[1] + "/" + datearr[2];
        }
        if (!datestr.contains(":")) {
            String[] arr = datestr.split(" ");
            if (arr.length != 2) {
                throw new InvalidDateException();
            }
            arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
            datestr = arr[0] + " " + arr[1];
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(datestr, formatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Removes the user requested task from the tasklist
     *
     * @param request A string containing the integer value of the index.
     * @param list The tasklist object containing all the stored tasks.
     */

    public static String delete(String request, TaskList list) {
        assert !request.equals("") : "Invalid input";
        try {
            int index = Integer.parseInt(request);
            isNumeric(index, list);
            String str = "The event has been removed as per your request: \n "
                    + list.get(index);
            list.delete(index);
            str += ". Now you have " + list.getSize() + " tasks to the list";
            return str;
        } catch (NumberFormatException e) {
            return "Error! You have not provided a valid number";
        } catch (InvalidNumberException e) {
            return "Error! The number provided was greater or lesser than the number of items in the list";
        }
    }

    /**
     * Marks the task at the user requested index as done
     *
     * @param request A String containing the integer value of the index
     * @param list The tasklist object containing all the stored tasks.
     */

    public static String update(String request, TaskList list) {
        assert !request.equals("") : "Input is invalid";
        try {
            int index = Integer.parseInt(request);
            isNumeric(index, list);
            list.updateStatus(index);
            String str = "Nice! I've marked this task as done: \n "
                    + list.get(index);
            str += ". Now you have " + list.getSize() + " tasks in the list";
            return str;
        } catch (NumberFormatException e) {
            return "You have not provided a valid number";
        } catch (InvalidNumberException e) {
            return "The number provided was greater or lesser than the "
                    + "number of items in the list";
        }
    }

    /**
     * Checking if the index provided by the user is of the right size
     *
     * @param n The index provided by the user
     * @param list The tasklist object containing all the stored tasks.
     * @throws InvalidNumberException
     */
    public static void isNumeric(int n, TaskList list) throws InvalidNumberException {
        if (list.getSize() < n || n <= 0) {
            throw new InvalidNumberException("The number provided is bigger tha the list size");
        }
    }

    /**
     * Adds a deadline object with description and datetime into the tasklist provided
     *
     * @param word The input provided by the user with the description of the deadline as well as the date and time
     * @param list The tasklist object containing all the stored tasks.
     * @return String containing the details of the deadline
     * @throws InvalidDeadlineException
     */
    public static String getDeadline(String word, TaskList list) throws InvalidDeadlineException {
        if (!(word.contains("/by") && !word.substring(word.indexOf("/by") + 3).equals(""))) {
            throw new InvalidDeadlineException();
        }
        word = word.substring(8);
        assert word.length() > 0 : "A deadline cannot be empty";
        int index = word.indexOf("/by");
        String str = word.substring(index + 3).trim();
        String datestr = str.replaceAll("-", "/");
        String[] datearr = datestr.split("/");
        if (datearr.length < 2) {
            throw new InvalidDeadlineException();
        }
        if (datearr[0].length() < 2) {
            datestr = "0" + datestr;
            datearr[0] = "0" + datearr[0];
        }
        if (datearr[1].length() < 2) {
            datestr = datearr[0] + "/0" + datearr[1] + "/" + datearr[2];
        }
        if (!datestr.contains(":")) {
            String[] arr = datestr.split(" ");
            if (arr.length > 2) {
                throw new InvalidDeadlineException();
            }
            arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
            datestr = arr[0] + " " + arr[1];
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(datestr, formatter);
            Deadline deadline = new Deadline(word.substring(0, index), date);
            list.update(deadline);
            return deadline.toString() + " has been added to the list. You have " + list.getSize() + " tasks left to do";
        } catch (DateTimeParseException e) {
            return "Error! Invalid Date provided";
        }
    }
    /**
     * Adds an event object with description and datetime into the tasklist provided
     *
     * @param word The input provided by the user with the description of the event as well as the date and time
     * @param list The tasklist object containing all the stored tasks.
     * @return String object containing the details of the event
     * @throws InvalidEventException
     */
    public static String getEventTest(String word, TaskList list) throws InvalidEventException {
        if (!(word.contains("/at") && !word.substring(word.indexOf("/at") + 3).equals(""))) {
            throw new InvalidEventException();
        }
        word = word.substring(5);
        int index = word.indexOf("/at");
        String str = word.substring(index + 3).trim();
        String datestr = str.replaceAll("-", "/");
        String[] datearr = datestr.split("/");
        if (datearr.length < 2) {
            throw new InvalidEventException();
        }
        if (datearr[0].length() < 2) {
            datestr = "0" + datestr;
            datearr[0] = "0" + datearr[0];
        }
        if (datearr[1].length() < 2) {
            datestr = datearr[0] + "/0" + datearr[1] + "/" + datearr[2];
        }
        if (!datestr.contains(":")) {
            String[] arr = datestr.split(" ");
            if (arr.length > 2) {
                throw new InvalidEventException();
            }
            arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
            datestr = arr[0] + " " + arr[1];
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(datestr, formatter);
            Event event = new Event(word.substring(0, index), date);
            list.update(event);
            return event.toString() + " has been added to the list. You have " + list.getSize() + " tasks left to do";
        } catch (DateTimeParseException e) {
            return "Error! Incorrect Date format used";
        }
    }

    /**
     * Checks if a command provided by the user is valid
     *
     * @param line The String input from the user
     * @throws UnknownCommandException
     */
    public static void validity(String line) throws UnknownCommandException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("delete", "done", "todo",
                "event", "deadline", "find", "list", "bye", "number", "update"));
        String[] words = line.split(" ");
        if (line.equals("list") || line.equals("bye") || line.equals("number")) {
            return;
        }
        if (list.contains(words[0]) && words.length > 1) {

        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Finds a task based on a keyword
     *
     * @param input A user-inputted string which is the keyword
     * @throws InvalidNumberException
     */
    public static String find(String input, TaskList list) throws InvalidNumberException {
        String[] requests = input.split(" ");
        assert requests.length != 0 : "Invalid request provided";
        ArrayList<Task> tasks = new ArrayList<>();
        if (requests.length != 2) {
            throw new InvalidNumberException("More than one keyword was provided");
        }
        for (int i = 1; i <= list.getSize(); i++) {
            if (list.get(i).getWork().contains(requests[1])) {
                tasks.add(list.get(i));
            }
        }
        return new TaskList(tasks).toString() + " contain " + requests[1];
    }

    /**
     * Adds a toDo object containing the description provided to the list of tasks
     *
     * @param work A String containing the description of the todo object that needs to be added
     * @param list The tasklist object containing all the stored tasks.
     * @return A String containing information about the todo object
     */
    public static String getTodo(String work, TaskList list) throws EmptyTodoException {
        assert work.length() != 0 : "Empty strings are not allowed";
        if (work.length() > 4) {
            ToDos todo = new ToDos(work.substring(4));
            list.update(todo);
            return todo.toString()  + " has been added to the list. You have " + list.getSize() + " tasks left to do";
        } else {
            throw new EmptyTodoException();
        }
    }
}
