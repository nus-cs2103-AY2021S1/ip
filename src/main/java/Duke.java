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
                string.append((i + 1) + ". " + list.get(i).toString());
            } else {
                string.append((i + 1) + ". " + list.get(i).toString() + "\n");
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

    public static void main(String[] args) {
        messageEcho("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String word  = sc.nextLine();
            if (word.equals(BYE_COMMAND)) {
                messageEcho("Bye. Hope to see you again soon!");
                break;
            }
            if (word.equals(LIST_COMMAND)) {
                messageEcho(listIterator(list));
                continue;
            }

            String[] tempArray = word.trim().split(" ");

            if (tempArray.length == 2 && tempArray[0].equals(DONE_COMMAND) && isNumber(tempArray[1])) {
                int itemIndex = Integer.parseInt(tempArray[1]) - 1;
                if (itemIndex < 0 || Integer.parseInt(tempArray[1]) > list.size()) {
                    messageEcho("Input number is out of range!");
                    continue;
                }
                list.get(itemIndex).markAsDone();
                messageEcho("Nice! I've marked this task as done:\n" + list.get(itemIndex).toString());
                continue;
            }

            if (tempArray[0].equals(DEADLINE_COMMAND)) {
                String[] eventDateArray = word.trim().split(" /by ");
                Deadline newDeadlineTask = new Deadline(eventDateArray[0], eventDateArray[1]);
                list.add(newDeadlineTask);
                messageEcho(
                        "Got it. I've added this task:\n" + newDeadlineTask.toString() +
                                "\nNow you have " + list.size() + " in total"
                );
                continue;
            }

            if (tempArray[0].equals(TODO_COMMAND)) {
                String task = word.substring(5);
                ToDo newToDoTask = new ToDo(task);
                list.add(newToDoTask);
                messageEcho(
                        "Got it. I've added this task:\n" + newToDoTask.toString() +
                                "\nNow you have " + list.size() + " in total"
                );
                continue;
            }

            if (tempArray[0].equals(EVENT_COMMAND)) {
                String[] eventDateArray = word.trim().split(" /at ");
                Event newEventTask = new Event(eventDateArray[0], eventDateArray[1]);
                list.add(newEventTask);
                messageEcho(
                        "Got it. I've added this task:\n" + newEventTask.toString() +
                                "\nNow you have " + list.size() + " in the list."
                );
                continue;
            }

            messageEcho("Input Command does not match any command");

//            removed for now as this function does not meet task specification
//            list.add(new Task(word));
//            messageEcho("added: " + word);
        }
    }
}
