package main.java.Exception;

public class NoIndexException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " You have to specify which number in your lists";
    }
}
