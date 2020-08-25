import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void readTasks(File file) {
        // read tasks from hard disk (./data/luke.txt)
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String readLine = null;
            while((readLine = br.readLine()) != null){
                String[] readEach = readLine.split("\\|");
                Task task = null;
                if (readEach[0].equals("T")) {
                    task = new Todo(readEach[2]);
                } else if (readEach[0].equals("D")) {
                    task = new Deadline(readEach[2], LocalDate.parse(readEach[3]));
                } else if (readEach[0].equals("E")) {
                    task = new Event(readEach[2], readEach[3]);
                }
                if (readEach[1].equals("1")) {
                    task.markAsDone();
                }
                this.tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeTasks(File file, Task task) {
        // add task to the list
        this.tasks.add(task);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            // write task to hard disk (./data/luke.txt)
            String newTask = "";
            String stat = "";
            if (task.isDone) {
                stat = "1|";
            } else {
                stat += "0|";
            }
            if (task instanceof Todo) {
                newTask += "T|" + stat + task.getDescription();
            } else if (task instanceof Deadline) {
                newTask += "T|" + stat  + task.getDescription() + ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                newTask += "T|" + stat  + task.getDescription() + ((Event) task).getAt();
            }
            bw.newLine();
            bw.write(newTask);
            bw.flush();
            printAddSuccess(this.tasks, task);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

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

    public void deleteTask(String input, File file) throws InvalidDeleteException, DeleteIndexOutofboundsException {
        if (input.equals("delete") || input.equals("delete ")) {
            throw new InvalidDeleteException("\n\tThe index of delete cannot be empty.\n\tPlease make sure you follow the correct format.");
        }
        String numbering = input.replaceAll("\\D+", ""); //extract only the digits from the input
        int index = Integer.parseInt(numbering) - 1;
        if (index < 0 || index >= this.tasks.size()) {
            throw new DeleteIndexOutofboundsException("\n\tYou have typed in an invalid number.\n\tPlease check your list again.");
        } else {
            Task delete = this.tasks.get(index);
            this.tasks.remove(index);
            File tempFile = new File("./data/", "temp.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(file));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                String readLine = "";
                int counter = 0;
                while((readLine = br.readLine()) != null) {
                    // remove task from hard disk (./data/luke.txt)
                    counter++;
                    if (counter == index + 1) {
                        continue;
                    }
                    bw.write(readLine);
                    bw.newLine();
                }
                bw.flush();
            } catch (IOException e) {
                System.out.println(e);
            }
            tempFile.renameTo(file);
            System.out.printf("Luke:\n\tThe following task has successfully been deleted!\n\t\t%s\nYou:\n", delete);
        }
    }

    private String countTasks(ArrayList<Task> arrayList) {
        int n = arrayList.size();
        return n <= 1
                ? String.format("%d task", n)
                : String.format("%d tasks", n);
    }

    private void printAddSuccess(ArrayList<Task> arrayList, Task task) {
        String number = countTasks(arrayList);
        System.out.printf("Luke:\n\tThe following task has been successfully added.\n\t\t%s\n\tNow you have %s in your list.\nYou:\n", task, number);
    }
}
