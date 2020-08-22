import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    static final String filePath = "list.txt";
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
        pullList();

        while (sc.hasNextLine()) {
            String nextInput = sc.nextLine();
            nextInput = nextInput.toLowerCase();
            String[] inputArr = nextInput.split(" ", 2);

            System.out.println("____________________________________________________________");
            try {
                if (nextInput.equals("bye")) {
                    sc.close();
                    storelist();
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
                Deadline newTask = new Deadline(deadlineSplit[0], deadlineSplit[1]);
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
                Event newTask = new Event(eventSplit[0], eventSplit[1]);
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
    
    public void pullList() {
        try {
            File file = new File(filePath);
            file.createNewFile();
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                Task temp;
                String line = sc.nextLine();
                String[] nextTaskArr = line.split("\\|");
                int nextTaskLength = nextTaskArr.length;
                String nextTaskType = nextTaskArr[0].strip();
                
                if (nextTaskType.equals("T")) {
                    if (nextTaskLength != 3) { 
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Todo(nextTaskArr[2].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    tasks.add(temp);
                } else if (nextTaskType.equals("D")) {
                    if (nextTaskLength != 4) {
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Deadline(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    tasks.add(temp);
                } else if (nextTaskType.equals("E")) {
                    if (nextTaskLength != 4) {
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Event(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    tasks.add(temp);
                }
            }
                    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void storelist() {
        try {
            String seperator = " | ";
            FileWriter fileWriter = new FileWriter(filePath);
            
            for (int i = 0; i < tasks.size(); i++) {
                Task temp = tasks.get(i);
                String doneStatus = "0";
                if (temp.checkDone()) {
                    doneStatus = "1";
                }
                
                if (temp instanceof Todo) {
                    fileWriter.write("T" + seperator + doneStatus + seperator + 
                            temp.getTaskName() + "\n");
                } else if (temp instanceof Deadline) {
                    Deadline tempDeadline = (Deadline) temp; 
                    fileWriter.write("D" + seperator + doneStatus + seperator +
                            tempDeadline.getTaskName() + seperator + tempDeadline.getDeadlineDate());
                } else if (temp instanceof Event) {
                    Event tempEvent = (Event) temp;
                    fileWriter.write("E" + seperator + doneStatus + seperator +
                            tempEvent.getTaskName() + seperator + tempEvent.getEventDate());
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
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