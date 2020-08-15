import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> myTasks = new ArrayList<>();

    public static void greet(){
        String greeting = "\n   _________________________________________________________________"
                + "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?"
                + "\n   _________________________________________________________________";
        System.out.println(greeting);
    }


    public static void addTask(Task task){
        myTasks.add(task);
        String output = "   ______________________________________________________________"
                        + "\n   Got it. I've added this task:"
                        + "\n       " + task
                        + String.format("\n   Now you have %d tasks in the list.", myTasks.size())
                        + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void listTasks(){
        if(myTasks.size() == 0){
            System.out.println("   ______________________________________________________________"
                    + "\n   " + "You have no tasks"
                    + "\n   ______________________________________________________________");
        } else {
            StringBuilder output = new StringBuilder("   ______________________________________________________________");
            for (int i = 0; i < myTasks.size(); i++) {
                output.append("\n    ").append(i + 1).append(". ").append(myTasks.get(i));
            }
            output.append("\n   ______________________________________________________________");
            System.out.println(output);
        }
    }

    public static void bye(){
        String output = "   ______________________________________________________________"
                + "\n   " + "Bye. Hope to see you again soon!"
                + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    private static void markAsDone(int i) {
        Task task = myTasks.get(i-1);
        task.markDone();
        String output = "   ______________________________________________________________"
                + "\n   " + "Nice! I've marked this task as done:"
                + "\n   " + task
                + "\n   ______________________________________________________________";
        System.out.println(output);
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                String input = scanner.nextLine();
                if (input.equals("bye") || input.equals("Bye")) {
                    bye();
                    break;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("done")) {
                    try {
                        if(input.split("done ").length>=2) {
                            String[] inputSplit = input.split("done ");
                            int index = Integer.parseInt(inputSplit[1]);
                            markAsDone(index);
                        } else {
                            throw new DukeException("", DukeExceptionType.NO_DESCRIPTION);
                        }
                    } catch(DukeException e){
                        System.err.println(e);
                    }
                } else if (input.startsWith("todo")) {
                    try {
                        if (input.split("todo ").length>=2) {
                            String[] inputSplit = input.split("todo ");
                            Task task = new TodoTask(inputSplit[1]);
                            addTask(task);
                        } else {
                            throw new DukeException("todo needs description", DukeExceptionType.NO_DESCRIPTION);
                        }
                    } catch (DukeException e){
                        System.err.println(e);
                    }
                } else if (input.startsWith("deadline")) {
                    try {
                        if(input.split("deadline ").length>=2) {
                            String[] inputSplit = input.split("/by ");
                            String name = inputSplit[0].split("deadline ")[1];
                            String time = inputSplit[1];
                            Task task = new DeadlineTask(name, time);
                            addTask(task);
                        } else {
                            throw new DukeException("deadline needs description", DukeExceptionType.NO_DESCRIPTION);
                        }
                    } catch(DukeException e){
                        System.err.println(e);
                    }
                } else if (input.startsWith("event")) {
                    try{
                        if (input.split("event ").length>=2){
                            String[] inputSplit = input.split("/at ");
                            String name = inputSplit[0].split("event ")[1];
                            String time = inputSplit[1];
                            Task task = new EventTask(name, time);
                            addTask(task);
                        } else {
                            throw new DukeException("", DukeExceptionType.NO_DESCRIPTION);
                        }
                    } catch(DukeException e){
                        System.err.println(e);
                    }
                } else {
                    throw new DukeException("", DukeExceptionType.INVALID_TASK);
                }
            } catch(DukeException e) {
                System.err.println(e);
            }
        }

    }

}
