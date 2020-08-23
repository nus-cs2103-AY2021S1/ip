import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;

public class TaskManager {
    private List<Task> toDoList;

    public TaskManager() {
        this.toDoList = new ArrayList<>();
    }

    public void manage(String input) throws DukeException {
        if(input.equals("list")) {
            this.displayList();
        }

        else if(input.contains("check")) {
            int checkInt;
            try {
                String intAtBack = input.substring(6, input.length());
                checkInt = Integer.parseInt(intAtBack);
                this.checkList(checkInt);
            }
            catch(Exception e) {
                System.out.print(e);
            }
        }
        else if (input.contains("remove")) {
            int removeInt;
            try {
                String intAtBack = input.substring(7, input.length());
                removeInt = Integer.parseInt(intAtBack);
                this.removeFromList(removeInt);
            }
            catch(Exception e) {
                System.out.print(e);
            }
        }
        else if (input.contains("todo")) {
            if(checkEmpty(input, "todo")) {
                throw new DukeException("Much error! You have to describe your mission!");
            }
            System.out.println("--------------------------------------");
            this.addToList(new ToDo(input.substring(5, input.length())));
            this.taskPrint(input);
            System.out.println("--------------------------------------");
        }

        else if (input.contains("deadline")) {
            if(checkEmpty(input, "deadline")) {
                throw new DukeException("Much error! You have to describe your mission!");
            }
            if(checkPlan(input)) {
                throw new DukeException("Oh no, you do not have a planned timing for your mission!");
            }
            System.out.println("--------------------------------------");
            if(input.contains("/")) {
                int notePos = input.indexOf("/") + 1;
                String note = input.substring(notePos, input.length());
                String echo = input.substring(9, notePos - 1) + " ------> " + note;
                this.taskPrint(echo);
                this.addToList(new Deadline(echo));
            }
            else {
                this.addToList(new Deadline(input.substring(9, input.length())));
            }
            System.out.println("--------------------------------------");


        }

        else if (input.contains("event")) {
            if(checkEmpty(input, "event")) {
                throw new DukeException("Much error! You have to describe your mission!");
            }
            if(checkPlan(input)) {
                throw new DukeException("Oh no, you do not have a planned timing for your mission!");
            }
            System.out.println("--------------------------------------");
            if(input.contains("/")) {
                int notePos = input.indexOf("/") + 1;
                String note = input.substring(notePos, input.length());
                String echo = input.substring(6, notePos - 1) + " ------> " + note;
                this.taskPrint(echo);
                this.addToList(new Event(echo));
            }
            else {
                this.addToList(new Event(input.substring(6, input.length())));
            }
            System.out.println("--------------------------------------");
        }

        else {
            /*System.out.println("--------------------------------------");
            this.addToList(new Task(input, TaskType.U));
            this.taskPrint(input);
            System.out.println("--------------------------------------");*/
            throw new DukeException("Oops! There is no such keyword!");

        }
        int t = toDoList.size();
        System.out.println("You have a total of " + t + " tasks");
        System.out.println("--------------------------------------");
    }
    //checks whether string is empty after keyword
    public Boolean checkEmpty(String input, String keyWord) {
        int keywordLength = keyWord.length();
        String remainingDescription = input.substring(keywordLength, input.length());
        if (remainingDescription.length() == 0 ) {
            return true;
        }
        else if (remainingDescription.length() > 1 && remainingDescription.charAt(1) == 32) {
            return true;
        }
        else if (remainingDescription.length() <= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    //returns true if there is a description
    public Boolean checkPlan(String input) {
        if(input.contains("/")) {
            String remainingDescription = input.substring(input.indexOf("/") + 1, input.length());
            if(remainingDescription.length() != 0) {
                return false;
            }
        }
        return true;
    }

    public void addToList(Task task) {
        this.toDoList.add(task);
        save();
    }

    public void removeFromList(int taskId) {
        Task tr = this.toDoList.get(taskId - 1);
        System.out.println("Task successfully removed!");
        System.out.println("-> " + tr.toString());
        this.toDoList.remove(taskId - 1);
        save();
    }

    public void save() {
        String path = System.getProperty("user.dir") + "\\" + "save.txt";
        if(!Files.exists(Paths.get(path))) {
            try {
                Files.createFile(Paths.get(path));
            }
            catch (IOException e) {
                System.out.println("error creating file");
            }
        }
        try {
            new FileWriter(path, false).close();
        }
        catch (IOException e) {
                System.out.println("error deleting file");
        }

        int i = 1;
        try {
            FileWriter myWriter = new FileWriter("save.txt");
            BufferedWriter out = new BufferedWriter(myWriter);
            for (Task s : this.toDoList) {
                    String write = i + ". " + " [" + s.getType() + "] "
                            + s.toString() + " [" + s.getTaskStatusIcon() + "]";
                    i += 1;
                    out.write(write);
                    out.newLine();

            }
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }

    public void displayList() {
        System.out.println("Check out your missions!");
        int i = 1;
        for (Task s : this.toDoList) {
            System.out.println(i + ". " + " [" + s.getType() + "] "
                    + s.toString() + " [" + s.getTaskStatusIcon() + "]");
            i += 1;
        }
    }

    public void checkList(int checkNumber) {
        Task task = toDoList.get(checkNumber - 1);
        task.markAsDone();
        System.out.println("--------------------------------------");
        System.out.println("Such wow! I have completed the following task!");
        System.out.println(task.toString() + " [" + task.getTaskStatusIcon() + "]");
        System.out.println("--------------------------------------");
    }

    public void taskPrint(String msg) {
        System.out.println("Added : " + msg);
    }
}
