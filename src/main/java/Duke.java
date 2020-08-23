import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    private static void printTasks(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).display());
        }
    }

    private static ArrayList<Task> handleDoneInput(String input, ArrayList<Task> list) throws DukeException{
        int index;
        if (!input.substring(4).trim().isEmpty()
                && input.substring(4).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
            index = Integer.parseInt(input.substring(4).trim()); //convert string to integer
            if (index >= 1) { //if input index is valid
                Task newTask = list.get(index - 1).markAsDone();
                list.set(index - 1, newTask);
                System.out.println("Nice! I've marked this task as doneee! yayy:\n" + newTask);
                return list;
            } else {
                throw new DukeException("Please enter a valid task number to mark as done (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to mark as done (substring doesn't match regex)");
        }
    }

    private static ArrayList<Task> handleDeleteInput(String input, ArrayList<Task> list) throws DukeException {
        int index;
        if (!input.substring(6).trim().isEmpty()
                && input.substring(6).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
            index = Integer.parseInt(input.substring(6).trim()); //convert string to integer
            if (index >= 1) { //if input index is valid
                Task removed = list.get(index - 1);
                list.remove(index - 1);
                System.out.println("I have removed this task:\n" + removed.display()
                        + "\nNow you have " + list.size() + " task(s) in the list.");
                return list;
            } else {
                throw new DukeException("Please enter a valid task number to delete (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to delete (substring doesn't match regex)");
        }
    }

    private static ArrayList<Task> handleTodoInput(String input, ArrayList<Task> list) throws DukeException, IOException {
        if (!input.substring(4).trim().isEmpty()) { //to make sure to do task is not empty
            String description = input.substring(4);
            Task newTask = new Todo(description.trim());
            list.add(newTask);
            System.out.println("Got itt. I've added this task:\n    "
                    + newTask.display()
                    + "\nNow you have " + list.size() + " task(s) in the list.");
            return list;
        } else {
            throw new DukeException("Please enter a valid todo");
        }
    }

    private static ArrayList<Task> handleDeadlineInput(String input, ArrayList<Task> list) throws DukeException, IOException {
        if (!input.substring(8).trim().isEmpty() //to make sure deadline is not empty
                && input.substring(8).trim().contains("/by") //to make sure deadline contains /by
                && !input.substring(8).trim().startsWith("/by") //to make sure deadline contains a task description
                && !input.substring(8).trim().endsWith("/by")) { //to make sure deadline contains a deadline
            String descriptionAndTime = input.substring(8);
            String description = descriptionAndTime.trim().split("/by ")[0];
            String by = descriptionAndTime.trim().split("/by ")[1].trim();
            try {
                if (by.contains(" ")) { //user gave a time input
                    String dateString = by.split(" ")[0].trim();
                    String timeString = by.split(" ")[1].trim();
                    if (dateString.contains("/")) {
                        dateString = dateString.replaceAll("\\/", "-");
                    }
                    String[] dateStringArr = dateString.split("-");
                    dateString = "";
                    for (int i = 0; i < dateStringArr.length; i++) {
                        if (dateStringArr[i].length() < 2) {
                            dateStringArr[i] = "0" + dateStringArr[i];
                        }
                        if (i > 0) {
                            dateString = dateString + "-" + dateStringArr[i];
                        } else {
                            dateString = dateStringArr[i];
                        }
                    }
                    LocalDate d1 = LocalDate.parse(dateString);
                    if (timeString.length() == 4) {
                        try {
                            int time = Integer.parseInt(timeString); //convert string to integer wrap in try catch?
                            if (time >= 0000 && time <= 2359) {
                                Task newTask = new Deadline(description.trim(), d1, timeString);
                                list.add(newTask);
                                System.out.println("Got itt. I've added this task:\n    "
                                        + newTask.display()
                                        + "\nNow you have " + list.size() + " task(s) in the list.");
                                return list;
                            } else {
                                throw new DukeException("Please enter a valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the time in the right format (eg. 1800)");
                        }
                    } else {
                        Task newTask = new Deadline(description.trim(), d1);
                        list.add(newTask);
                        System.out.println("Got itt. I've added this task:\n    "
                                + newTask.display()
                                + "\nNow you have " + list.size() + " task(s) in the list.");
                        return list;
                    }
                } else { //user didn't give a time input
                    if (by.contains("/")) {
                        by = by.replaceAll("\\/", "-");
                    }
                    String[] byArr = by.split("-");
                    by = "";
                    for (int i = 0; i < byArr.length; i++) {
                        if (byArr[i].length() < 2) {
                            byArr[i] = "0" + byArr[i];
                        }
                        if (i > 0) {
                            by = by + "-" + byArr[i];
                        } else {
                            by = byArr[i];
                        }
                    }
                    LocalDate d1 = LocalDate.parse(by);
                    Task newTask = new Deadline(description.trim(), d1);
                    list.add(newTask);
                    System.out.println("Got itt. I've added this task:\n    "
                            + newTask.display()
                            + "\nNow you have " + list.size() + " task(s) in the list.");
                    return list;
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date and time in the format yyyy-mm-dd hhmm (eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid deadline");
        }
    }

    private static ArrayList<Task> handleEventInput(String input, ArrayList<Task> list) throws DukeException {
        if (!input.substring(5).trim().isEmpty() //to make sure event is not empty
                && input.substring(5).trim().contains("/at") //to make sure event contains at
                && !input.substring(5).trim().startsWith("/at") //to make sure event description is not empty
                && !input.substring(5).trim().endsWith("/at")) { //to make sure event contains a time/date
            String descriptionAndTime = input.substring(5);
            String description = descriptionAndTime.split("/at ")[0];
            String at = descriptionAndTime.split("/at ")[1].trim();
            try {
                if (at.contains(" ")) { //user gave a time input
                    String dateString = at.split(" ")[0].trim();
                    String timeString = at.split(" ")[1].trim();
                    if (dateString.contains("/")) {
                        dateString = dateString.replaceAll("\\/", "-");
                    }
                    String[] dateStringArr = dateString.split("-");
                    dateString = "";
                    for (int i = 0; i < dateStringArr.length; i++) {
                        if (dateStringArr[i].length() < 2) {
                            dateStringArr[i] = "0" + dateStringArr[i];
                        }
                        if (i > 0) {
                            dateString = dateString + "-" + dateStringArr[i];
                        } else {
                            dateString = dateStringArr[i];
                        }
                    }
                    LocalDate d2 = LocalDate.parse(dateString);
                    if (timeString.length() == 4) {
                        try {
                            int time = Integer.parseInt(timeString); //convert string to integer wrap in try catch?
                            if (time >= 0000 && time <= 2359) {
                                Task newTask = new Event(description.trim(), d2, timeString);
                                list.add(newTask);
                                System.out.println("Got itt. I've added this task:\n    "
                                        + newTask.display()
                                        + "\nNow you have " + list.size() + " task(s) in the list.");
                                return list;
                            } else {
                                throw new DukeException("Please enter a valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the time in the right format (eg. 1800)");
                        }
                    } else {
                        Task newTask = new Event(description.trim(), d2);
                        list.add(newTask);
                        System.out.println("Got itt. I've added this task:\n    "
                                + newTask.display()
                                + "\nNow you have " + list.size() + " task(s) in the list.");
                        return list;
                    }
                } else { //user didn't give a time input
                    if (at.contains("/")) {
                        at = at.replaceAll("\\/", "-");
                    }
                    String[] atArr = at.split("-");
                    at = "";
                    for (int i = 0; i < atArr.length; i++) {
                        if (atArr[i].length() < 2) {
                            atArr[i] = "0" + atArr[i];
                        }
                        if (i > 0) {
                            at = at + "-" + atArr[i];
                        } else {
                            at = atArr[i];
                        }
                    }
                    LocalDate d2 = LocalDate.parse(at);
                    Task newTask = new Event(description.trim(), d2);
                    list.add(newTask);
                    System.out.println("Got itt. I've added this task:\n    "
                            + newTask.display()
                            + "\nNow you have " + list.size() + " task(s) in the list.");
                    return list;
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date and time in the format yyyy-mm-dd hhmm (eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid event");
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Yooo, I'm Duke.\nWhat can I do for you today?"); //Greeting

        ArrayList<Task> list = FileClass.readFileContents("src/data/duke.txt");
        String input = sc.nextLine();

        try {
            while (!input.isEmpty()) {
                if (input.equalsIgnoreCase("bye")) { //if user types "bye"
                    System.out.println("Bye bye!!! See you again next time :)");
                    input = "";
                    sc.close();
                } else if (input.equalsIgnoreCase("list")) { //if user types "list"
                    System.out.println("Here are the tasks in your list:");
                    printTasks(list);
                    input = sc.nextLine();
                } else if (input.toLowerCase().startsWith("done")) { //if user input starts with "done"
                    list = handleDoneInput(input, list);
                    FileClass.writeListToFile("src/data/duke.txt", list);
                    input = sc.nextLine();
                } else if (input.toLowerCase().startsWith("delete")) {
                    list = handleDeleteInput(input, list);
                    FileClass.writeListToFile("src/data/duke.txt", list);
                    input = sc.nextLine();
                } else if (input.toLowerCase().startsWith("todo")) {
                    list = handleTodoInput(input, list);
                    FileClass.writeListToFile("src/data/duke.txt", list);
                    input = sc.nextLine();
                } else if (input.toLowerCase().startsWith("deadline")) {
                    list = handleDeadlineInput(input, list);
                    FileClass.writeListToFile("src/data/duke.txt", list);
                    input = sc.nextLine();
                } else if (input.toLowerCase().startsWith("event")) {
                    list = handleEventInput(input, list);
                    FileClass.writeListToFile("src/data/duke.txt", list);
                    input = sc.nextLine();
                } else {
                    throw new DukeException("Please enter a valid deadline");
                }
            }
        } catch (DukeException de) {
            System.out.println(de);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }
}
