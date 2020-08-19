import java.util.Scanner;

public class Duke {
    static final String GREET = "greet";
    static final String EXIT = "bye";
    static final String LIST = "list";
    static final String DONE = "done";
    static final String TODO = "todo";
    static final String EVENT = "event";
    static final String DEADLINE = "deadline";

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        Echo echo = new Echo();
        echo.addTask(new Greet());
        String res = echo.replyUser();
        System.out.println(res);

        while (sc.hasNextLine()) {
            Task t;
            String[] command = sc.nextLine().split(" ", 2);
            String type = command[0];

            if (command.length > 1) {
                String description = command[1];

                if (type.equals(DONE)) {
                    int taskNum = Integer.parseInt(description) - 1;
                    t = new Done(taskNum);
                } else if (type.equals(TODO)) {
                    t = new ToDo(description);
                } else if (type.equals(EVENT)) {
                    String[] s = description.split(" /at ");
                    String desc = s[0];
                    String at = s[1];
                    t = new Event(desc, at);
                } else if (type.equals(DEADLINE)) {
                    String[] s = description.split(" /by ");
                    String desc = s[0];
                    String by = s[1];
                    t = new Deadline(desc, by);
                } else {
                    // throw exception
                    t = new Task("fail", "fail");
                }
            } else {
                if (type.equals(LIST)) {
                    t = new List();
                } else if (type.equals(EXIT)) {
                    t = new Exit();
                } else {
                    // throw exception
                    t = new Task("fail", "fail");
                }
            }


            echo.addTask(t);
            String response = echo.replyUser();
            System.out.println(response);
            if (echo.toExit()) {
                break;
            }
        }

        sc.close();
    }
}
