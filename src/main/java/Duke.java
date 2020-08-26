import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> userInputs = new ArrayList<>();

    public static void viewTasks() throws DukeException {
        if (userInputs.size() == 0) { // user has not added any task
            throw new DukeException("Nothing has been added to the list yet!");
        } else {
            for (int i = 0; i < userInputs.size(); i++) {
                String output = (i + 1) + ". " + userInputs.get(i).toString();
                System.out.println(output);
            }
        }
    }

    public static void markDone(String inputMsg) throws DukeException {
        int taskNumber = Integer.valueOf(inputMsg.split(" ")[1]); // gets the done task number
        if (userInputs.size() < taskNumber || taskNumber <= 0) {
            throw new DukeException("There is no such task number!");
        } else {
            Task currTask = userInputs.get(taskNumber - 1);
            if (currTask.getStatus()) { // task has already marked done before
                throw new DukeException("Task has already been completed earlier on!");
            } else {
                currTask.markAsComplete();
                System.out.println("Nice! I've marked this task as done:\n" + currTask.toString());
            }
        }
    }

    public static void deleteFromList(String inputMsg) throws DukeException {
        int taskNumber = Integer.valueOf(inputMsg.split(" ")[1]); // gets the deleted task number
        if (userInputs.size() < taskNumber || taskNumber <= 0) {
            throw new DukeException("There is no such task number!");
        } else {
            Task currTask = userInputs.get(taskNumber - 1);
            userInputs.remove(taskNumber - 1);
            String output = "Noted. I've removed this task:\n"
                    + currTask.toString()
                    + "\nNow you have " + userInputs.size() + " tasks in the list.";
            System.out.println(output);
        }
    }

    public static LocalDateTime processDate(String inputDeadline) throws DukeException {
        String dateFormat;
        if (inputDeadline.contains("/")) {
            dateFormat = "/";
        } else if (inputDeadline.contains(".")) {
            dateFormat = "\\.";
        } else if (inputDeadline.contains("-")) {
            dateFormat = "-";
        } else {
            throw new DukeException("Please input valid date format!");
        }
        String[] inputData = inputDeadline.split(dateFormat);
        int date = Integer.parseInt(inputData[0]);
        int month = Integer.parseInt(inputData[1]);
        int year = Integer.parseInt(inputData[2].substring(0, 4));
        int hour = Integer.parseInt(inputData[2]. substring(5, 7));
        int min = Integer.parseInt(inputData[2]. substring(7));
        return LocalDateTime.of(year, month, date, hour, min);
    }

    public static void addToList(String inputMsg, String actionType) throws DukeException {
        Task newTask;
        int numOfWords = inputMsg.split(" ").length;
        switch (actionType) {
            case "todo": {
                if (numOfWords <= 1) {
                    throw new DukeException("Description of task cannot be empty!");
                }
                String taskName = inputMsg.substring(5);
                newTask = new Todo(taskName, false);
                break;
            }
            case "deadline": {
                if (numOfWords <= 1) {
                    throw new DukeException("Description of task cannot be empty!");
                }
                String taskName = inputMsg.split("/")[0].substring(9);
                String inputDeadline = inputMsg.split("/", 2)[1].substring(3);
                LocalDateTime deadline = processDate(inputDeadline);
                newTask = new Deadline(taskName, false, deadline);
                break;
            }
            case "event": {
                if (numOfWords <= 1) {
                    throw new DukeException("Description of task cannot be empty!");
                }
                String taskName = inputMsg.split("/")[0].substring(6);
                String inputDeadline = inputMsg.split("/", 2)[1].substring(3);
                LocalDateTime deadline = processDate(inputDeadline);
                newTask = new Event(taskName, false, deadline);
                break;
            }
            default:  // when user keys in unregistered action
                throw new DukeException("Specified action is not recognised.");
        }
        userInputs.add(newTask);
        String outputMsg = "Got it. I've added this task:\n"
                + newTask.toString()
                + "\nYou have " + userInputs.size() + " tasks in the list.";
        System.out.println(outputMsg);
    }

    public static void writeToFile() throws IOException {
        String output = "";
        if (userInputs.size() == 0) { // user has not added any task
            output = "Nothing has been added to the list yet!";
        } else {
            for (int i = 0; i < userInputs.size(); i++) {
                output += (i + 1) + ". " + userInputs.get(i).toString() + "\n";
            }
        }
        File directory = new File("./data");
        if (!directory.exists()) { // directory does not exist
            directory.mkdir();
        }
        File file = new File("./data/duke.txt");
        if (!file.exists()) { // file does not exist
            file.createNewFile();
        }
        FileWriter fw = new FileWriter("./data/duke.txt");
        fw.write(output);
        fw.close();
    }

    public static void main(String[] args) {
        String welcomeMsg = "Hello! I'm Duke, some call me a parrot.\n"
                + "What can I do for you?";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String inputMsg = sc.nextLine();
            String actionType = inputMsg.split(" ")[0]; // user specified action, to identify type of action

            if (inputMsg.equals("bye")) { // ends the bot
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputMsg.equals("list")) { // sees all tasks
                try {
                    viewTasks();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (actionType.equals("done")) { // mark task as done
                try {
                    markDone(inputMsg);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (actionType.equals("delete")) {
                try {
                    deleteFromList(inputMsg);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else { // add task to list
                try {
                    addToList(inputMsg, actionType);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}
