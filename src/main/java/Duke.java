import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        String line = "____________________________________________________________\n";
        String greeting = "Hello! I'm Duke from the chat bot universe ~ Nice to meet you (●´∀｀●) \n" +
                           "I'll be your task manager from now onwards.\n";
        String bye = "Awwww, I guess you are gonna leave... \n" +
                "I'll keep track of your tasks nicely. \n" +
                "Text me if you wanna talk again! Have a nice day ❤️\n ";

        System.out.println(line + greeting + line);
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            //String[] pieces = temp.split("\\s+");
            String [] pieces = temp.split(" ", 2);
            if (temp.equals("bye")) {
                System.out.println(line + bye + line);
                return;
            } else if (temp.equals("list")) {
                System.out.println(line + "Here are the tasks in your list: \n");
                for (int i = 1; i < count + 1; i++) {
                    Task cur = list[i - 1];
                    String type = "";
                    if (cur instanceof ToDo) {
                        type = "[T]";
                    } else if (cur instanceof Deadline) {
                        type = "[D]";
                    } else {
                        type = "[E]";
                    }
                    System.out.println("" + i + "." + type + "[" + cur.getStatusIcon() + "] " + cur);
                }
                System.out.println(line);


            } else if (pieces[0].equals("done")){
                int task = Integer.valueOf(pieces[1]);
                Task cur = list[task-1];
                cur.markAsDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done: \n" +
                        "[" + cur.getStatusIcon()+ "] " + cur);
                System.out.println(line);

            } else {
                String s = "";
                String[] array = new String[1];
                Task t = new Task("");
                switch(pieces[0]) {
                    case "todo":
                        t = new ToDo(pieces[1]);
                        s = "[T][\u2718] " + t;
                        break;

                    case "deadline":
                        array = pieces[1].split("/by");
                        t = new Deadline(array[0], array[1]);
                        s = "[D][\u2718] " + t;
                        break;

                    case "event":
                        array = pieces[1].split("/at");
                        t = new Event(array[0], array[1]);
                        s = "[E][\u2718] " + t;
                        break;

                    default:
                        break;
                }

                list[count] = t;
                count++;

                System.out.println(line);
                System.out.println("Got it. I've added this task: \n" + s );
                System.out.println("Now you have " + count + " tasks in the list. ");
                System.out.println(line);

            }
                //echo
                //System.out.println(line + temp + "\n" + line);
        }

    }
}

