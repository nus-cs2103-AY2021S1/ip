package main.java.Duke.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;
    public ArrayList<Expense> expenses;

    public TaskList(ArrayList<Task> tasklist, ArrayList<Expense> expenses) {
        this.list = tasklist;
        this.expenses = expenses;
    }

    /**
     * Deletes the task from the Task List.
     *
     * @param taskNumber Task number to be deleted.
     */
    public String deleteTask(int taskNumber) {
        Task taskDeleted = list.get(taskNumber);
        list.remove(taskNumber);
        return ("I have removed the task:\n" + taskDeleted.stringify() + "\n" + "Now you have "
                + list.size() + " tasks in the list.");
    }

    /**
     * Adds the task to the Task List
     *
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        list.add(task);
        return
                "I have added this task:\n"
                        + task.stringify() + "\n"
                        + "Now you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Mark the task as completed.
     *
     * @param taskNumber Task number of the task to be marked as completed.
     */
    public String completeTask(int taskNumber) {
        Task taskCompleted = this.list.get(taskNumber);
        taskCompleted.isComplete = true;
        return "Nice! I've marked this task as done:\n" + "[✓] " + taskCompleted.task ;
    }

    /**
     * Shows the Task list.
     */
    public String showList(){
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            str += ((i+1)+"." + list.get(i).stringify() + "\n");
        }
        return str;
    }

    public String findTask(String str) {
        String returnStr = ("Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.task.contains(str)) {
                returnStr += ((i + 1) + "." + task.stringify() + "\n");
            }
        }
        return returnStr;
    }

    public double calculateTotalExpense(){
        double sum = 0;

        for (int i = 0; i < expenses.size() ; i++) {
            sum+= expenses.get(i).amount;
        }
        return sum;
    }

    public String addExpense(Expense expense){
        expenses.add(expense);
        return
                "I have added this expense:\n"
                        + expense.stringify() + "\n"
                        + "Now your total expense is: $" + String.format("%.2f", this.calculateTotalExpense());
    }

    public String showExpenses(){
        String str = "Here are the expenses:\n";

        for (int i = 0; i < expenses.size(); i++) {
            str += ((i+1)+"." + expenses.get(i).stringify() + "\n");
        }
        return str
                + "\n"
                + "Total expenses " + this.calculateTotalExpense();
    }

    public String deleteExpense(int expenseNumber) {
        Expense expenseDeleted = expenses.get(expenseNumber);
        expenses.remove(expenseNumber);
        return ("I have removed the expense:\n" + expenseDeleted.stringify() + "\n" + "Now you have "
                + expenses.size() + " expenses in the list.");
    }
}
