package gol;

import java.util.ArrayList;

public class CircularQueue {

	private final int MAX_QUEUE;

	private Cell[] cells;

	private int front, back, count;

	public CircularQueue(int capacity) {
		MAX_QUEUE = capacity;
		cells = new Cell[MAX_QUEUE];
		front = count = 0;
		back = MAX_QUEUE - 1;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public boolean isFull() {
		return count == MAX_QUEUE;
	}

	public void enqueue(Cell newCell) throws QueueException {
		if (count == MAX_QUEUE) {
			throw new QueueException("Queue is full");
		}
		back = (back + 1) % MAX_QUEUE;
		cells[back] = newCell;
		++count;
	}

	public Cell dequeue() throws QueueException {
		if (count == 0) {
			throw new QueueException("Queue is empty");
		}
		Cell newCell = cells[front];
		front = (front + 1) % MAX_QUEUE;
		--count;
		return newCell;
	}

	public void dequeueAll() {
		front = count = 0;
		back = MAX_QUEUE - 1;
	}

	public Cell peek() throws QueueException {
		if (count == 0) {
			throw new QueueException("Queue is empty");
		}
		return cells[front];
	}

}
