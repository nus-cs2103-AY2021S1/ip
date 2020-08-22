import java.awt.desktop.SystemSleepEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Duke {
    private final List<Task> listOfTask = new ArrayList<>();
    private Path filePath;
    private final static String ignoreCase = "(?i)";

    enum Command {
        LIST, DONE, BYE, TODO, DEADLINE, EVENT, DELETE,
    }

    Duke(Path filePath) {
        this.filePath = filePath;
    }

    public static Duke createDuke() throws DukeException {
        String errMessage = "Woof woof... I can't seem to create a file to store your tasks...\n"
                + "Your tasks would be forgotten at this rate...";
        try {
            String home = System.getProperty("user.dir");
            Path currDir = Paths.get(home).getParent();
//            Path currDir = Paths.get(home);
            Path targetPath = Paths.get(currDir.toString(), "data", "duke.txt");

            if (!java.nio.file.Files.exists(targetPath)) {
                File dir = new File(Paths.get(currDir.toString(),"data").toString());
                boolean isDirCreated = dir.mkdir();
                File file = new File(Paths.get(currDir.toString(),"data", "duke.txt").toString());
                boolean isFileCreated = file.createNewFile();

                if (isDirCreated && isFileCreated) {
                    String welcome = " Woof! I am Yuki your assigned Task Manager!\n"
                            +" I have just created a new file to store all your tasks!\n"
                                    +" So... What is my first assignment? *Woof woof*\n";
                    Print.print(welcome);
                    return new Duke(targetPath);
                } else {
                    throw new DukeException(errMessage);
                }

            } else {
                String welcome = " Hello! I'm Yuki *Woof*\n What can I do for you? *Woof woof*\n";
                Print.print(welcome);
                Duke duke = new Duke(targetPath);
                File f = new File(targetPath.toString());
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    duke.checkTask(s.nextLine());
                }
                return duke;
            }
        } catch (InvalidPathException | DukeException | IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void checkTask(String s) {
        Task t;
        String taskType = s.substring(0, 3);
        String status = s.substring(3,6);
        boolean isDone = status.equals("[" + "\u2713" + "]");
        if (taskType.equals("[T]")) {
            t = new Todo(s.substring(7), isDone);
        } else if (taskType.equals("[D]")){
            int indOfTime = s.lastIndexOf("(FINISH by: ");
            t = new Deadline(s.substring(7, indOfTime), s.substring(indOfTime + 11, s.lastIndexOf(")")), isDone);
        } else {
            int indOfTime = s.lastIndexOf("(APPEAR at: ");
            t = new Event(s.substring(7, indOfTime), s.substring(indOfTime + 11, s.lastIndexOf(")")), isDone);
        }
        listOfTask.add(t);
    }

    public void goodBye() throws DukeException{
         try {
            FileWriter fileWriter = new FileWriter(filePath.toString());
            for (Task t : listOfTask) {
                fileWriter.write(t.toString());
                fileWriter.write(System.getProperty("line.separator"));
            }
            fileWriter.close();
        } catch (IOException e){
             throw new DukeException(e.getMessage());
        }
        String bye = " Bye. Hope to see you again soon! *Woof woof*\n";
        Print.print(bye);
    }

    public String printTotal() {
        return " Now you have " + listOfTask.size() + " tasks in the list. Keep going!!\n";
    }

    public void deleteTask(String message) throws DukeException{
        try {
            int ind = Integer.parseInt(message.substring(6).stripLeading().stripTrailing()) - 1;
            Task t = listOfTask.get(ind);
            listOfTask.remove(ind);
            Print.print(" *WOOF* I have removed:\n   " + t + "\n" + printTotal());
        } catch (IndexOutOfBoundsException e) {
            String errMessage = Print.printFormat(" *Woof!* This task does not exist!\n");
            throw new DukeException(errMessage);
        } catch (NumberFormatException e) {
            String errMessage = Print.printFormat(" *Woof!* Please enter an integer value! I can't really read...\n");
            throw new DukeException(errMessage);
        }
    }

    public void addTask(Task t) {
        listOfTask.add(t);
        Print.print(" *WOOF* I have added:\n   " + t + "\n" + printTotal());
    }

    public void checkAction(String message) throws DukeException{
        Task t;
        if (message.matches(ignoreCase + Command.DEADLINE.name() + "(.*)")) {
            t = Deadline.createTask(message);
            addTask(t);
        } else if (message.matches(ignoreCase + Command.EVENT.name() + "(.*)")) {
            t = Event.createTask(message);
            addTask(t);
        } else if (message.matches(ignoreCase + Command.TODO.name() + "(.*)")) {
            t = Todo.createTask(message);
            addTask(t);
        } else if (message.matches(ignoreCase + Command.DELETE.name() + "(.*)")) {
            deleteTask(message);
        } else {
            String errMessage = Print.printFormat(" I'm sorry but i do not know what you want to do. *woof*\n");
            throw new DukeException(errMessage);
        }
    }

    public void markAsDone(int ind) throws DukeException{
        try {
            listOfTask.get(ind).markAsDone();
            printTotal();
        } catch (Exception e) {
            int taskInd = ind + 1;
            String errMessage = Print.printFormat(" There's no task " + taskInd + " in your list *woof*\n");
            throw new DukeException(errMessage);
        }
    }

    public void printToDos() {
        if (listOfTask.size() == 0) {
            Print.print(" You have no task to complete! *WOOF*\n");
        } else {
            String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            System.out.print(lines);
            System.out.println(" Here are the tasks in your list *Woof*:");
            listOfTask.forEach((task) -> {
                int ind = listOfTask.indexOf(task) + 1;
                System.out.println("   " + ind + "." + task.toString());
            });
            System.out.println(lines);
        }
    }

    public static void main(String[] args) throws DukeException {
        Scanner input = new Scanner(System.in);
        Duke duke = Duke.createDuke();

        while (input.hasNextLine()) {
            try {
                String query = input.nextLine();
                if (query.matches(ignoreCase + Command.BYE.name())) {
                    duke.goodBye();
                    input.close();
                    break;
                } else if (query.matches(ignoreCase + Command.LIST.name())) {
                    duke.printToDos();
                } else if (query.matches(ignoreCase + Command.DONE.name() +"(.*)")) {
                    int taskInd = Integer.parseInt(query.substring(5));
                    duke.markAsDone(taskInd - 1);
                } else {
                    duke.checkAction(query);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
