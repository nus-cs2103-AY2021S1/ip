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

        public String stringify(){
            if(this.complete == true) {
                return "[T][[DONE] " + this.task;
            }else{
                return "[T][UNDONE] " + this.task;
            }
        }
    }
    public static class toDo extends Task{

        toDo(String task, boolean complete) {
            super(task, complete);
        }

        @Override
        public String stringify(){
            if(this.complete == true) {
                return "[T][[DONE] " + this.task;
            }else{
                return "[T][UNDONE] " + this.task;
            }
        }
    }
    public static class Deadline extends Task{
        String deadline;
        Deadline(String task, boolean complete,String deadline) {
            super(task, complete);
            this.deadline = deadline;
        }

        @Override
        public String stringify(){
            if(this.complete == true) {
                return "[D][DONE] " + this.task + " " +"(by: " +this.deadline+")" ;
            }else{
                return "[D][UNDONE] " + this.task + " " +"(by: " +this.deadline+")" ;
            }
        }
    }

    public static class Event extends Deadline{

        Event(String task, boolean complete,String deadline) {
            super(task, complete,deadline);
        }

        @Override
        public String stringify(){
            if(this.complete == true) {
                return "[E][DONE] " + this.task + " " +"(at: " +this.deadline+")" ;
            }else{
                return "[E][UNDONE] " + this.task + " " +"(at: " +this.deadline+")" ;
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<Task> arrList= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String greeting =
                "Hello! I'm Duke. What can I do for you?";

        System.out.println(greeting);
        String userinput = scanner.nextLine();
        while(!userinput.equals("bye")) {
            //if done
            if (userinput.contains("done")){
                int taskNumber = Integer.parseInt(userinput.substring(5)) - 1 ;

                Task taskCompleted = arrList.get(taskNumber);
                taskCompleted.complete = true;
                System.out.println("Nice! I've marked this task as done:\n"+"[DONE] "+taskCompleted.task);
                userinput = scanner.nextLine();
            } else {
                //if list
                if (userinput.equals("list")) {
                    String arrString = "";
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < arrList.size(); i++) {
                        System.out.println((i+1)+"."+arrList.get(i).stringify());
                    }

                    userinput = scanner.nextLine();
                    //add todo
                } else if(userinput.contains("todo")){
                    Task task = new Task(userinput.substring(5),false);
                    arrList.add(task);

                    String reply = "I have added this task: \n"
                            + task.stringify() + "\n"
                            +"Now you have " + arrList.size() + " task(s) in the list." ;
                    System.out.println(reply);
                    userinput = scanner.nextLine();
                }
                else if(userinput.contains("deadline")){
                    int index = userinput.indexOf("/by");
                    String deadlineDate = userinput.substring(index + 4 );
                    Deadline deadline = new Deadline(userinput.substring(9,index),false,deadlineDate);
                    arrList.add(deadline);

                    String reply ="I have added this task: \n"
                            + deadline.stringify() + "\n"
                            +"Now you have " + arrList.size() + " task(s) in the list.";
                    System.out.println(reply);
                    userinput = scanner.nextLine();
                }
                else if(userinput.contains("event")){
                    int index = userinput.indexOf("/at");
                    String eventDate = userinput.substring(index + 4 );
                    Event event = new Event(userinput.substring(6,index),false,eventDate);
                    arrList.add(event);

                    String reply ="I have added this task:"
                            + event.stringify() + "\n"
                            +"Now you have " + arrList.size() + " task(s) in the list.";
                    System.out.println(reply);
                    userinput = scanner.nextLine();
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
