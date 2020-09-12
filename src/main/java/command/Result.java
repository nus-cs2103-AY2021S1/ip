package command;

public class Result {
    private String resultMessage;
    public boolean isSuccessful;

    public Result(String resultMessage, boolean isSuccessful ) {
        this.resultMessage = resultMessage;
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful(){
        return this.isSuccessful;
    }

    @Override
    public String toString(){
        return this.resultMessage;
    }
}
