import java.util.Scanner;

public class Duke {

    private Scanner sc;
    int noOfTasks;
    Task[] tasks;

    public static void main (String[] args) {
        Duke duke = new Duke();
        duke.initializeDuke();

    }

    public void initializeDuke() {
        sc = new Scanner(System.in);
        tasks = new Task[100];
        System.out.println("Hello~ I'm Duke!\n" + "What can I do for you?");

        while (sc.hasNextLine()) {
            String nextInput = sc.next();
            System.out.println("____________________________________________________________");

            if (nextInput.equals("bye")) {
                sc.close();
                System.out.println("Goodbye~\n"
                + "____________________________________________________________");
                System.exit(0);
                return;

            } else if (nextInput.equals("list")) {
                listTasks();
            } else if (nextInput.equals("done")) {
                processDone();
            } else if (nextInput.equals("todo")) {
                processToDo();
            } else if (nextInput.equals("deadline")) {
                processDeadline();
            } else if (nextInput.equals("event")) {
                processEvent();
            }

            System.out.println("____________________________________________________________");
        }

    }

    public void listTasks() {
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }

    public void processDone() {
        int doneTask = Integer.parseInt(sc.next()) - 1;
        tasks[doneTask].setDone();
        System.out.println("Nice! I've set this task as done~\n" +
                tasks[doneTask]);
    }

    public void processToDo() {
        Todo newTask = new Todo(sc.nextLine());
        tasks[noOfTasks] = newTask;
        noOfTasks += 1;
        System.out.println("Got it~ I've added this task:\n"
                + newTask + "\n" + "You now have " + noOfTasks + " tasks in the list~");
    }

    public void processDeadline() {
        String[] deadlineSplit = sc.nextLine().split("/by");
        Deadline newTask = new Deadline(deadlineSplit[0], deadlineSplit[1]);
        tasks[noOfTasks] = newTask;
        noOfTasks += 1;
        System.out.println("Got it~ I've added this task:\n"
                + newTask + "\n" + "You now have " + noOfTasks + " tasks in the list~");
    }

    public void processEvent() {
        String[] eventSplit = sc.nextLine().split("/at");
        Event newTask = new Event(eventSplit[0], eventSplit[1]);
        tasks[noOfTasks] = newTask;
        noOfTasks += 1;
        System.out.println("Got it~ I've added this task:\n"
                + newTask + "\n" + "You now have " + noOfTasks + " tasks in the list~");
    }


//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}