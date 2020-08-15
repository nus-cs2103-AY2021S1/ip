import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeCommandsHandler.greetings();
        String name = sc.nextLine();
        DukeCommandsHandler.addressUser(name);

        String input;
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.contains("todo")) { // add todo tasks
                DukeCommandsHandler.addToDo(input);
            } else if (input.contains("deadline")) { // add deadline tasks
                DukeCommandsHandler.addDeadline(input);
            } else if (input.contains("event")) { // add event tasks
                DukeCommandsHandler.addEvent(input);
            } else if (input.contains("done")) { // mark tasks done
                DukeCommandsHandler.markTaskDone(input);
            } else if (input.equals("list")) { // list out the tasks
                DukeCommandsHandler.listTasks();
            } else if (input.equals("bye")) { // exit the bot
                DukeCommandsHandler.exitFocus();
                break;
            } else { // handle invalid inputs
                DukeException.invalidInput();
            }
        }
        sc.close();
    }
}