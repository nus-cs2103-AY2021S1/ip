import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


    protected Scanner sc;
    protected List<Task> tasks;



    public static void main (String[] args) {
        Duke duke = new Duke();
        duke.initializeDuke();
    }

    public void initializeDuke() {
        sc = new Scanner(System.in);
        tasks = new ArrayList<Task>();
        System.out.println("Hello~ I'm Duke!\n" + "What can I do for you?");

        while (sc.hasNextLine()) {
            String nextInput = sc.nextLine();
            nextInput = nextInput.toLowerCase();
            String[] inputArr = nextInput.split(" ", 2);

            System.out.println("____________________________________________________________");
            try {
                if (nextInput.equals("bye")) {
                    sc.close();
                    System.out.println("Goodbye~\n"
                            + "____________________________________________________________");
                    System.exit(0);

                } else if (inputArr[0].equals("list")) {
                    listTasks();
                } else if (inputArr[0].equals("done")) {
                    processDone(inputArr);
                } else if (inputArr[0].equals("todo")) {
                    processToDo(inputArr);
                } else if (inputArr[0].equals("deadline")) {
                    processDeadline(inputArr);
                } else if (inputArr[0].equals("event")) {
                    processEvent(inputArr);
                } else if (inputArr[0].equals("delete")) {
                    processDelete(inputArr);
                } else {
                    processNone();
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("____________________________________________________________");
        }

    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks~");
        } else {
            System.out.println("Here are your tasks~");
            for (int i = 0; i < tasks.size(); i++) {
                String num = (i + 1) + ". ";
                Task current = tasks.get(i);
                System.out.println(num + current);
            }
        }
    }

    public void processDone(String[] valArr) throws DoneException {
        try {
            int doneTask = Integer.parseInt(valArr[1]);
            tasks.get(doneTask - 1).setDone();
            System.out.println("Nice! I've set this task as done~\n" +
                    tasks.get(doneTask - 1));
        } catch (Exception e) {
            throw new DoneException();
        }
    }

    public void processToDo(String[] valArr) throws TodoException {
            try {
                Todo newTask = new Todo(valArr[1]);
                tasks.add(newTask);
                System.out.println("Got it~ I've added this task:\n"
                        + newTask + "\n" + "You now have " + tasks.size() + " tasks in the list~");
            } catch (Exception e) {
                throw new TodoException();
            }
    }

    public void processDeadline(String[] valArr) throws DeadlineException, DeadlineFormatException {

        if (valArr.length < 2) {
            throw new DeadlineException();
        } else {
            try {

                String[] deadlineSplit = valArr[1].split("/by");
                Deadline newTask = new Deadline(deadlineSplit[0], deadlineSplit[1].strip());
                tasks.add(newTask);
                System.out.println("Got it~ I've added this task:\n"
                        + newTask + "\n" + "You now have " + tasks.size() + " tasks in the list~");
            } catch (Exception e) {
                throw new DeadlineFormatException();
            }
        }
    }

    public void processEvent(String[] valArr) throws EventException, EventFormatException {
        if (valArr.length < 2) {
            throw new EventException();
        } else {
            try {
                String[] eventSplit = valArr[1].split("/at");
                Event newTask = new Event(eventSplit[0], eventSplit[1].strip());
                tasks.add(newTask);
                System.out.println("Got it~ I've added this task:\n"
                        + newTask + "\n" + "You now have " + tasks.size() + " tasks in the list~");
            } catch (Exception e) {
                throw new EventFormatException();
            }
        }
    }

    public void processNone() throws DukeException {
        throw new DukeException("Sorry, I don't know what that means~");
    }

    public void processDelete(String[] valArr) throws DeleteException {
        try {
            int deleteTask = Integer.parseInt(valArr[1]);
            Task deleted = tasks.get(deleteTask - 1);
            tasks.remove(deleteTask - 1);
            System.out.println("Alright~ I've removed this task:\n" +
                    deleted);
        } catch (Exception e) {
            throw new DeleteException();
        }
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