package kodlamaio.homework6.entities.abstracts;

public class Result implements IResult {

    private boolean success;
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(Boolean success, String message) {
        this(success);
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
