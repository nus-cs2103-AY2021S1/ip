import java.util.Scanner;

public class Duke {

    private static String printDesign(String word) {
        return "**\n" + word + "\n**";
    }

    private static String addWorkStringDesign(String work, WorkList lst) {
        return "Got it. I've added this task:\n"
                + work
                + "\nNow you have "
                + lst.getWorkList().size()
                + " tasks in the list.";
    }

    private static void order() {
        Scanner sc = new Scanner(System.in);
        WorkList lst = new WorkList();
        String output;

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.equals("list")) {
                output = printDesign(lst.readWork());
            } else if(input.equals("bye")) {
                output = "** Bye. Hope to see you soon!! **";
            } else {
                String[] splitOrder = input.split(" ", 2);
                String Command = splitOrder[0];
                String info = splitOrder[1];

                switch (Command) {
                    case "done":
                        output = printDesign(
                                lst.updateTaskStatus(
                                        Integer.parseInt(info)
                                )
                        );
                        break;
                    case "todo":
                        Todo newTodo = new Todo(info);
                        lst.addWork(newTodo);
                        output = printDesign(addWorkStringDesign(newTodo.toString(), lst));
                        break;
                    case "deadline":
                        String[] deadlineInfo = info.split(" /by ");
                        String deadlineEvent = deadlineInfo[0];
                        String deadlineTime = deadlineInfo[1];
                        Deadline newDeadline = new Deadline(deadlineEvent, deadlineTime);
                        lst.addWork(newDeadline);
                        output = printDesign(addWorkStringDesign(newDeadline.toString(), lst));
                        break;
                    case "event":
                        String[] eventInfo = info.split(" /at ");
                        String eventEvent = eventInfo[0];
                        String EventTime = eventInfo[1];
                        Event newEvent = new Event(eventEvent, EventTime);
                        lst.addWork(newEvent);
                        output = printDesign(addWorkStringDesign(newEvent.toString(), lst));
                        break;
                    default:
                        Task newTask = new Task(input);
                        lst.addWork(newTask);
                        output = printDesign("added: " + input);
                        break;
                }
            }

            System.out.println(output);
            if(input.equals("bye")) {
                break;
            }
        }


    }

    private static void welcome() {
        String logo = " ___    ___        ______\n"
                + "|   \\  /   |_    _|  ____|\n"
                + "|    \\/    | |  | |  |  _ \n"
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
