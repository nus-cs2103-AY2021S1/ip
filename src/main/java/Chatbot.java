import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Chatbot {

    protected String inquiry;
    protected List<Task> planner;
    protected final String INDENTATION = "     ";
    protected final String LINE = "    ____________________________________________________________";
    protected final String INVALID_TASK = "⚠⚠⚠ I'm sorry, but I don't know what that means :-(";
    protected final String INVALID_INDEX = "⚠⚠⚠ There appears to be a problem with your task number.";
    protected final String EMPTY_DONE_TASK = "⚠⚠⚠ Add the appropriate task number after the command 'done'.";
    protected final String EMPTY_TODO_TASK = "⚠⚠⚠ The description of a 'todo' cannot be empty.";
    protected final String EMPTY_DEADLINE_TASK = "⚠⚠⚠ The description of a 'deadline' cannot be empty.";
    protected final String EMPTY_EVENT_TASK = "⚠⚠⚠ The description of a 'event' cannot be empty.";
    protected final String DEADLINE_FORMAT = "⚠⚠⚠ The description of 'deadline' should be accompanied"
            + '\n' + INDENTATION + "    by '/by' followed by the date";
    protected final String EVENT_FORMAT = "⚠⚠⚠ The description of 'event' should be accompanied"
            + '\n' + INDENTATION + "    by '/at' followed by the date";



    public Chatbot() {
        this.inquiry = "";
        this.planner = new ArrayList<Task>();
    }


    public void chat() {

            introductions();
            Scanner sc = new Scanner(System.in);
            this.inquiry = sc.nextLine();
            while (!inquiry.equals("bye")) {
                try {
                    if (inquiry.equals("list")) {
                        list();
                    } else if (inquiry.startsWith("todo")) {
                        taskHandler(TaskType.TODOS, inquiry);
                    } else if (inquiry.startsWith("deadline")) {
                        taskHandler(TaskType.DEADLINE, inquiry);
                    } else if (inquiry.startsWith("event")) {
                        taskHandler(TaskType.EVENT, inquiry);
                    } else if (inquiry.startsWith("done")) {
                        completeTask(inquiry);
                    } else {
                        throw new DukeInvalidTaskException(INVALID_TASK);
                    }

                } catch (DukeException e) {
                    reply(e.getMessage());
                }
                System.out.println(LINE);
                inquiry = sc.nextLine();
            }
            farewell();

    }

    private void taskHandler(TaskType type, String body) throws DukeException {
            Task currentTask;
            if (type.equals(TaskType.TODOS)) {
                if (inquiry.equals("todo")) throw new DukeEmptyToDoException(EMPTY_TODO_TASK);
                currentTask = new ToDos(body.substring(5));

            } else if (type.equals(TaskType.DEADLINE)) {
                if (inquiry.equals("deadline")) throw new DukeEmptyDeadlineException(EMPTY_DEADLINE_TASK);
                String[] arrOfString = (body.substring(9)).split("/by ", 2);

                if (arrOfString.length == 1) throw new DukeDeadlineFormatException(DEADLINE_FORMAT);

                String deadline = arrOfString[1];
                String description = arrOfString[0];
                currentTask = new Deadline(description, deadline);
            } else {
                if (inquiry.equals("event")) throw new DukeEmptyEventException(EMPTY_EVENT_TASK);
                String[] arrOfString = (body.substring(6)).split("/at ", 2);

                if (arrOfString.length == 1) throw new DukeEventFormatException(EVENT_FORMAT);
                String eventDate = arrOfString[1];
                String description = arrOfString[0];
                currentTask = new Event(description, eventDate);
            }
            addToPlanner(currentTask);
            reply("Got it. I've added this task:");
            reply(INDENTATION + currentTask.toString());
            reply("Now you have " + planner.size() + " tasks in the list.");
    }

    private void completeTask(String inquiry) throws DukeException {

        try {
            String[] tokens = inquiry.split(" ");
            int num = Integer.parseInt(tokens[1]);

            if (num > planner.size() || num <= 0) {
                throw new DukeInvalidIndexException(INVALID_INDEX);
            }
            Task currentTask = planner.get(num - 1);
            currentTask.done();

            reply("Good job! I've marked this task as done");
            reply(INDENTATION + currentTask.toString());

        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException(INVALID_INDEX);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeEmptyDoneException(EMPTY_DONE_TASK);
        }
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

    public void reply (String string) {
        System.out.println(INDENTATION + string);
    }

    private void addToPlanner(Task task) {
        planner.add(task);
    }

    private void list() {

        if (planner.size() == 0) {
            reply("You have no pending tasks");
        } else {
            reply("Here are the tasks in your list:");
            for (int i = 0; i < planner.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = planner.get(i);
                reply(number + currentTask.toString());
            }
        }
    }
}
