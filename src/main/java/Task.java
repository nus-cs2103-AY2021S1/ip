package main.java;

public class Task {
    String title;

    Task(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
