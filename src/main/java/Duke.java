import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {

    public static void emptyStringChecker(String taskString, boolean isTaskName) throws DukeException{
        if (taskString.equals("")) {
            if (isTaskName) {
                throw new DukeException("☹ OOPS!!! The description of a task cannot be empty. ");
            } else {
                throw new DukeException("☹ OOPS!!! The time of a task cannot be empty. ");
            }
        }
    }

    public static int listCheck(ArrayList<Task> taskList, String[] taskString) throws DukeException{
        if (taskString.length <= 1) {
            throw new DukeException("Oops, please enter a task number after your command!");
        }
        try {
            int taskNumber = Integer.parseInt(taskString[1]);
            if (taskNumber <= 0 || taskNumber > taskList.size()) {
                throw new DukeException("Oops, enter a task number that already exists in the list.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Task Number must be an Integer!");
        }
    }

    public static void printList(ArrayList<Task> taskList) {
        System.out.print("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Here are the tasks in your list:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print("\n\t " + (i + 1) + "." +  taskList.get(i).toString());
        }
        System.out.print("\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0\n");
    }

    public static void markDone(ArrayList<Task> taskList, String[] taskString) throws DukeException{
        Task taskToMarkDone = taskList.get(listCheck(taskList, taskString) -1);
        taskToMarkDone.markDone();
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Nice! I've marked this task as done: " +
                "\n\t   " + taskToMarkDone.toString() +
                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");

    }

    public static void removeTask(ArrayList<Task> taskList, String[] taskString) throws DukeException{
        int taskIndex = listCheck(taskList, taskString) -1;
        Task taskToDelete = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Noted. I've removed this task: " +
                "\n\t   " + taskToDelete.toString() +
                "\n\t Now you have " + taskList.size() + " tasks in the list." +
                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
    }

    public static String processTaskName(String taskString, String delimeter) throws DukeException {
        String taskName =  Arrays.stream(taskString.split(" ")).takeWhile(e -> !e.equals(delimeter)).collect(Collectors.joining(" "));
        emptyStringChecker(taskName,true);
        return taskName;
    }

    public static String processTaskTime(String taskString, String delimeter) throws DukeException {
        String time = Arrays.stream(taskString.split(" ")).dropWhile(e -> !e.equals(delimeter)).skip(1).collect(Collectors.joining(" "));
        emptyStringChecker(time,false);
        return time;
    }

    public static void addTask(String taskString, String taskType, ArrayList<Task> taskList) throws DukeException {
        emptyStringChecker(taskString, true);
        Task taskToAdd;
        if (taskType.equals("todo")) {
            taskToAdd = new Todo(taskString);
            taskList.add(taskToAdd);
        } else if (taskType.equals("deadline")) {
            String taskName =  processTaskName(taskString, "/by");
            String deadline = processTaskTime(taskString,"/by");
            taskToAdd = new Deadline(taskName, deadline);
            taskList.add(taskToAdd);
        } else {
            String taskName =  processTaskName(taskString, "/at");
            String time = processTaskTime(taskString,"/at");
            taskToAdd = new Event(taskName, time);
            taskList.add(taskToAdd);
        }
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Got it. I've added this task: " + "\n\t  " + taskToAdd.toString() +
                "\n\t Now you have " + taskList.size() + " tasks in the list." +
                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ \uD83D\uDD34 \uD83D\uDD34 \\\n"
                + "| |_| | |_| |   <  \\__/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);

        System.out.println("\nWhat can I do for you?");

        ArrayList<Task> inputStore = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] splitString = userInput.split(" ");
            if (splitString[0].equals("bye")) {
                System.out.println("\u263A Bye. Hope to see you again soon!");
                break;
            }
            switch(splitString[0]) {
                case "list" :
                    printList(inputStore);
                    break;

                case "todo" :
                    String todo =  Arrays.stream(splitString).skip(1).collect(Collectors.joining(" "));
                    addTask(todo, "todo", inputStore);
                    break;

                case "deadline" :
                    String deadlineNameTime = Arrays.stream(splitString).skip(1).collect(Collectors.joining(" "));
                    addTask(deadlineNameTime, "deadline", inputStore);
                    break;

                case "event" :
                    String eventNameTime = Arrays.stream(splitString).skip(1).collect(Collectors.joining(" "));
                    addTask(eventNameTime, "event", inputStore);
                    break;

                case "done" :
                    markDone(inputStore, splitString);
                    break;

                case "delete" :
                    removeTask(inputStore, splitString);
                    break;

                case "" :
                    System.out.println("Please enter something! Or say bye to exit!");
                    break;

                default :
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
