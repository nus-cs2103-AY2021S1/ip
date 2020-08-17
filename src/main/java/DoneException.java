package main.java;

public class DoneException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " You have to specify which number in the list to be set as done";
    }
}
