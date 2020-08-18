import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args){
        String gap = "        ";

        String init = "        ____________________________________________________________\n" +
                "        Hello! I'm Duke\n" +
                "        What can I do for you?\n" +
                "        ____________________________________________________________\n";

        System.out.println(init);

        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
        String temp;
        List<Task> tasks = new ArrayList<>();

        Pattern pattern;

        while(!(temp = sc.nextLine()).equals("bye")) {
            sb = new StringBuilder();
            sb.append(gap);
            sb.append("____________________________________________________________\n");


            try {
                if(temp.equals("list")) {
                    sb.append(gap);
                    sb.append("Here are the tasks in your list:\n");

                    int ctr = 1;

                    for(Task task: tasks) {
                        sb.append(gap);
                        sb.append(ctr).append(".");
                        sb.append(task);
                        sb.append("\n");
                        ctr++;
                    }
                } else if(temp.startsWith("done")) {

                    pattern = Pattern.compile("done [1-9][0-9]{0,}");
                    if(!pattern.matcher(temp).matches()) {
                        throw new DukeException("☹ OOPS!!! Wrong 'done' command format!\n");
                    } else {
                        int index = Integer.parseInt(""+temp.charAt(5)) - 1;
                        if(tasks.size() >= index && index >= 0) {
                            Task tas = tasks.get(index);
                            tas.markAsDone();

                            sb.append(gap);
                            sb.append("Nice! I've marked this task as done: \n");
                            sb.append(gap).append("  ");
                            sb.append(tas);
                            sb.append("\n");
                        }
                    }

                } else if(temp.startsWith("todo")) {

                    pattern = Pattern.compile("todo ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
                    if(!pattern.matcher(temp).matches()) {
                        throw new DukeException("☹ OOPS!!! Wrong 'todo' command format!\n");

                    } else {
                        Todo todo = new Todo(temp.substring(5));
                        tasks.add(todo);

                        sb.append(gap);
                        sb.append("Got it. I've added this task: \n");

                        sb.append(gap).append("  ").append(todo);
                        sb.append("\n");

                        sb.append(gap);
                        sb.append("Now you have ").append(tasks.size()).append(" task(s) in the list.\n");
                    }

                } else if(temp.startsWith("deadline")) {
                    pattern = Pattern.compile("deadline ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*) /by ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
                    if(!pattern.matcher(temp).matches()) {
                        throw new DukeException("☹ OOPS!!! Wrong 'deadline' command format!\n");
                    } else {
                        String[] s = temp.substring(9).split(" /by ");
                        Deadline deadline = new Deadline(s[0], s[1]);
                        tasks.add(deadline);

                        sb.append(gap);
                        sb.append("Got it. I've added this task: \n");

                        sb.append(gap).append("  ").append(deadline);
                        sb.append("\n");

                        sb.append(gap);
                        sb.append("Now you have ").append(tasks.size()).append(" task(s) in the list.\n");
                    }

                } else if(temp.startsWith("event")) {
                    pattern = Pattern.compile("event ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*) /at ([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)");
                    if(!pattern.matcher(temp).matches()) {
                        throw new DukeException("☹ OOPS!!! Wrong 'event' command format!\n");
                    } else {
                        String[] s = temp.substring(6).split(" /at ");
                        Event event = new Event(s[0], s[1]);
                        tasks.add(event);

                        sb.append(gap);
                        sb.append("Got it. I've added this task: \n");

                        sb.append(gap).append("  ").append(event);
                        sb.append("\n");

                        sb.append(gap);
                        sb.append("Now you have ").append(tasks.size()).append(" task(s) in the list.\n");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (DukeException e) {
                sb.append(gap);
                sb.append(e.getMessage());
            }


            sb.append(gap);
            sb.append("____________________________________________________________\n");
            System.out.println(sb.toString());
        }


        String exit = "        ____________________________________________________________\n" +
                "        Bye. Hope to see you again soon!\n" +
                "        ____________________________________________________________\n";

        System.out.println(exit);
    }
}