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
                           "You are the first human I've ever met. Say something to me!\n";
        String bye = "Awwww, I guess you are gonna leave... \n" +
                "Human language is so fun! " +
                "But it's time for me to sleep again (yesss I'm just a little sleepy chat bot xD )\n" +
                "Nudge me if you wanna talk again! Have a nice day ❤️\n ";

        System.out.println(line + greeting + line);
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            String[] pieces = temp.split("\\s+");
            if (temp.equals("bye")) {
                System.out.println(line + bye + line);
                return;
            } else if (temp.equals("list")) {
                System.out.println(line + "Here are the tasks in your list: \n");
                for (int i = 1; i < count + 1; i++) {
                    Task cur = list[i-1];
                    System.out.println("" + i + ". [" + cur.getStatusIcon()+ "] " + cur );
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
                System.out.println(line);
                System.out.println("added: " + temp);
                System.out.println(line);
                list[count] = new Task(temp);
                count++;
            }
                //echo
                //System.out.println(line + temp + "\n" + line);
            }

        }
    }

