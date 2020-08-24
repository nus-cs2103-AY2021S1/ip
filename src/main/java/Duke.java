import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // different possible commands that can be received from the user
    private final static String DIVIDER = "__________________________________________________________";
    private final static String BYE_COMMAND = "bye";
    private final static String LIST_COMMAND = "list";
    private final static String DONE_COMMAND = "done";
    private final static String TODO_COMMAND = "todo";
    private final static String DEADLINE_COMMAND = "deadline";
    private final static String EVENT_COMMAND = "event";
    private final static String DELETE_COMMAND = "delete";


    private static void messageEcho(String word) {
        System.out.println(DIVIDER);
        System.out.println(word);
        System.out.println(DIVIDER + "\n");
    }

    private static String listIterator(ArrayList<Task> list) {
        StringBuilder string = new StringBuilder();
        string.append("Here are the tasks in your list:\n");
        for (int i =0; i < list.size(); i++){
            if (list.size() == i + 1) {
                string.append(i + 1).append(". ").append(list.get(i).toString());
            } else {
                string.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
            }
        }
        return string.toString();
    }


    private static boolean isNumber(String size) {
        try {
            int tempInt = Integer.parseInt(size);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public void runDuke() throws IOException, InvalidTimeException {

        ArrayList<Task> list = FileReader.getCurrentTasks();
        messageEcho("Hello! I'm Duke! Welcome back!\n" + listIterator(list));
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String word = sc.nextLine();
            try {
                if (word.equals(BYE_COMMAND)) {
                    messageEcho("Bye. Hope to see you again soon!");
                    break;
                }
                if (word.equals(LIST_COMMAND)) {
                    messageEcho(listIterator(list));
                    continue;
                }

                String[] tempArray = word.trim().split(" ");

                if (tempArray[0].equals(DONE_COMMAND)) {
                    if (tempArray.length != 2) {
                        throw new InvalidDoneFormatException();
                    }
                    if (!isNumber(tempArray[1])) {
                        throw new IncorrectDoneInputException(list.size());
                    }

                    int itemIndex = Integer.parseInt(tempArray[1]) - 1;
                    if (itemIndex < 0 || Integer.parseInt(tempArray[1]) > list.size()) {
                        throw new IncorrectDoneInputException(list.size());
                    }
                    list.get(itemIndex).markAsDone();
                    messageEcho("Nice! I've marked this task as done:\n" + list.get(itemIndex).toString());
                    continue;
                }

                if ((tempArray[0].equals(DELETE_COMMAND))) {
                    if (tempArray.length != 2) {
                        throw new InvalidDeleteFormatException();
                    }
                    if (!isNumber(tempArray[1])) {
                        throw new IncorrectDeleteInputException(list.size());
                    }

                    int itemIndex = Integer.parseInt(tempArray[1]) - 1;
                    if (itemIndex < 0 || Integer.parseInt(tempArray[1]) > list.size()) {
                        throw new IncorrectDeleteInputException(list.size());
                    }
                    Task deletedTask = list.get(itemIndex);
                    list.remove(itemIndex);
                    messageEcho("Noted. I've removed this task:\n" + deletedTask.toString()
                    + "\nNow you have " + list.size() + " tasks in the list.");
                    continue;
                }

                if (tempArray[0].equals(DEADLINE_COMMAND)) {
                    if (tempArray.length == 1) {
                        throw new InvalidDeadlineFormatException();
                    }
                    String deadlineAndDate = word.substring(9);
                    String[] deadlineDateArray = deadlineAndDate.trim().split(" /by ");
                    if (deadlineDateArray.length != 2) {
                        throw new InvalidDeadlineFormatException();
                    }
                    Deadline newDeadlineTask = new Deadline(deadlineDateArray[0], Time.getFormatedTime(deadlineDateArray[1]));
                    list.add(newDeadlineTask);
                    messageEcho(
                            "Got it. I've added this task:\n" + newDeadlineTask.toString() +
                                    "\nNow you have " + list.size() + " tasks in the list."
                    );
                    continue;
                }

                if (tempArray[0].equals(TODO_COMMAND)) {
                    if (!(tempArray.length > 1)) {
                        throw new InvalidToDoFormatException();
                    }
                    String task = word.substring(5);
                    ToDo newToDoTask = new ToDo(task);
                    list.add(newToDoTask);
                    messageEcho(
                            "Got it. I've added this task:\n" + newToDoTask.toString() +
                                    "\nNow you have " + list.size() + " tasks in the list."
                    );
                    continue;
                }

                if (tempArray[0].equals(EVENT_COMMAND)) {
                    if (tempArray.length == 1) {
                        throw new InvalidEventFormatException();
                    }
                    String eventAndDate = word.substring(6);
                    String[] eventDateArray = eventAndDate.trim().split(" /at ");
                    if (eventDateArray.length != 2) {
                        throw new InvalidEventFormatException();
                    }
                    Event newEventTask = new Event(eventDateArray[0], Time.getFormatedTime(eventDateArray[1]));
                    list.add(newEventTask);
                    messageEcho(
                            "Got it. I've added this task:\n" + newEventTask.toString() +
                                    "\nNow you have " + list.size() + " tasks in the list."
                    );
                    continue;
                }

                throw new InvalidCommandException();

            } catch (DukeException e) {
                messageEcho(e.getMessage());
            }
        }

        FileReader.saveFile(list);
    }


    public static void main(String[] args) {
        try {
            new Duke().runDuke();
        } catch (IOException | InvalidTimeException e) {
            e.getMessage();
        }
    }
}
