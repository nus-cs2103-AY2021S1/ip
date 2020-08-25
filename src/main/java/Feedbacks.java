import java.util.ArrayList;

public class Feedbacks {
    public static void logoMsg() {
        String logo = " ___       __   _______   ___       ________  " +
                "________  _____ ______   _______      \n" +
                "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     " +
                "|\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\     \n" +
                "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|" +
                "\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|    \n" +
                " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    " +
                "\\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__  \n" +
                "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____" +
                "\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ \n" +
                "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______" +
                "\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\\n" +
                "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______" +
                "|\\|__|     \\|__|\\|_______|"
                + "\n\n WELCOME to DukeBT!";
        System.out.println(logo);
    }

    public static void greetingMsg() {
        String greetingMsg = "\n___________________________________________________________"
                + "\n |°‿°|"
                + "\n Hello! I'm DukeBT."
                + "\n What can I do for you?"
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n___________________________________________________________\n";
        System.out.println(greetingMsg);
    }

    public static void helpMsg() {
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

    public static void byeMsg() {
        String byeMsg = "\n___________________________________________________________"
                + "\n |^_^|┛"
                + "\n Bye. Hope to see you again soon!"
                + "\n Your task list has been saved."
                + "\n It will be automatically reloaded when you come back!"
                + "\n___________________________________________________________\n";

        System.out.println(byeMsg);
    }

    public static void addDeadlineTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this Deadline task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    public static void addEventTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this Event task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    public static void addTodoTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this ToDo task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    public static void doneTaskMsg(int index, ArrayList<Task> tasks) {
        String outputMsg = "___________________________________________________________"
                + "\n (ﾉﾟ0ﾟ)ﾉ~"
                + "\n Congratulations from DukeBT! You have done 1 task!"
                + "\n The task below has been marked as done:"
                + "\n      Task #" + index + ". " + tasks.get(index - 1)
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    public static void deleteTaskMsg(int index, int newSizeOfTasks, Task taskToDelete) {
        String outputMsg = "___________________________________________________________"
                + "\n (ಠ‿↼)"
                + "\n Noted. This task has been removed from your task list:"
                + "\n      Task #" + index + ". " + taskToDelete
                + "\n Now you have " + newSizeOfTasks + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
    }

    public static void getAllTasksMsg(ArrayList<Task> tasks) {
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
}
