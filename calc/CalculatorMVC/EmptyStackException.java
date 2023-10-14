public class EmptyStackException extends RuntimeException {
    private String msg;

    public EmptyStackException(String msg) {
        super(msg);
    }
}
