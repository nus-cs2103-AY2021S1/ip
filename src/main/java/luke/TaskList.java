package luke;

import luke.exception.DeleteIndexOutofboundsException;
import luke.exception.DoneIndexOutofboundsException;
import luke.exception.InvalidDeleteException;
import luke.exception.InvalidDoneException;
import luke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves a task.Task object that corresponds to the given index.
     *
     * @param i index of the task.Task object
     * @return the task.Task object that corresponds to the given index
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Retrieves the number of tasks for the current luke.TaskList.
     *
     * @return the size of the current luke.TaskList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Completes Tasks for the given file.
     *
     * @param input destination file
     */
    public void completeTask(String input) throws InvalidDoneException, DoneIndexOutofboundsException {
        if (input.equals("done") || input.equals("done ")) {
            throw new InvalidDoneException("\n\tThe index of done cannot be empty.\n\tPlease make sure you follow the correct format.");
        }
        String numbering = input.replaceAll("\\D+", ""); //extract only the digits from the input
        int index = Integer.parseInt(numbering) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new DoneIndexOutofboundsException("\n\tYou have typed in an invalid number.\n\tPlease check your list again.");
        } else {
            Task done = this.tasks.get(index);
            done.markAsDone();
            System.out.printf("Luke:\n\tThe following task has successfully been marked as done!\n\t\t%s\nYou:\n", done);
        }
    }

    public Task deleteTask(int taskNumber) {
        int idx = taskNumber - 1;
        Task deletedTask = this.tasks.get(idx);
        this.tasks.remove(idx);
        return deletedTask;
    }

    public Task doTask(int taskNumber) {
        int idx = taskNumber - 1;
        Task doneTask = this.tasks.get(idx);
        doneTask.markAsDone();
        return doneTask;
    }

//    public void deleteTask(String input, File file) throws InvalidDeleteException, DeleteIndexOutofboundsException {
//        if (input.equals("delete") || input.equals("delete ")) {
//            throw new InvalidDeleteException("\n\tThe index of delete cannot be empty.\n\tPlease make sure you follow the correct format.");
//        }
//        String numbering = input.replaceAll("\\D+", ""); //extract only the digits from the input
//        int index = Integer.parseInt(numbering) - 1;
//        if (index < 0 || index >= this.tasks.size()) {
//            throw new DeleteIndexOutofboundsException("\n\tYou have typed in an invalid number.\n\tPlease check your list again.");
//        } else {
//            Task delete = this.tasks.get(index);
//            this.tasks.remove(index);
//            File tempFile = new File("./data/", "temp.txt");
//            try (BufferedReader br = new BufferedReader(new FileReader(file));
//                 BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
//                String readLine = "";
//                int counter = 0;
//                while((readLine = br.readLine()) != null) {
//                    // remove task from hard disk (./data/luke.txt)
//                    counter++;
//                    if (counter == index + 1) {
//                        continue;
//                    }
//                    bw.write(readLine);
//                    bw.newLine();
//                }
//                bw.flush();
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//            tempFile.renameTo(file);
//            System.out.printf("Luke:\n\tThe following task has successfully been deleted!\n\t\t%s\nYou:\n", delete);
//        }
//    }

    public void findTask(String input) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task current = this.tasks.get(i);
            if (current.getDescription().contains(input)) {
                result.add(current);
            }
        }
        if (result.size() < 1) {
            System.out.printf("Luke:\n\tYou don't have any tasks in your list :(\nYou:\n");
        } else {
            String todoSummary = "Luke:\n\tHere are the tasks in your list.";
            for (int i = 0; i < result.size(); i++) {
                Task current = result.get(i);
                todoSummary += String.format("\n\t%d.%s", i + 1, current);
            }
            System.out.printf("%s\nYou:\n", todoSummary);
        }
    }

}
