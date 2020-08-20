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
                + " Hello I'm Duke \n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(logo);
    }

    public static void run(){
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String input;
        String line = "____________________________________________________________\n";
        String[] strings;
        ArrayList<Task> list = new ArrayList<>();
        Task task;
        int num;
        while(isRunning){
            input = sc.next();
            if(input.equals("list")){
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= list.size(); i++){
                    task = list.get(i-1);
                    System.out.println(i + "." + task.getTypeString() + task.getDoneString() + task.getString());
                }
                System.out.println(line);
            }
            else if(input.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                isRunning = false;
            }
            else if(input.equals("done")){
                num = sc.nextInt();
                task = list.get(num-1).done();
                list.add(num-1, task); //update task in list to done
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(" " + task.getDoneString() + task.getString());
                System.out.println(line);

            }
            else if(input.equals("todo")){
                input = sc.nextLine();
                task = new Task(TaskType.TODO,false, input);
                list.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
                System.out.println("Now you have " + list.size() +" tasks in the list.");
                System.out.println(line);
            }
            else if(input.equals("deadline")) {
                input = sc.nextLine();
                strings = input.split("/by");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(strings[0]).append("(by:").append(strings[1]).append(")");
                input = stringBuilder.toString();
                task = new Task(TaskType.DEADLINE,false, input);
                list.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
                System.out.println("Now you have " + list.size() +" tasks in the list.");
                System.out.println(line);
            }
            else if(input.equals("event")) {
                input = sc.nextLine();
                strings = input.split("/at");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(strings[0]).append("(at:").append(strings[1]).append(")");
                input = stringBuilder.toString();
                task = new Task(TaskType.EVENT,false, input);
                list.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.getTypeString() + task.getDoneString() + input);
                System.out.println("Now you have " + list.size() +" tasks in the list.");
                System.out.println(line);
            }
        }
    }
}
