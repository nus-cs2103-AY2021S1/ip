import java.util.Scanner;

public class Duke {

    private static String printDesign(String word) {
        return "**\n" + word + "\n**";
    }

    private static void infoValidator(String command, int splitNum, boolean timeRelated) throws DukeException {
        if(splitNum < 2) {
            if(timeRelated
                    && (command.equals("deadline") || command.equals("event"))) {
                throw new DukeException("HEY!!! Feed me with time/date. I am hungry T_T");
            } else {
                throw new DukeException("HEY!!! Don't be stingy give me more information >.<");
            }
        }
    }

    private static int indexValidator(String command, String strIndex, int listLen, int splitNum)
            throws DukeException, NumberFormatException {
        try {
            int index = Integer.parseInt(strIndex);

            if (index > listLen && command.equals("done")) {
                throw new DukeException("I don't have this work to mark as Done :>");
            } else if (splitNum < 2) {
                throw new DukeException("HEY!!! Don't be stingy give me more information >.<");
            }
            return index;
        } catch ( NumberFormatException ex) {
            throw new NumberFormatException("Please feed me with integer number ~_~");
        }
    }


    private static void order() {
        Scanner sc = new Scanner(System.in);
        String output;
        WorkList lst = new WorkList();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] splitOrder = input.split(" ", 2);
            String command = splitOrder[0];
            try {
                switch (command) {
                    case "list":
                        output = printDesign(lst.readWork());
                        break;
                    case "bye":
                        output = "** Bye. Hope to see you soon!! **";
                        break;
                    case "done":
                        infoValidator(command, splitOrder.length, false);
                        int doneTaskId = indexValidator(command, splitOrder[1], lst.workListLen(), splitOrder.length);
                        output = printDesign(lst.updateTaskStatus(doneTaskId));
                        break;
                    case "todo":
                        infoValidator(command, splitOrder.length, false);
                        String todoInfo = splitOrder[1];
                        Todo newTodo = new Todo(todoInfo);
                        output = printDesign(lst.addWork(newTodo));
                        break;
                    case "deadline":
                        infoValidator(command, splitOrder.length, false);
                        String deadlineInfo = splitOrder[1];
                        String[] dInfo = deadlineInfo.split(" /by ");
                        infoValidator(command, dInfo.length, true);
                        String deadlineEvent = dInfo[0];
                        String deadlineTime = dInfo[1];
                        Deadline newDeadline = new Deadline(deadlineEvent, deadlineTime);
                        output = printDesign(lst.addWork(newDeadline));
                        break;
                    case "event":
                        infoValidator(command, splitOrder.length, false);
                        String eventInfo = splitOrder[1];
                        String[] eInfo = eventInfo.split(" /at ");
                        infoValidator(command, eInfo.length, true);
                        String eventEvent = eInfo[0];
                        String EventTime = eInfo[1];
                        Event newEvent = new Event(eventEvent, EventTime);
                        output = printDesign(lst.addWork(newEvent));
                        break;
                    default:
                        String errorCommand = "Hey!!! I'm sorry, but I don't know what that means :-()";
                        output = printDesign(errorCommand);
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex){
                output = printDesign(ex.getMessage());
            }

            System.out.println(output);
            if(input.equals("bye")) {
                break;
            }

        }


    }

    public static void welcome() {
        String logo = " ___    ___        ______\n"
                + "|   \\  /   |_    _|  ____|\n"
                + "|    \\/    | |  | |  |  _\n"
                + "|          | |__| |  |_| |\n"
                + "|__/\\__/\\__|\\___,_|______|\n";

        String welcome = logo
                + "\n"
                + "** Hello! I'm MUG  **\n"
                + "** What can I do for you ?_? **";

        System.out.println(welcome);
    }

    public static void main(String[] args) {
        Duke.welcome();
        Duke.order();
    }
}
