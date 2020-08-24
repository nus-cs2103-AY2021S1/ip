package main.java.Exception;

public class DukeFileNotFoundException extends DukeException {

    @Override
    public String toString() {
        return super.toString() +" I cannot find your file. Sorry! :(";
    }
}
