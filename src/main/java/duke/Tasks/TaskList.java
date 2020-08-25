package duke.Tasks;

import duke.Ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    private ArrayList<String> preProcessedTask;

    public TaskList() {
        this.preProcessedTask = new ArrayList<>();
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> inputList){
        this.preProcessedTask = inputList;
        this.listOfTasks = new ArrayList<>();
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public int listSize() {
        return this.listOfTasks.size();
    }

    public void printList(Ui ui) {
        int counter = 1;
        for (Task task: this.listOfTasks) {
            ui.printTask(counter, task);
            counter += 1;
        }
    }

    public void addTodo(Ui ui, String description) {
        ToDo newTask = new ToDo(description);
        this.listOfTasks.add(newTask);
        ui.addTask(listOfTasks.size(), newTask);
    }

    public void addEvent(Ui ui, String description, LocalDate localDate, LocalTime localTime) {
        Event newEvent = new Event(description, localDate, localTime);
        this.listOfTasks.add(newEvent);
        ui.addTask(listOfTasks.size(), newEvent);
    }

    public void addDeadline(Ui ui, String description, LocalDate localDate, LocalTime localTime) {
        Deadline newDeadline = new Deadline(description, localDate, localTime);
        this.listOfTasks.add(newDeadline);
        ui.addTask(listOfTasks.size(), newDeadline);
    }

    public void taskDone(int index, Ui ui) {
        Task temp = this.listOfTasks.get(index);
        temp.completed();
        ui.markDone(temp);
    }

    public void taskDelete(int index, Ui ui) {
        Task temp = this.listOfTasks.get(index);
        this.listOfTasks.remove(index);
        ui.markDelete(listOfTasks.size(), temp);
    }

    public ArrayList<String> convertToFile() {
        ArrayList<String> dataFile = new ArrayList<>();
        for (Task task : this.listOfTasks){
            dataFile.add(task.toData());
        }
        return dataFile;
    }
}
