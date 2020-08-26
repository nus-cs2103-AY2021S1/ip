package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public static void welcomeMessage(){
        String catLogo = "        /\\_____/\\\n" +
                "       /  o   o  \\\n" +
                "      ( ==  ^  == )\n" +
                "       )         (\n" +
                "      (           )\n" +
                "     ( (  )   (  ) )\n" +
                "    (__(__)___(__)__)";
        System.out.println(catLogo);

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm NEKOBOT!!");
        System.out.println("    What can I do for you :>");
        System.out.println("    ____________________________________________________________");
    }

    public static void goodbyeMessage(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye~ Hope to see you again soon ;w;");
        System.out.println("    ____________________________________________________________");
    }

    public static void listMessage(ArrayList<Task> taskList){
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list!!");
        int index = 1;
        for(Task task: taskList) {
            System.out.println("    " + index + ". " + task);
            index++;
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void todayMessage(ArrayList<Task> taskList){
        // TODO: refactor to filter in duke.task.TaskList class
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are today's tasks!!");
        int index = 1;
        for(Task task: taskList) {
            if(task.isToday()){
                System.out.println("    " + index + ". " + task);
                index++;
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void addTaskMessage(Task task, int size){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Okies! I've added this task~");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list uwu");
        System.out.println("    ____________________________________________________________");
    }

    public static void doneTaskMessage(Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Yay! I've marked this task as done :3");
        System.out.println("       " + task);
        System.out.println("    ____________________________________________________________");
    }

    public static void deleteTaskMessage(Task task, int size) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it! I'll remove this task :>");
        System.out.println("       " + task);
        System.out.println("     Only " + size + " tasks left!!");
        System.out.println("    ____________________________________________________________");
    }

    public static void errorMessage(String UiMessage){
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + UiMessage);
        System.out.println("    ____________________________________________________________");
    }
}
