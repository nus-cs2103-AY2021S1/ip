import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static class Task{
        String task;
        boolean complete;
        Task(String task, boolean complete){
            this.task = task;
            this.complete = complete;
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> arrList= new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        String greeting = "____________________________________________________________" +
                "\n" +
                "Hello! I'm Duke \nWhat can I do for you? \n" +
                "____________________________________________________________";

        System.out.println(greeting);
        String userinput = scanner.nextLine();
        while(!userinput.equals("bye")) {
            if (userinput.contains("done")){
                int taskNumber = Integer.parseInt(userinput.substring(5)) - 1 ;

                Task taskCompleted = arrList.get(taskNumber);
                taskCompleted.complete = true;
                System.out.println("Nice! I've marked this task as done:\n"+"[✓] "+taskCompleted.task);
                userinput = scanner.nextLine();
            } else {
                if (userinput.equals("list")) {
                    String arrString = "";

                    for (int i = 0; i < arrList.size(); i++) {
                        if(arrList.get(i).complete==true){
                            arrString += (i + 1) + ". " +"[✓] " + arrList.get(i).task + "\n";}
                        else{
                            arrString += (i + 1) + ". " +"[✗] " + arrList.get(i).task + "\n";}
                        }

                    String reply = "____________________________________________________________\n" + arrString + "\n"
                            + "____________________________________________________________";
                    System.out.println(reply);
                    userinput = scanner.nextLine();
                } else {
                    arrList.add(new Task(userinput,false));

                    String reply = "____________________________________________________________\n" + "added: " + userinput + "\n"
                            + "____________________________________________________________";
                    System.out.println(reply);
                    userinput = scanner.nextLine();
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
