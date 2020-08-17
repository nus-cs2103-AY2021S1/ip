import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Chatbot {

    protected String inquiry;
    protected List<Task> planner;
    protected final String INDENTATION = "     ";
    protected final String LINE = "    ____________________________________________________________";

    public Chatbot() {
        this.inquiry = "";
        this.planner = new ArrayList<Task>();
    }


    public void chat() {
        introductions();

        Scanner sc = new Scanner(System.in);
        this.inquiry = sc.nextLine();
        while (!inquiry.equals("bye")) {
            if (inquiry.equals("list")) {
                list();

            } else if (inquiry.startsWith("todo")) {
                taskHandler(TaskType.TODOS, inquiry.substring(5));
            } else if (inquiry.startsWith("deadline")) {
                taskHandler(TaskType.DEADLINE, inquiry.substring(9));
            } else if (inquiry.startsWith("event")) {
                taskHandler(TaskType.EVENT, inquiry.substring(6));
            } else if (inquiry.startsWith("done")) {
                String number = "";
                for (String val: inquiry.split(" ")) {
                    number = val;
                }

                int num = Integer.parseInt(number);

                if (num > planner.size() || num == 0) {
                    reply("There appears to be a problem. Type 'list' to show your tasks.");
                } else {
                    Task currentTask = planner.get(num - 1);
                    done(currentTask);
                }
            }
            System.out.println(LINE);
            inquiry = sc.nextLine();
        }

        farewell();
    }

    private void taskHandler(TaskType type, String body) {
        reply("Got it. I've added this task:");
        Task currentTask;
        if (type.equals(TaskType.TODOS)) {
            currentTask = new ToDos(body);

        } else if (type.equals(TaskType.DEADLINE)) {
            int index = body.indexOf('/');
            String deadline = body.substring(index + 1);
            String description = body.substring(0, index);
            currentTask = new Deadline(description, deadline);
//        } else if (type.equals(TaskType.EVENT)) {
        } else {
            int index = body.indexOf('/');
            String deadline = body.substring(index + 1);
            String description = body.substring(0, index);
            currentTask = new Event(description, deadline);
        }
        addToPlanner(currentTask);
        reply(INDENTATION + currentTask.toString());
        reply("Now you have " + planner.size() + " tasks in the list.");
    }


    private void introductions() {
        reply("Hello, I'm Ravenloss");
        reply("What can I do for you?");
        System.out.println(LINE);
    }


    private void farewell() {
        reply("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }


    private void reply (String string) {
        System.out.println(INDENTATION + string);
    }


    private void addToPlanner(Task task) {
        planner.add(task);
    }


    private void list() {
        reply("Here are the tasks in your list:");
        for (int i = 0; i < planner.size(); i++) {
            String number = (i + 1) + ".";
            Task currentTask = planner.get(i);
            reply(number + currentTask.toString());
        }
    }


    private void done(Task task) {
        task.done();
        reply("Good job! I've marked this task as done");
        reply(INDENTATION + task.toString());

    }
}
