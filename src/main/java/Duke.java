import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> items;

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
            if (userInput.equals(Instruction.BYE.getInstruction())) {
                exit = true;
                exit();
            } else if (userInput.equals(Instruction.LIST.getInstruction())) {
                displayList();
            } else {
                String[] inputArr = userInput.split(" ");
                try {
                    if (inputArr[0].equals(Instruction.DONE.getInstruction())) {
                        int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                        markAsDone(itemsIdx);
                    } else if (inputArr[0].equals(Instruction.DELETE.getInstruction())) {
                        int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                        deleteItem(itemsIdx);
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        if (taskType.equals(Instruction.TODO.getInstruction())){
            if (taskDescription.equals(Instruction.EMPTY.getInstruction())) {
                throw new DukeException("Please key in a valid name for To Do");
            }
            newTask = new Todo(taskDescription);
        } else if (taskType.equals(Instruction.DEADLINE.getInstruction())) {
            String[] deadlineArr = taskDescription.split(" /by ", 2);
            if (deadlineArr.length != 2) {
                throw new DukeException("Please key in a valid name and date for the Deadline");
            }
            String deadlineName = deadlineArr[0];
            String deadlineDateTime = deadlineArr[1];

            if (deadlineDateTime.equals(Instruction.EMPTY.getInstruction())) {
                throw new DukeException("Please key in a valid date and time for the Deadline");
            }

            LocalDateTime localDateTime = LocalDateTime.parse(deadlineDateTime, formatter);
            newTask = new Deadline(deadlineName, localDateTime);

        } else if (taskType.equals(Instruction.EVENT.getInstruction())) {
            String[] eventArr = taskDescription.split(" /at ", 2);
            if (eventArr.length != 2) {
                throw new DukeException("Please key in a valid name and date for the Event");
            }
            String eventName = eventArr[0];
            String eventDuration = eventArr[1];

            if (eventDuration.equals(Instruction.EMPTY.getInstruction())) {
                throw new DukeException("Please key in a valid duration for the Event");
            }
            LocalDateTime localDateTime = LocalDateTime.parse(eventDuration, formatter);
            newTask = new Event(eventName, localDateTime);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        items.add(newTask);
        String taskText = "Got it. I've added this task:" + "\n" + newTask + "\n";
        String totalText = "Now you have " + items.size() + " tasks in the list";
        System.out.println(taskText + totalText);
    }

    private static void deleteItem(int itemsIdx) throws DukeException{
        if (itemsIdx < 0 || itemsIdx > items.size() - 1) {
            throw new DukeException("Sorry, the task does not exist");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(items.get(itemsIdx));

            items.remove(itemsIdx);

            System.out.println("Now you have " + items.size() + " tasks in the list");
        }
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
