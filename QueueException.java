package gol;

//runtime exception for the queue.
public class QueueException extends RuntimeException {
	// no arg constuctor
	public QueueException() {
		super();
	}

	// constructor with String as arg to accept a message as failure output.
	public QueueException(String string) {
		super(string);
	}
}
