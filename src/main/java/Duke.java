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

    public static ArrayList<Task> addTask(String inputMsg, ArrayList<Task> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Task added: " + inputMsg
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);

        Task.createTask(inputMsg, tasks);
        return tasks;
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

    public static void help() {
        String commandList = "**************************************************************"
                + "\n ┗( ⊙.⊙ )┛ "
                + "\n These are all the commands you may use:"
                + "\n     1.list -- show all tasks"
                + "\n     2.todo <task name> -- add a task"
                + "\n     3.deadline <task name> /by <task deadline> -- add a task with its deadline"
                + "\n     4.event <event name> /at <event timing> -- add an event with its timing"
                + "\n     5.done <task index> -- mark this task as done"
                + "\n     6.help -- show all commands"
                + "\n     7.bye -- exit the chatbot"
                + "\n**************************************************************\n";
        System.out.println(commandList);
    }


    public static void invalidDoneTaskIndex(int tasksSize) {
        String warningMsg = "**************************************************************"
                + "\n 【• ▂ •】What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'done <Task_Index>'."
                + "\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n**************************************************************";
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
            } else {
                tasks = addTask(inputMsg, tasks);
            }
            inputMsg = sc.nextLine();
        }
        bye();
    }
}
