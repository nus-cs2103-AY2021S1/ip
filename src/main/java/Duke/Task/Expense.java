package main.java.Duke.Task;

public class Expense extends Task {
    double amount;

    public Expense(String task, boolean isComplete, double amount) {
        super(task, isComplete);
        this.amount = amount;
    }

    @Override
    public String stringify() {
        //Expense:Phone , Cost:$800
            return this.task +  ",Cost:$" + String.format("%.2f", this.amount);
    }
}
