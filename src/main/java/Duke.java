/*input
deadline return book /by 2019-10-15
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
            try {
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
                } else if (command.startsWith("delete")) {
                    todoManager.delete(command.substring(7));
                } else {
                    throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Helper.horiLine);
                System.out.println(e.getMessage());
                System.out.println(Helper.horiLine);
            }
        }
    }
}
