//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Rishabh Jain
// Course:   CS 300 Spring 2023
//
// Author:   Rishabh Jain
// Email:    rvjain@wisc.edu
// Lecturer: Hobbes LeGault
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models a circular-indexing array queue which stores elements of type Bottle.
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  private Bottle[] bottles;
  private int front;
  private int back;
  private int size;

  /**
   * Constructs a CircularBottleQueue object, initializing its data fields as follows: the bottles
   * oversize array to an empty array of Bottle objects whose length is the input capacity, its size
   * to zero, and both its front and back to -1.
   *
   * @param capacity defining the number of bottles the queue can hold
   * @throws IllegalArgumentException when capacity is not positive
   */
  CircularBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    bottles = new Bottle[capacity];
    front = -1;
    back = -1;
    size = 0;
  }

  /**
   * Checks and returns true if the queue is empty
   *
   * @return boolean value
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks and returns true if the queue is full
   *
   * @return boolean value
   */
  @Override
  public boolean isFull() {
    return size == bottles.length;
  }

  /**
   * Returns the number of bottles in the queue
   *
   * @return size of the queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Add a bottle to the end of the queue
   *
   * @param bottle element of type T to add to queue
   * @throws IllegalStateException when queue is full
   * @throws NullPointerException  when bottle to add is null
   */
  @Override
  public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
    if (bottle == null) {
      throw new NullPointerException();
    }
    if (isFull()) {
      throw new IllegalStateException();
    }

    back = (back + 1) % bottles.length;
    bottles[back] = bottle;
    size++;
    if (front == -1) {
      front = 0;
    }
  }

  /**
   * Removes and returns the first bottle in the queue.
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException when queue is empty
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (isEmpty())
      throw new NoSuchElementException();

    Bottle toReturn = bottles[front];
    bottles[front] = null;

    if (front == back) {
      front = -1;
      back = -1;
    } else {
      front = (front + 1) % bottles.length;
    }

    size--;
    return toReturn;
  }

  /**
   * Returns the first bottle in the queue without removing it
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException when queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (isEmpty())
      throw new NoSuchElementException();
    if (front == -1) {
      return bottles[front++];
    }
    return bottles[front];
  }

  /**
   * Returns a deep copy of this Queue
   *
   * @return a deep copy of the queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    CircularBottleQueue copyQ = new CircularBottleQueue(bottles.length);
    copyQ.front = this.front;
    copyQ.back = this.back;
    copyQ.size = this.size;
    for (int i = 0; i < bottles.length; i++) {
      copyQ.bottles[i] = this.bottles[i];
    }
    return copyQ;
  }

  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line.
   *
   * @return String in expected format, empty string when queue is empty
   */
  @Override
  public String toString() {
    String output = "";
    for (Bottle b : this) {
      output += b.toString() + "\n";
    }
    return output.trim();
  }

  /**
   * Returns an iterator to traverse the queue.
   * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue.
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this);
  }
}
