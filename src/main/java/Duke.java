import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    private Storage db;
    private Parser parser;
    private Ui ui;

    Duke() throws IOException {
        try {
            db = new Storage();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        ui = new Ui();
        parser = new Parser();
    }

//    public boolean isTaskModification(String action) {
//        return action.equals("done") || action.equals("delete");
//    }

    public List<Task> getToDoLst() {
        return db.getToDoLst();
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

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        ui.showWelcomeMessage();

        while(!line.equals("bye")) {
            if (!line.equals("")) {
                if (line.equals("list")) {
                    ui.showListItems(getToDoLst());
                } else if (parser.isTaskModification(line.split(" ")[0])) {
                    String[] processedData = parser.processModification(line);
                    String type = processedData[0];
                    int i = Integer.valueOf(processedData[1]);

                    if (type.equals("done")) {
                        Task updatedTask = setToDoItemStatus(i, true);

                        ui.showDoneMessage();
                        System.out.println(updatedTask);
                    }  else {
                        Task deletedTask = removeToDoItem(i);

                        ui.showDeleteMessage();
                        System.out.println(deletedTask);
                        System.out.println(getTotalItemsDescription());
                    }
                } else {
                    String[] lineData = parser.processTaskItem(line);
                    String type = lineData[0];

                    if (type.equals("todo")) {
                        if (lineData.length == 1) {
                            ui.showErrorMessage();
                        } else {
                            line = String.join(" ", Arrays.copyOfRange(lineData, 1, lineData.length));

                            Task newTask = addToDoItem(type, line, null);

                            ui.showAddMessage();
                            System.out.println(newTask);
                            System.out.println(getTotalItemsDescription());
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

                        Task newTask = addToDoItem(type, line, date);

                        ui.showAddMessage();
                        System.out.println(newTask);
                        System.out.println(getTotalItemsDescription());
                    } else {
                        ui.showWrongInputMessage();
                    }
                }
            }

            line = scanner.nextLine();
        }

        try {
            save();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        ui.showByeMessage();
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

        duke.run();
    }
}