/*input
list
bye
*/
package duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui.init();
        Ui.greet();

        while (in.hasNextLine()) {
            try {
                String command = in.nextLine();
                if (command.equals("bye")) {
                    Ui.exit();
                    break;
                } else if (command.equals("list")) {
                    taskList.list();
                } else if (command.startsWith("done")) {
                    taskList.markDone(command.substring(5));
                } else if (command.startsWith("deadline") || command.startsWith("event") || command.startsWith("todo")) {
                    taskList.addTask(command);
                } else if (command.startsWith("delete")) {
                    taskList.delete(command.substring(7));
                } else {
                    throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Ui.seperateLine);
                System.out.println(e.getMessage());
                System.out.println(Ui.seperateLine);
            }
        }
    }
}
