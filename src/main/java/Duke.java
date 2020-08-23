import main.java.*;

import java.util.Scanner;

public class Duke {
    final static String UNDERSCORE = "____________________________________________________________ \n";

    public static void main(String[] args) {
        String intro = "____________________________________________________________ \n" +
                        "Hello! I'm Duke \n" + "What can I do for you? \n" +
                        "____________________________________________________________";
        System.out.println(intro);
        TaskList taskList = new TaskList();
        Scanner input =  new Scanner(System.in);
        String line;
        while(input.hasNextLine()) {
            try {
                line = input.nextLine();
                if (line.equals("bye")) {
                    System.out.println(UNDERSCORE + " Bye. Hope to see you again soon!" + "\n" + UNDERSCORE);
                    break;
                } else if (line.equals("list")) {
                    taskList.list();
                } else if (line.indexOf("delete") == 0) {
                    taskList.delete(line);
                } else if (line.indexOf("done") == 0) {
                    taskList.checkOff(line);
                } else {
                    taskList.addTask(line);
                }
            } catch (DukeInvalidDateException err){
                System.out.println(err.getMessage());
            } catch (DukeInvalidDayException err) {
                System.out.println(err.getMessage());
            } catch (DukeInvalidTaskException err) {
                System.out.println(err.getMessage());
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
        }
        input.close();
    }
}
