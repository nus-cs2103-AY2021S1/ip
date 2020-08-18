import java.util.Scanner;

public class Jimmy {
    public static void main(String[] args) {
        String div = "\n______________________________________________________________\n\n";
        String logo = "                      █████████\n"
                    +  "  ███████          ███        ███\n"
                    +  "  █      █       ███             ███\n"
                    +  "   █      █    ██                   ██\n"
                    +  "    █     █   ██     ██      ██     ███          Hey! I'm Jimmy,\n"
                    +  "     █   █   █      ████    ████      ██     your personal assistant!\n"
                    +  "   █████████████                      ██\n"
                    +  "   █            █         █           ██   What can I do for you today?\n"
                    +  " ██             █   ██          ██    ██\n"
                    +  "██   ███████████     ██        ██     ██\n"
                    +  "█               █      ████████       ██\n"
                    +  "██              █                    ██\n"
                    +  " █   ███████████                   ██\n"
                    +  " ██          ████                 █\n"
                    +  "  ████████████   █████████████████";
        
        System.out.println(div + logo + div);

        Planner lst = new Planner();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String msg = sc.nextLine();
            String[] arr = msg.split(" ");
            if (msg.equals("bye")) {
                System.out.println(div + "\tBye. Hope to see you again soon!" + div);
                break;
            } else if (msg.equals("list")) {
                System.out.println(div + lst + div);
            } else if (arr[0].equals("done") || arr[0].equals("delete")) {
                if (arr.length > 2 || arr.length == 1) {
                    System.out.println(div + "\tError: The function \"" + arr[0] + "\" should be followed by a single number." + div);
                    continue;
                }
                int i = Integer.parseInt(arr[1]);
                if (i > lst.getNumTasks()+1 || i < 1) {
                    System.out.println(div + "\tError: Task #" + i + " is out of range." + div);
                    continue;
                }
                if (arr[0].equals("done")) {
                    lst.completeTask(i);
                    Task t = lst.getTask(i);
                    System.out.println(div + "\tNice! I've marked this task as done:" + "\n\t  " + t + div);
                } else {
                    Task t = lst.getTask(i);
                    lst.del(i);
                    System.out.println(div + "\tNoted. I've removed this task:" + "\n\t  " + t + "\n\tNow you have " + lst.getNumTasks() + " tasks in the list." + div);
                }
            } else if (arr.length > 1) {
                Task t = null;
                switch (arr[0]) {
                    case "todo":
                        t = new Todo(msg);
                        break;
                    case "deadline":
                        t = new Deadline(msg);
                        break;
                    case "event":
                        t = new Event(msg);
                        break;
                }
                if (t == null) {
                    System.out.println(div + "\tError: The function \"" + msg + "\" does not exist." + div);
                    continue;
                }
                System.out.println(div + "\tGot it. I've added this task:");
                lst.addTask(t);
                System.out.println("\t  " + t + "\n\tNow you have " + lst.getNumTasks() + " tasks in the list." + div);
            } else {
                if (msg.equals("todo") || msg.equals("deadline") || msg.equals("event")) {
                    System.out.println(div + "\tError: The description of \"" + msg + "\" should not be empty." + div);
                } else {
                    System.out.println(div + "\tError: The function \"" + msg + "\" does not exist." + div);
                }
            }
            
        }
        sc.close();
    }
}