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

    /**
     * Returns List of tasks.
     *
     * @return List of tasks
     */
    public List<Task> getToDoLst() {
        return db.getToDoLst();
    }

    /**
     * Returns description of total number of items in todo list.
     *
     * @return description of total number of items in todo list.
     */
    public String getTotalItemsDescription() {
        return db.getTotalItemsDescription();
    }

    /**
     * Returns description of total number of items in todo list.
     *
     * @param i index of Task in todo list
     * @param bool new isDone status to set in task
     *
     * @return updated task
     */
    public Task setToDoItemStatus(int i, boolean bool) {
        Task task = getToDoLst().get(i);

        task.setStatus(bool);

        return task;
    }

    /**
     * Add new task to todo list.
     *
     * @param type type of task
     * @param todo description of task
     * @param date deadline of task (LocalDate)
     *
     * @return new task
     */
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

    /**
     * Remove task from todo list.
     *
     * @param i index of Task in todo list
     *
     * @return deleted task
     */
    public Task removeToDoItem(int i) {
        Task deletedTask = db.removeToDoItem(i);

        return deletedTask;
    }

    /**
     * Save tasks to storage.
     *
     * @throws IOException
     */
    public void save() throws IOException {
        try {
            db.save();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Run.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        ui.showWelcomeMessage();

        while(!line.equals("bye")) {
            if (!line.equals("")) {
                if (line.equals("list")) {
                    ui.showListItems(getToDoLst(), "list");
                } else if (line.split(" ")[0].equals("find")) {
                    String searchTerm = parser.processFind(line);

                    List<Task> tasks = db.searchToDoItems(searchTerm);

                    ui.showListItems(tasks, "search");
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

    /**
     * Entry point.
     */
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