import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // print all the content in the list
    public static void printList(ArrayList<Task> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + list.get(i).toString());
        }
    }

    // return the description of Event
    public static String getEventDescription(String[] command, int ptr){
        String res = "";
        Boolean start = false;
        for(int i = ptr; i < command.length; i++){
            if(command[i].equals("/at")) break;
            if(start){
                res += (" " + command[i]);
            }
            else{
                start = true;
                res += command[i];
            }
        }
        return res;
    }

    // return the description of Event
    public static String getDeadlineDescription(String[] command, int ptr){
        String res = "";
        Boolean start = false;
        for(int i = ptr; i < command.length; i++){
            if(command[i].equals("/by")) break;
            if(start){
                res += (" " + command[i]);
            }
            else{
                start = true;
                res += command[i];
            }
        }
        return res;
    }

    // return the string after the substring "/by" or "/at"
    public static String getEventTime(String[] command){
        int len = command.length;
        if(len <= 0) return null;
        int i = 0;
        String res = "";
        while(i < len && !command[i].equals("/at")){
            i++;
        }
        i++;
        while(i < len){
            res += command[i++];
        }
        return res;
    }

    // return the string after the substring "/by" or "/at"
    public static String getDeadlineTime(String[] command){
        int len = command.length;
        if(len <= 0) return null;
        int i = 0;
        String res = "";
        while(i < len && !command[i].equals("/by")){
            i++;
        }
        i++;
        while(i < len){
            res += command[i++];
        }
        return res;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print greetings of chatbot
        System.out.println("____________________________________________________________\n" +
                           "Hello! I'm Duke\n" +
                           "What can I do for you?" +
                           "\n____________________________________________________________");

        // add command entered by the user to the list
        ArrayList<Task> list = new ArrayList<>();
        String inputCommand;
        String[] command;
        int ptr;
        Scanner sc = new Scanner(System.in);
        while(true){
            inputCommand = sc.nextLine();
            command = inputCommand.split(" ");
            ptr = 0;

            // if the user input is empty, continue the loop
            if(command.length <= 0 || inputCommand.equals("")){
                continue;
            }

            while(/*ptr < command.length && */command[ptr].equals("")) ptr++;

            if(command[ptr].equals("bye")){
                break;
            }
            else if(command[ptr].equals("list")){
                System.out.println("____________________________________________________________");
                printList(list);
                System.out.println("____________________________________________________________");
            }
            else if(command[ptr].equals("done")){
                int taskNumber = Integer.parseInt(command[ptr + 1]);
                if(taskNumber > list.size()){
                    System.out.println("no such task: task" + taskNumber + " as you only have " + list.size() + " in total");
                }
                else{
                    Task task = list.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                }
            }
            else if(command[ptr].equals("todo")){
                Todo newTodo = new Todo(inputCommand);
                list.add(newTodo);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        newTodo.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else if(command[ptr].equals("deadline")){
                String by = getDeadlineTime(command), description = getDeadlineDescription(command, ptr + 1);
                Deadline deadline = new Deadline(description, by);
                list.add(deadline);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        deadline.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else if(command[ptr].equals("event")){
                String at = getEventTime(command), description = getEventDescription(command, ptr + 1);
                Event event = new Event(description, at);
                list.add(event);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        event.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else{
                Task task = new Task(inputCommand);
                list.add(task);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        task.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________\n" +
                           "Bye. Hope to see you again soon!" +
                           "\n____________________________________________________________");
    }
}
