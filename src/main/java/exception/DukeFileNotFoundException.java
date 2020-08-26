package main.java.exception;

public class DukeFileNotFoundException extends DukeException {

    @Override
    public String toString() {
        return super.toString() +" I cannot find your file. Sorry! :(";
    }
}
