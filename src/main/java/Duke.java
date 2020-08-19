/*input
todo borrow book
deadline return book /by Sunday
event project meeting /at Mon 2-4pm
list
bye
*/
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GreetExit greetExit = new GreetExit();
        TodoManager todoManager = new TodoManager();
        Helper.init();
        greetExit.greet();
        while (in.hasNextLine()) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                greetExit.exit();
                break;
            } else if (command.equals("list")) {
                todoManager.listTask();
            } else if (command.startsWith("done")) {
                todoManager.markDone(command.substring(5));
            } else if (command.startsWith("deadline") || command.startsWith("event") || command.startsWith("todo")) {
                todoManager.addTask(command);
            }
        }
    }
}
