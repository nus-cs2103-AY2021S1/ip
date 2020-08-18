import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void greeting() {
        String greetingMsg = "\n___________________________________________________________"
                + "\n |°‿°|"
                + "\n Hello! I'm DukeBT."
                + "\n What can I do for you?"
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n___________________________________________________________\n";
        System.out.println(greetingMsg);
    }

    public static void addTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos.createTodoTask(taskTitle, tasks);

        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);

    }

    public static void addDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines.createDeadline(taskTitle, deadlineTime, tasks);

        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);

    }

    public static void addEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events.createEvent(taskTitle, eventTime, tasks);

        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);


    }

    public static void getAllTasks(ArrayList<Task> tasks) {
        String outputMsg = "";

        if (tasks.isEmpty()) {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n Task list is empty, please try to add some tasks first."
                    + "\n___________________________________________________________\n";
        } else {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n You have " + tasks.size() + " tasks in total."
                    + "\n Here they are:";
            for (int i = 0; i < tasks.size(); i++) {
                outputMsg += "\n      " + (i + 1) + ". " + tasks.get(i);
            }
            outputMsg += "\n___________________________________________________________\n";
        }

        System.out.println(outputMsg);
    }

    public static void doneTask(int index, ArrayList<Task> tasks) {
        tasks.get(index - 1).markAsDone();
        String outputMsg = "___________________________________________________________"
                + "\n (ﾉﾟ0ﾟ)ﾉ~"
                + "\n Congratulations from DukeBT! You have done 1 task!"
                + "\n The task below has been marked as done:"
                + "\n      Task #" + index + ". " + tasks.get(index - 1)
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    public static void deleteTask(int index, ArrayList<Task> tasks) {
        String outputMsg = "___________________________________________________________"
                + "\n (ʘ‿ʘ)"
                + "\n Noted. This task has been removed from your task list:"
                + "\n      Task #" + index + ". " + tasks.get(index - 1)
                + "\n Now you have "+(tasks.size()-1)+" tasks in the list."
                + "\n___________________________________________________________\n";
        tasks.remove(index - 1);
        System.out.println(outputMsg);
    }

    public static void help() {
        String commandList = "**************************************************************"
                + "\n ┗( ⊙.⊙ )┛ "
                + "\n These are all the commands you may use:"
                + "\n     1.list -- show all tasks"
                + "\n     2.todo <task name> -- add a task"
                + "\n     3.deadline <task name> /by <task deadline> -- add a task with its deadline"
                + "\n     4.event <event name> /at <event timing> -- add an event with its timing"
                + "\n     5.done <task index> -- mark this task as done"
                + "\n     6.delete <task index> -- mark this task from task list"
                + "\n     7.help -- show all commands"
                + "\n     8.bye -- exit the chatbot"
                + "\n**************************************************************\n";
        System.out.println(commandList);
    }

    public static void invalidDoneTaskIndex(int tasksSize) {
        String warningMsg = "################################################################"
                + "\n 【• ▂ •】What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'done <Task_Index>'."
                + "\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    public static void invalidToDo() {
        String warningMsg = "################################################################"
                + "\n 【• ▂ •】What? "
                + "\n OOPS!!! The description of a todo cannot be empty."
                + "\n And make sure you follow this format - 'todo <task name>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    public static void invalidDeadline() {
        String warningMsg = "################################################################"
                + "\n 【• ▂ •】What? "
                + "\n OOPS!!! The description or/and deadline of a deadline task cannot be empty."
                + "\n And make sure you follow this format - 'deadline <task name> /by <task deadline>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    public static void invalidEvent() {
        String warningMsg = "################################################################"
                + "\n 【• ▂ •】What? "
                + "\n OOPS!!! The description or/and event timing of a event task cannot be empty."
                + "\n And make sure you follow this format - 'event <event name> /at <event timing>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    public static void invalidInput() {
        String warningMsg = "################################################################"
                + "\n 【• ▂ •】What? "
                + "\n OOPS!!! I'm sorry, but I don't know what that means."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    public static void invalidDelete(int tasksSize) {
        String warningMsg = "################################################################"
                + "\n 【• ▂ •】What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'delete <Task_Index>'."
                + "\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    public static void bye() {
        String byeMsg = "\n___________________________________________________________"
                + "\n Bye. Hope to see you again soon! |^_^|┛"
                + "\n___________________________________________________________\n";

        System.out.println(byeMsg);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        greeting();
        String inputMsg = sc.nextLine();

        while (!inputMsg.equals("bye")) {
            if (inputMsg.equals("list")) {
                getAllTasks(tasks);
            } else if (inputMsg.equals("help")) {
                help();
            } else if (inputMsg.startsWith("done")) {
                try {
                    int index = Integer.parseInt(inputMsg.split(" ")[1]);
                    doneTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    invalidDoneTaskIndex(tasks.size());
                }
            } else if (inputMsg.startsWith("todo")) {
                String taskTitle;
                try {
                    taskTitle = inputMsg.split("todo ")[1];
                    addTodoTask(taskTitle, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    invalidToDo();
                }
            } else if (inputMsg.startsWith("deadline")) {
                String taskTitle;
                String deadlineTime;
                try {
                    taskTitle = inputMsg.split("deadline ")[1].split("/by")[0];
                    deadlineTime = inputMsg.split("deadline ")[1].split("/by")[1];
                    addDeadlineTask(taskTitle, deadlineTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    invalidDeadline();
                }
            } else if (inputMsg.startsWith("event")) {
                String taskTitle;
                String eventTime;
                try {
                    taskTitle = inputMsg.split("event ")[1].split("/at")[0];
                    eventTime = inputMsg.split("event ")[1].split("/at")[1];
                    addEventTask(taskTitle, eventTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    invalidEvent();
                }
            } else if (inputMsg.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(inputMsg.split(" ")[1]);
                    deleteTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    invalidDelete(tasks.size());
                }
            } else {
                invalidInput();
            }
            inputMsg = sc.nextLine();
        }
        bye();
    }
}
