package main.java.exception;

public class DukeCreateFileException extends DukeException {
    @Override
    public String toString(){
        return super.toString() + "Failed to create a file";
    }
}
