import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();


        Scanner Sc = new Scanner(System.in);
        String line = "____________________________\n"
                     +"____________________________\n";

        String logo = "****** ****** ****** ******\n"
                     +"   *   *      *      *\n"
                     +"   *   ****** ****** ******\n"
                     +"*  *   *      *      *\n"
                     +"***    ****** *      *\n";

        System.out.println("My name is\n" + logo);
        System.out.println("What do you want?");
        System.out.println(line);

        //noinspection InfiniteLoopStatement
        while(true) {
            try {
                String[] inputs = Sc.nextLine().trim().split(" ", 2);
                String command = inputs[0];
                String taskDescription = "";
                if (inputs.length > 1) {
                    taskDescription = inputs[1];
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    throw new DukeException(String.format("The description of %s cannot be empty",command));
                }

                switch (command) {
                    case "done": {
                        int index = Integer.parseInt(taskDescription) - 1;
                        if(index < 0 || index > tasks.size() - 1){
                            throw new DukeException("please give a correct task index");
                        }
                        Task doneTask = tasks.get(index);
                        doneTask.complete();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(String.format("  %s", doneTask.toString()));
                        break;
                    }
                    case "list": {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
                        }
                        break;
                    }
                    case "bye": {
                        System.out.println("Bye, Have a Great Time!");
                        break;
                    }
                    case "todo": {
                        Task newTask = new Todo(taskDescription);
                        tasks.add(newTask);
                        newTask.printAddTask();
                        printNumberOfTask(tasks.size());
                        break;
                    }
                    case "deadline": {
                        Task newTask = Deadline.create(taskDescription);
                        tasks.add(newTask);
                        newTask.printAddTask();
                        printNumberOfTask(tasks.size());
                        break;
                    }
                    case "event": {
                        Task newTask = Event.create(taskDescription);
                        tasks.add(newTask);
                        newTask.printAddTask();
                        printNumberOfTask(tasks.size());
                        break;
                    }
                    case "delete": {
                        int index = Integer.parseInt(taskDescription) - 1;
                        if(index < 0 || index > tasks.size() - 1){
                            throw new DukeException("please give a correct task index");
                        }
                        tasks.get(index).printDeleteTask();
                        tasks.remove(index);
                        printNumberOfTask(tasks.size());
                        break;
                    }
                    default: {
                        throw new DukeException("smlj??????");
                    }
                }
            }
            catch(DukeException e){
                System.out.println(e.getMessage());
            }
            System.out.println(line);
        }
    }

    static void printNumberOfTask(int i){
        System.out.println(String.format("Now you have %d tasks in the list.",i));
    }

}