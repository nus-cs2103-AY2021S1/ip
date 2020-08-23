import java.util.*;
import java.time.LocalDate;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Duke {
    FileUtil db;

    Duke() throws IOException {
        try {
            db = new FileUtil();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    public boolean isTaskModification(String action) {
        return action.equals("done") || action.equals("delete");
    }

    public List<Task> getToDoLst() {
        return db.getToDoLst();
    }

    public int getToDoLstSize() {
        return db.getToDoLstSize();
    }

    public String getTotalItemsDescription() {
        return db.getTotalItemsDescription();
    }

    public Task setToDoItemStatus(int i, boolean bool) {
        Task task = getToDoLst().get(i);

        task.setStatus(bool);

        return task;
    }

    public Task addToDoItem(String type, String todo, LocalDate date) {
        Task newTask = null;

        if (type.equals("todo")) {
            newTask = new Task("T", todo, null,false);
        } else if (type.equals("deadline")) {
            newTask = new Task("D", todo, date, false);
        } else if (type.equals("event")) {
            newTask = new Task("E", todo, date, false);
        }

        db.addToDoItem(newTask);

        return newTask;
    }

    public Task removeToDoItem(int i) {
        Task deletedTask = db.removeToDoItem(i);

        return deletedTask;
    }

    public void save() throws IOException {
        try {
            db.save();
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        Duke duke = null;

        try {
            duke = new Duke();
        } catch (FileNotFoundException e) {
            System.out.println("Data directory or file not found, see error");

            System.out.println(e.getMessage());

            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return;
        }

        Scanner scanner = new Scanner(System.in);
        String line = "";

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(!line.equals("bye")) {
            if (!line.equals("")) {
                if (line.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < duke.getToDoLstSize(); i++) {
                        System.out.println(String.format("%d.%s", i + 1, duke.getToDoLst().get(i)));
                    }
                } else if (duke.isTaskModification(line.split(" ")[0])) {
                    String[] lineData = line.split(" ");
                    int i = Integer.parseInt(lineData[1]) - 1;

                    if (line.split(" ")[0].equals("done")) {
                        Task updatedTask = duke.setToDoItemStatus(i, true);

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(updatedTask);
                    }  else {
                        Task deletedTask = duke.removeToDoItem(i);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedTask);
                        System.out.println(duke.getTotalItemsDescription());
                    }
                } else {
                    String[] lineData = line.split(" ");
                    String type = lineData[0];
                    boolean isValidEntry = true;

                    if (type.equals("todo")) {
                        if (lineData.length == 1) {
                            isValidEntry = false;
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            line = String.join(" ", Arrays.copyOfRange(lineData, 1, lineData.length));

                            Task newTask = duke.addToDoItem(type, line, null);

                            System.out.println("Got it. I've added this task: ");
                            System.out.println(newTask);
                            System.out.println(duke.getTotalItemsDescription());
                        }
                    } else if (type.equals("deadline") || type.equals("event")) {
                        int byIndex = -1;

                        for (int i = 0; i < lineData.length; i++) {
                            if (lineData[i].equals("/by") || lineData[i].equals("/at")) {
                                byIndex = i;
                                break;
                            }
                        }

                        String dateStr = lineData[byIndex + 1];
                        LocalDate date = LocalDate.parse(dateStr);

                        line = String.join(" ", Arrays.copyOfRange(lineData, 1, byIndex));

                        Task newTask = duke.addToDoItem(type, line, date);

                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newTask);
                        System.out.println(duke.getTotalItemsDescription());
                    } else {
                        isValidEntry = false;
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }

            line = scanner.nextLine();
        }

        try {
            duke.save();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}