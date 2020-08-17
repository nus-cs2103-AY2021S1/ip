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

    public static class DukeException extends Exception{
        public DukeException(String s){
            super(s);
        }
    }
    public static void main(String[] args) throws DukeException {
        ArrayList<Task> arrList= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String greeting =
                "Hello! I'm Duke. What can I do for you?";

        System.out.println(greeting);
        String userinput = "";
        while(!userinput.equals("bye")) {
            System.out.println("Input:");
            userinput = scanner.nextLine();
            //if done
            if (userinput.contains("done")){
                if(userinput.length()<6) {
                    throw new DukeException("Must include number after 'done'.");
                }
                int taskNumber = Integer.parseInt(userinput.substring(5)) - 1 ;
                if(taskNumber>arrList.size()){
                    throw new DukeException("Number cannot be longer than list.");
                }
                Task taskCompleted = arrList.get(taskNumber);
                taskCompleted.complete = true;
                System.out.println("Nice! I've marked this task as done:\n"+"[DONE] "+taskCompleted.task);
            } else {
                //if list
                if (userinput.equals("list")) {
                    String arrString = "";
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < arrList.size(); i++) {
                        System.out.println((i+1)+"."+arrList.get(i).stringify());
                    }
                    //add todo
                }else if(userinput.contains("delete")){
                    if(userinput.length()<6){
                        throw new DukeException(("Must include number after 'delete'"));
                    }
                    int taskNumber = Integer.parseInt(userinput.substring(7)) - 1 ;
                    Task taskDeleted = arrList.get(taskNumber);
                    arrList.remove(taskNumber);
                    System.out.println("I have removed the task:\n"+taskDeleted.stringify()+"\n"+"Now you have " +
                            arrList.size()+" tasks in the list.");
                }
                else if(userinput.contains("todo")){
                    if(userinput.length()<5){
                        throw new DukeException("Must include description for todo");
                    }
                    String description = userinput.substring(5);

                    Task task = new Task(description, false);
                    arrList.add(task);

                    String reply = "I have added this task:\n"
                            + task.stringify() + "\n"
                            + "Now you have " + arrList.size() + " task(s) in the list.";
                    System.out.println(reply);

                }
                //add deadline
                else if(userinput.contains("deadline")){
                    if(!userinput.contains("/by")){
                        throw new DukeException("deadline must include '/by'");
                    }else {
                        int index = userinput.indexOf("/by");
                        String deadlineDate = userinput.substring(index + 4);
                        Deadline deadline = new Deadline(userinput.substring(9, index), false, deadlineDate);
                        arrList.add(deadline);

                        String reply = "I have added this task:\n"
                                + deadline.stringify() + "\n"
                                + "Now you have " + arrList.size() + " task(s) in the list.";
                        System.out.println(reply);
                    }
                }
                //add event
                else if(userinput.contains("event")){
                    if(!userinput.contains("/at")){
                        throw new DukeException("event must include '/at'");
                    }else {
                        int index = userinput.indexOf("/at");
                        String eventDate = userinput.substring(index + 4);
                        Event event = new Event(userinput.substring(6, index), false, eventDate);
                        arrList.add(event);

                        String reply = "I have added this task:"
                                + event.stringify() + "\n"
                                + "Now you have " + arrList.size() + " task(s) in the list.";
                        System.out.println(reply);
                    }
                }
                else{
                    System.out.println("Please input:\n"+
                            "1)list - to access the list\n" +
                            "2)todo - to create a todo task\n" +
                            "3)deadline - to create a deadline\n" +
                            "4)event - to schedule an event\n" +
                            "5)done - to mark tasks as done\n" +
                            "6)delete - to delete tasks from the list");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
