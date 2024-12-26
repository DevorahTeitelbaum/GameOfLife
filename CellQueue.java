package gol;

import java.util.ArrayList;

//this is a previously created queue, adapted SPECIFICALLY for the GOL.
public class CellQueue {
	private final int MAX_QUEUE;
	private Cell[] cells;
	private int front, back, count;

	// note that it can only handle cells.
	public CellQueue(int capacity) {
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

	// this is a circular queue, and therefore follows all logic as such.
	public void enqueue(Cell newCell) throws QueueException {
		if (count == MAX_QUEUE) {
			throw new QueueException("Queue is full");
		}
		// can wrap around using mod
		back = (back + 1) % MAX_QUEUE;
		cells[back] = newCell;
		++count;
	}

	// dequeuing is done same as for circular, hut returns only Cells as is specific
	public Cell dequeue() throws QueueException {
		if (count == 0) {
			throw new QueueException("Queue is empty");
		}
		Cell newCell = cells[front];
		front = (front + 1) % MAX_QUEUE;
		--count;
		return newCell;
	}

	// same as constructor, dequeueAll resets the queue.
	public void dequeueAll() {
		front = count = 0;
		back = MAX_QUEUE - 1;
	}

	// we don't need peek in GOL, but it is still a queue, so should have.
	public Cell peek() throws QueueException {
		if (count == 0) {
			throw new QueueException("Queue is empty");
		}
		return cells[front];
	}

}
