import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StringProcessor {

    protected String[] splitString;
    protected ArrayList<Task> taskList;

    StringProcessor(String[] splitString, ArrayList<Task> taskList) {
        this.splitString = splitString;
        this.taskList = taskList;
    }

    //Checks Task Number is specified and returns task number provided it is not > no. of total tasks
    public int listCheck() throws DukeException {
        if (isIndexEmpty(1)) {
            throw new DukeException("Oops, please enter a task number after your command!");
        }
        try {
            int taskNumber = Integer.parseInt(returnIndex(1));
            if (taskNumber <= 0 || taskNumber > totalTask()) {
                throw new DukeException("Oops, enter a task number that already exists in the list.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Task Number must be an Integer!");
        }
    }

    public void printList() {
        System.out.print("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Here are the tasks in your list:");

        for (int i = 0; i < totalTask(); i++) {
            System.out.print("\n\t " + (i + 1) + "." + getTask(i).toString());
        }
        System.out.print("\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0\n");
    }

    public void markDone() throws DukeException {
        Task taskToMarkDone = getTask(listCheck() - 1);
        taskToMarkDone.markDone();
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Nice! I've marked this task as done: " +
                "\n\t   " + taskToMarkDone.toString() +
                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");

    }

    public void removeTask() throws DukeException {
        int taskIndex = listCheck() - 1;
        Task taskToDelete = getTask(taskIndex);
        deleteTask(taskIndex);
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Noted. I've removed this task: " +
                "\n\t   " + taskToDelete.toString() +
                "\n\t Now you have " + totalTask() + " tasks in the list." +
                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
    }

    //drops first element of array and delimeter to get array containing task name, date and time only
    public ArrayList<String> processTask(String delimeter) throws DukeException {
        //Task Name Only
        String taskName = Arrays.stream(getString()).takeWhile(e -> !e.equals(delimeter)).skip(1)
                .collect(Collectors.joining(" "));
        //Date + Time, each in a single array cell
        String[] dateTime = Arrays.stream(getString()).dropWhile(e -> !e.equals(delimeter)).skip(1)
                .collect(Collectors.joining(" ")).split(" ");
        //Makes sure Task Name is available first, otherwise throw exception
        if (taskName.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        //Array to store 3 fields - task name, date and time (if available)
        ArrayList<String> newArray = new ArrayList<>();
        //TaskName
        newArray.add(taskName);

        //More than necessary words or date and time in wrong format
        if (dateTime.length > 2) {
            throw new DukeException("Please make sure date is inputed in yyyy-mm-dd format. Any optional time" +
                    " parameter should be in HHmm format. Don't add any more characters after the date and time!");
        }
        //Append date and time into newArray if they exist
        for (int i = 0; i < dateTime.length; i++) {
            if (!dateTime[i].equals("")) {
                newArray.add(dateTime[i]);
            }
        }
        return newArray;
    }

    public void addTask(ArrayList<String> taskArray, String taskType, boolean isDone) throws DukeException {
        if (taskType.equals("todo")) {
            addTaskToList(new Todo(taskArray.get(0), isDone));
            return;
        }
        //Deadline / Event Tasks
        if (taskArray.size() < 2) {
                throw new DukeException("All deadline/event tasks must come with a date in yyyy-mm-dd format!");
        }
        try {
            LocalDate taskDate = LocalDate.parse(taskArray.get(1), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //Make sure deadline set is in the future
            LocalDate todayDate = LocalDate.now();
            if (taskDate.isBefore(todayDate)) {
                throw new DukeException("Date for deadline/event tasks must be set in the future!");
            }
            //Time Exists
            if (taskArray.size() > 2) {
                LocalTime taskTime = LocalTime.parse(taskArray.get(2), DateTimeFormatter.ofPattern("HHmm"));
                //Make sure time has not passed
                LocalTime timeNow = LocalTime.now();
                if (todayDate.isEqual(taskDate) && taskTime.isBefore(timeNow)) {
                    throw new DukeException("The date/time combination you specified has already passed!");
                }
                addTaskToList(taskType.equals("deadline")
                        ? new Deadline(taskArray.get(0), taskDate, taskTime, isDone)
                        : new Event(taskArray.get(0), taskDate, taskTime, isDone));
            } else {
                addTaskToList(taskType.equals("deadline") ? new Deadline(taskArray.get(0), taskDate, isDone)
                        : new Event(taskArray.get(0), taskDate, isDone));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("All deadline/event tasks' date must be n yyyy-mm-dd format" +
                    " and any time specified must be in HHmm format! (i.e. 2021-10-05 1800)");
        }
    }

    public void printAddTask() {
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0" +
                "\n\t Got it. I've added this task: " + "\n\t  " + getTask(totalTask() - 1).toString() +
                "\n\t Now you have " + totalTask() + " tasks in the list." +
                "\n\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
    }

    public void process(boolean isDone) throws DukeException {
        //String will not be empty as this is checked in Duke
        switch(returnIndex(0)) {
        case "list" :
            printList();
            break;

        case "todo" :
            addTask(processTask(""), "todo", isDone);
            printAddTask();
            break;

        case "deadline" :
            addTask(processTask("/by"), "deadline", isDone);
            printAddTask();
            break;

        case "event" :
            addTask(processTask("/at"), "event", isDone);
            printAddTask();
            break;

        case "done" :
            markDone();
            break;

        case "delete" :
            removeTask();
            break;

        default :
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public boolean isIndexEmpty(int index) {
        //If String array is already shorter than index, simply return true
        if (getStringLength() <= index) {
            return true;
        }
        return returnIndex(index).equals("");
    }

    public String[] getString() {
        return this.splitString;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getStringLength() {
        return getString().length;
    }

    public String returnIndex(int index) {
        return getString()[index];
    }

    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    public void deleteTask(int index) {
        getTaskList().remove(index);
    }

    public void addTaskToList(Task task) {
        getTaskList().add(task);
    }

    public int totalTask() {
        return getTaskList().size();
    }
}
