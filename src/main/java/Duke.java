import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        run();
    }

    public static void greet(){
        String logo = "____________________________________________________________\n"
                /*+ " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"*/
                + " Hello I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo);
    }

    public static void run(){
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String input;
        String[] strings;
        ArrayList<Task> list = new ArrayList<>();
        Task task;
        int num, index;
        while(isRunning){
            input = sc.nextLine();

            if(input.equals("list")){
                list(list);
            }
            else if(input.equals("bye")){
                isRunning = bye();
            }
            else if(getWord(input).equals("done")){ //incomplete
                num = sc.nextInt();
                task = list.get(num-1).done();
                list.add(num-1, task); //update task in list to done
                printLine();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(" " + task.getDoneString() + task.getString());
                printLine();

            }
            else if(getWord(input).equals("todo")){
                todo(input, list);
            }
            else if(getWord(input).equals("deadline")) {
                deadline(input, list);
            }
            else if(getWord(input).equals("event")) {
                event(input, list);
            }
            else{
                error();
            }
        }
    }

    public static String getWord(String string){

        int firstSpaceIndex = string.indexOf(' ');
        if(firstSpaceIndex == -1){
            return string;
        }
        return string.substring(0, firstSpaceIndex);
    }

    public static void list(ArrayList<Task> list){
        printLine();
        Task task;
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= list.size(); i++){
            task = list.get(i-1);
            System.out.println(i + "." + task.getTypeString() + task.getDoneString() + task.getString());
        }
        printLine();
    }

    public static boolean bye(){
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        return false;
    }

    public static void todo(String input, ArrayList<Task> list){
        printLine();
        input = input.substring(4);
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            Task task = new Task(TaskType.TODO, false, input);
            list.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
        printLine();
    }

    public static void deadline(String input, ArrayList<Task> list){
        input = input.substring(8);
        printLine();
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }else {
            int index = input.indexOf("/by");
            if (index == -1) {
                System.out.println("☹ OOPS!!! The description of a deadline must have a indicated deadline.");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(input.substring(0, index)).append("(by:").append(input.substring(index + 3)).append(")");
                input = stringBuilder.toString();
                Task task = new Task(TaskType.DEADLINE, false, input);
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        }
        printLine();
    }

    public static void event(String input, ArrayList<Task> list){
        input = input.substring(5);
        printLine();
        if(input.isEmpty()){
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        }else {
            int index = input.indexOf("/at");
            if (index == -1) {
                System.out.println("☹ OOPS!!! The description of a event must have a indicated deadline.");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(input.substring(0, index)).append("(at:").append(input.substring(index + 3)).append(")");
                input = stringBuilder.toString();
                Task task = new Task(TaskType.DEADLINE, false, input);
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        }
        printLine();
    }

    public static void printLine(){
        System.out.println("____________________________________________________________\n");
    }

    public static void error(){
        printLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }
}
class Task{
    TaskType taskType;
    boolean isDone;
    String string;

    public Task(TaskType taskType, boolean isDone, String string){
        this.taskType = taskType;
        this.isDone = isDone;
        this.string = string;
    }

    public String getString() {
        return string;
    }
    public String getDoneString(){
        String string;
        if(isDone){
            string = "[✓]";
        }
        else{
            string = "[✗]";
        }
        return string;
    }

    public Task done(){
        return new Task(taskType, true, string);
    }

    public String getTypeString(){
        String string;
        if(taskType.equals(TaskType.TODO)){
            string = "[T]";
        }
        else if(taskType.equals(TaskType.DEADLINE)){
            string = "[D]";
        }
        else{
            string = "[E]";
        }
        return string;
    }
}
enum TaskType{
    TODO,
    DEADLINE,
    EVENT;
}