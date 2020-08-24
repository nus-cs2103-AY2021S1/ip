import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

public class Duke {
    File dataFile;

    public static void main(String[] args) {
        String name = "Omega";
        Duke.printHorizontalLine();
        System.out.println("Hi! I am " + name + ", your personal assistant.");
        System.out.println("How may I help you today?");
        Duke.printHorizontalLine();
        new Duke().interactWithUser();
    }

    private ArrayList<Task> loadTaskList() throws IOException {
        this.dataFile = new File("data/duke.txt");
        if (dataFile.exists()) {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner scn = new Scanner(dataFile);
            while (scn.hasNextLine()) {
                String[] data = scn.nextLine().split("/");
                switch (data[0]) {
                    case "T":
                        taskList.add(new Todo(data[2], data[1].equals("1")));
                        break;
                    case "E":
                        taskList.add(new Event(data[2], data[1].equals("1"), data[3]));
                        break;
                    case "D":
                        taskList.add(new Deadline(data[2], data[1].equals("1"), data[3]));
                        break;
                }
            }
            scn.close();
            return taskList;
        } else {
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
            return new ArrayList<>();
        }
    }

    private void interactWithUser() {
        boolean exitProgram = false;
        List<Task> listOfTasks;
        try {
             listOfTasks = this.loadTaskList();
        } catch (IOException e) {
            System.out.println("Sorry this is an error loading the data");
            return;
        }
        Scanner scn = new Scanner(System.in);
        while (!exitProgram) {
            System.out.println();
            if (!scn.hasNext()) {
                exitProgram = true;
            } else {
                String userInput = scn.nextLine();
                String[] userInputArgs = userInput.split(" ");
                Duke.printHorizontalLine();
                try {
                    if (userInput.equals(Command.BYE.getName())) {
                        System.out.println("Goodbye! Shutting down now...");
                        exitProgram = true;
                    } else if (userInput.equals(Command.LIST.getName())) {
                        int n = listOfTasks.size();
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < n; i++) {
                            System.out.println(String.format("%d.%s", i + 1, listOfTasks.get(i)));
                        }
                    } else if (userInputArgs[0].equals(Command.DONE.getName())) {
                        Task task = markTaskDone(listOfTasks, userInputArgs);
                        System.out.println("Nice! I've marked this task as done:");
                        Duke.printWithIndent(task.toString());
                    } else if (userInputArgs[0].equals(Command.DELETE.getName())) {
                        Task task = deleteTask(listOfTasks, userInputArgs);
                        System.out.println("Noted. I've removed this task:");
                        Duke.printWithIndent(task.toString());
                        System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.size()));
                    } else {
                        Task task = createTask(userInputArgs);
                        listOfTasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        Duke.printWithIndent(task.toString());
                        System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.size()));
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                Duke.printHorizontalLine();
            }
        }
    }

    private static Task markTaskDone(List<Task> listOfTasks, String[] userInputArgs) throws DukeException {
        try {
            int idx = Integer.parseInt(userInputArgs[1]);
            if (idx <= 0 || idx > listOfTasks.size()) {
                throw new DukeException("The task cannot be found.");
            }
            Task task = listOfTasks.get(idx - 1);
            task.markAsDone();
            return task;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The number of the task to be marked as done has to be provided.");
        }
    }

    private static Task deleteTask(List<Task> listOfTasks, String[] userInputArgs) throws DukeException {
        try {
            int idx = Integer.parseInt(userInputArgs[1]);
            if (idx <= 0 || idx > listOfTasks.size()) {
                throw new DukeException("The task cannot be found.");
            }
            Task task = listOfTasks.get(idx - 1);
            listOfTasks.remove(idx - 1);
            return task;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The number of the task to be deleted has to be provided.");
        }
    }

    private static Task createTask(String[] userInputArgs) throws DukeException {
        if (userInputArgs[0].equals(Command.TODO.getName())) {
            String description = Duke.reassembleString(userInputArgs, 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new Todo(description);
        } else if (userInputArgs[0].equals(Command.DEADLINE.getName())) {
            int byIdx = Arrays.asList(userInputArgs).indexOf("/by");
            if (byIdx < 0) {
                throw new DukeException("The deadline date has to be provided to the deadline task.");
            }
            String description = Duke.reassembleString(userInputArgs, 1, byIdx);
            String by = Duke.reassembleString(userInputArgs, byIdx + 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (by.equals("")) {
                throw new DukeException("The date of a deadline cannot be empty.");
            } else {
                return new Deadline(description, by);
            }
        } else if (userInputArgs[0].equals(Command.EVENT.getName())) {
            int atIdx = Arrays.asList(userInputArgs).indexOf("/at");
            if (atIdx < 0) {
                throw new DukeException("The event date has to be provided to the event task.");
            }
            String description = Duke.reassembleString(userInputArgs, 1, atIdx);
            String at = Duke.reassembleString(userInputArgs, atIdx + 1, userInputArgs.length);
            if (description.equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (at.equals("")) {
                throw new DukeException("The date of an event cannot be empty.");
            } else {
                return new Event(description, at);
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means...");
        }
    }

    private static String reassembleString(String[] arr, int from, int to) {
        return String.join(" ", Arrays.copyOfRange(arr, from, to));
    }

    private static void printHorizontalLine() {
        String horizontalLine = "---------------------------------------------";
        System.out.println(horizontalLine);
    }

    private static void printWithIndent(String str) {
        System.out.println("  " + str);
    }
}
