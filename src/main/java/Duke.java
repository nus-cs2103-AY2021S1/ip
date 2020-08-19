import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> items;

    private static final String DONE = "done";
    private static final String BYE = "bye";
    private static final String LIST = "list";

    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String TODO = "todo";

    private static final String EMPTY = "";


    public static void main(String[] args) {
        greeting();
        getUserInput();
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        items = new ArrayList<>();

        String userInput;

        while (!exit) {
            userInput = sc.nextLine();
            lineBreak();
            if (userInput.equals(BYE)) {
                exit = true;
                exit();
            } else if (userInput.equals(LIST)) {
                displayList();
            } else {
                String[] inputArr = userInput.split(" ");
                try {
                    if (inputArr[0].equals(DONE)) {
                        int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                        markAsDone(itemsIdx);
                    } else {
                        addItem(userInput);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void greeting() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void displayList() {
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : items) {
            System.out.println(index + ". " + t);
            index++;
        }
    }

    private static void addItem(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String taskType = inputArr[0];
        String taskDescription = inputArr[1];

        Task newTask;

        switch (taskType) {
            case TODO:
                if (taskDescription.equals(EMPTY)) {
                    throw new DukeException("Please key in a valid name for To Do");
                }
                newTask = new Todo(taskDescription);
                break;
            case DEADLINE:
                String[] deadlineArr = taskDescription.split("/by", 2);
                if (deadlineArr.length != 2) {
                    throw new DukeException("Please key in a valid name and date for the Deadline");
                }
                String deadlineName = deadlineArr[0];
                String deadlineDate = deadlineArr[1];

                if (deadlineDate.equals(EMPTY)) {
                    throw new DukeException("Please key in a valid date for the Deadline");
                }
                newTask = new Deadline(deadlineName, deadlineDate);
                break;
            case EVENT:
                String[] eventArr = taskDescription.split("/at", 2);
                if (eventArr.length != 2) {
                    throw new DukeException("Please key in a valid name and date for the Event");
                }
                String eventName = eventArr[0];
                String eventDuration = eventArr[1];

                if (eventDuration.equals(EMPTY)) {
                    throw new DukeException("Please key in a valid duration for the Event");
                }
                newTask = new Event(eventName, eventDuration);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        items.add(newTask);
        String taskText = "Got it. I've added this task:" + "\n" + newTask + "\n";
        String totalText = "Now you have " + items.size() + " tasks in the list";
        System.out.println(taskText + totalText);
    }

    private static void markAsDone(int itemsIdx) throws DukeException{
        if (itemsIdx < 0 || itemsIdx > items.size() - 1) {
            throw new DukeException("Sorry, the task does not exist");
        } else {
            items.get(itemsIdx).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(items.get(itemsIdx));
        }
    }

    private static void lineBreak() {
        System.out.println("---");
    }

}
