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
 * This class implements the QueueADT interface using a linked list to store objects of type Bottle.
 * Each node in the linked list is of type LinkedNode.
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
  private LinkedNode<Bottle> front;
  private LinkedNode<Bottle> back;
  private int size;
  private int capacity;

  /**
   * This is the constructor for the class that ensures the parameters are assigned correctly and no
   * exception is thrown.
   *
   * @param capacity The maximum number of bottles the queue can hold.
   * @throws IllegalArgumentException if capacity is invalid.
   */
  LinkedBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException();
    }
    this.capacity = capacity;
    this.front = null;
    this.back = null;
    this.size = 0;
  }

  /**
   * Checks and returns true if the queue is empty
   *
   * @return if the queue is empty or not
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks and returns true if the queue is full
   *
   * @return if the queue is full or not
   */
  @Override
  public boolean isFull() {
    return size == capacity;
  }

  /**
   * Returns the number of bottles in the queue
   *
   * @return the number of bottles in the queue.
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
    if (isFull()) {
      throw new IllegalStateException();
    }
    if (bottle == null) {
      throw new NullPointerException();
    }
    if (size == 0) {
      back = new LinkedNode<Bottle>(bottle);
      front = back;
    } else if (size == 1) {
      back.setNext(new LinkedNode<Bottle>(bottle));
      front.setNext(new LinkedNode<Bottle>(bottle));
      back = back.getNext();
    } else {
      back.setNext(new LinkedNode<Bottle>(bottle));
      back = back.getNext();
    }
    size += 1;
  }

  /**
   * Removes and returns the first bottle in the queue
   *
   * @return the first bottle in the queue.
   * @throws NoSuchElementException when the queue is empty.
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Bottle toReturn = front.getData();

    front = front.getNext();
    size -= 1;
    return toReturn;
  }

  /**
   * Returns the first bottle in the queue without removing it
   *
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException When queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return front.getData();
  }

  /**
   * This method returns a deep copy of the queue.
   *
   * @return the deep copy of this queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue copy = new LinkedBottleQueue(this.capacity);
    if (!isEmpty()) {
      LinkedNode<Bottle> thisTemp = this.front;
      LinkedNode<Bottle> copyTemp = new LinkedNode<Bottle>(thisTemp.getData());
      copy.front = copyTemp;

      while (thisTemp.getNext() != null) {
        thisTemp = thisTemp.getNext();
        LinkedNode<Bottle> newNode = new LinkedNode<Bottle>(thisTemp.getData());
        copyTemp.setNext(newNode);
        copyTemp = newNode;
      }
      copy.back = copyTemp;
    }
    copy.size = this.size;
    return copy;
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
      output += b + "\n";
    }
    return output.trim();
  }

  /**
   * Returns an iterator for traversing the queue's items
   *
   * @return an iterator for traversing the queue's items.
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this);
  }
}
