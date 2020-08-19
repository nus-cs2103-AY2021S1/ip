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
                + "\n |^_^|┛"
                + "\n Bye. Hope to see you again soon!"
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
                Task.getAllTasks(tasks);
            } else if (inputMsg.equals("help")) {
                help();
            } else if (inputMsg.startsWith("done")) {
                try {
                    int index = Integer.parseInt(inputMsg.split("done ")[1]);
                    Task.doneTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    invalidDoneTaskIndex(tasks.size());
                } catch (NumberFormatException e) {
                    invalidDoneTaskIndex(tasks.size());
                }
            } else if (inputMsg.startsWith("todo")) {
                String taskTitle;
                try {
                    taskTitle = inputMsg.split("todo ")[1];
                    ToDos.addTodoTask(taskTitle, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    invalidToDo();
                }
            } else if (inputMsg.startsWith("deadline")) {
                String taskTitle;
                String deadlineTime;
                try {
                    taskTitle = inputMsg.split("deadline ")[1].split("/by")[0];
                    deadlineTime = inputMsg.split("deadline ")[1].split("/by")[1];
                    Deadlines.addDeadlineTask(taskTitle, deadlineTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    invalidDeadline();
                }
            } else if (inputMsg.startsWith("event")) {
                String taskTitle;
                String eventTime;
                try {
                    taskTitle = inputMsg.split("event ")[1].split("/at")[0];
                    eventTime = inputMsg.split("event ")[1].split("/at")[1];
                    Events.addEventTask(taskTitle, eventTime, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    invalidEvent();
                }
            } else if (inputMsg.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(inputMsg.split("delete ")[1]);
                    Task.deleteTask(index, tasks);
                } catch (IndexOutOfBoundsException e) {
                    invalidDelete(tasks.size());
                } catch (NumberFormatException e) {
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
