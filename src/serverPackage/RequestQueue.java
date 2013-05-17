package serverPackage;
import java.util.*;

public class RequestQueue {
	
	List<Request> pending;

	public RequestQueue() {

		pending = new ArrayList<>();
	}

	public synchronized void pushBack(Request request) {
		pending.add(request);
	}

	public synchronized Request popFront() {
		return pending.remove(0);
	}
	
	public Request get(int pos) {
		return pending.get(pos);
	}
	
	public synchronized void remove(int pos) {
		pending.remove(pos);
	}
	
	public synchronized void insert(Request request, int pos) {
		pending.add(pos,  request);
	}

	public synchronized boolean isEmpty() {
		return pending.isEmpty();
	}
	
	public synchronized int size() {
		return pending.size();
	}
	
	public synchronized List<Request> getInnerList() {
		return pending;
	}
}
