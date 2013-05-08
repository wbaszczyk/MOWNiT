import java.util.*;

public class RequestQueue {

	// prosty wrapper na wbudowana kolejke
	// + synchronizowane wstawianie

	Queue<Request> pending;

	public RequestQueue() {

		pending = new ArrayDeque<>();
	}

	public synchronized void push(Request request) {
		pending.add(request);
	}

	public synchronized Request pop() {
		return pending.remove();
	}

	public synchronized boolean isEmpty() {
		return pending.isEmpty();
	}
}
