package main.java.ui;

import main.java.tasklist.TaskList;
import main.java.tasks.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public Ui(){
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void displayWelcome() {
        System.out.println("Hello! I am YURINA Chan.\nWhat can I do for you? ᕕ( ᐛ )ᕗ");
    }

    public void displayBye() {
        System.out.println("Bye~ Hope to see you again soon! ∠( ᐛ 」∠)＿");
    }

    public void showLoadingError() {
        System.out.println("There is no data file found. " +
                "A new file will be created at the end of the session. ⊂(￣▽￣)⊃");
    }

    public void displayAddTaskMessage(Task task){
        System.out.println(task.addMessage());
    }

    public void displayDeleteMessage(Task task){
        System.out.println(task.deleteMessage());
    }

    public void displayMarkAsDoneMessage(Task task){
        System.out.println(task.markAsDoneMessage());
    }

    public void displayErrorMessage(Exception ex){
        System.out.println(ex.getMessage());
    }


    public void showTask(TaskList taskList){
        if (taskList.getTasks().size() == 0) {
            System.out.println("This is no task in your task list yet. Add one now! (/^▽^)/");
        }

        int no = 1;
        for (Task task : taskList.getTasks()) {
            String prefix = task.toString().substring(0, 7);
            String end = task.toString().substring(7);
            System.out.println(prefix + no + ". " + end);
            no++;
        }
    }

    public void showTask(TaskList taskList, LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateString = date.format(formatter);
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<Task> filtered = tasks.stream().filter(Task::getHasTime).filter(task -> task.getTime().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filtered.size() == 0) {
            System.out.println("No tasks on this day! Chill Chill~ ٩(˘◡˘)۶");
        } else {
            System.out.println("On " + dateString + ", you have the following tasks:");
            int no = 1;
            for (Task task : filtered) {
                String state = "[" + task.getStatusIcon() + "] ";
                System.out.println(task.toString());
            }
        }
    }

}
