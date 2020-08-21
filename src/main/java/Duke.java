import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {

    private static String printDesign(String word) {
        return "**\n" + word + "\n**";
    }

    private static void order() {
        Scanner sc = new Scanner(System.in);
        String output;
        WorkList lst = new WorkList();
        TaskWriter tw = new TaskWriter("mug.txt");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            try {
                String[] splitOrder = input.split(" ", 2);
                Command command = Validator.command(splitOrder[0]);

                switch (command) {
                    case LIST:
                        output = printDesign(lst.readWork());
                        break;
                    case BYE:
                        output = "** Bye. Hope to see you soon!! **";
                        break;
                    case DONE:
                        Validator.info(command, splitOrder.length, false);
                        int doneTaskId = Validator.index(command, splitOrder[1], lst.workListLen(), splitOrder.length);
                        output = printDesign(lst.updateTaskStatus(doneTaskId));
                        break;
                    case DELETE:
                        Validator.info(command, splitOrder.length, false);
                        int deleteTaskId = Validator.index(command, splitOrder[1], lst.workListLen(), splitOrder.length);
                        tw.deleteTask(deleteTaskId);
                        output = printDesign(lst.deleteWork(deleteTaskId));
                        break;
                    case TODO:
                        Validator.info(command, splitOrder.length, false);
                        String todoInfo = splitOrder[1];
                        Todo newTodo = new Todo(todoInfo);
                        tw.appendTask(command, todoInfo);
                        output = printDesign(lst.addWork(newTodo));
                        break;
                    case DEADLINE:
                        Validator.info(command, splitOrder.length, false);
                        String deadlineInfo = splitOrder[1];
                        tw.appendTask(command, deadlineInfo);
                        String[] dInfo = deadlineInfo.split(" /by ");
                        Validator.info(command, dInfo.length, true);
                        String deadlineEvent = dInfo[0];
                        String deadlineTime = dInfo[1];
                        Deadline newDeadline = new Deadline(deadlineEvent, deadlineTime);
                        output = printDesign(lst.addWork(newDeadline));
                        break;
                    case EVENT:
                        Validator.info(command, splitOrder.length, false);
                        String eventInfo = splitOrder[1];
                        tw.appendTask(command, eventInfo);
                        String[] eInfo = eventInfo.split(" /at ");
                        Validator.info(command, eInfo.length, true);
                        String eventEvent = eInfo[0];
                        String EventTime = eInfo[1];
                        Event newEvent = new Event(eventEvent, EventTime);
                        output = printDesign(lst.addWork(newEvent));
                        break;
                    default:
                        String errorCommand = "Hey!!! I'm sorry, but MUG don't know what that means :-()";
                        output = printDesign(errorCommand);
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | IOException ex){
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
                + "** What can MUG do for you ?_? **";

        System.out.println(welcome);
    }

    public static void main(String[] args) {
        try {
            File workList = new File("mug.txt");
            workList.createNewFile();
            Duke.welcome();
            Duke.order();
        } catch (IOException e) {
            System.out.println("An error occurred!!");
            e.printStackTrace();
        }

    }
}
