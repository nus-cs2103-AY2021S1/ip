package command;

public class Result {
    private String message;
    public boolean isSuccessful;

    public boolean isSuccessful(){
        return this.isSuccessful;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
