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
 * This class models an iterator to iterate over a queue of Bottle objects. When the queue is not
 * empty, Bottle objects are iterated over from the front to the back of the queue. No more Bottle
 * objects are returned by this iterator when all the Bottle objects are traversed (returned). This
 * Iterator iterates over any queue which implements the QueueADT<Bottle> interface. It uses the
 * QueueADT.isEmpty() and QueueADT.dequeue() methods to iterate over a deep copy of the queue.
 */
public class BottleQueueIterator implements Iterator<Bottle> {
  QueueADT<Bottle> bottleQueue;

  /**
   * This is a constructor that initializes the instance fields.
   *
   * @param queue The queue to iterate over, should implement the QueueADT interface.
   * @throws IllegalArgumentException when queue is null
   */
  BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
    if (queue == null) {
      throw new IllegalArgumentException();
    }
    this.bottleQueue = queue.copy();
  }

  /**
   * Returns true if there is the iteration is not yet exhausted, meaning at least one bottle is not
   * iterated over
   *
   * @return boolean value
   */
  @Override
  public boolean hasNext() {
    return !bottleQueue.isEmpty();
  }

  /**
   * Returns the next bottle in the iteration
   *
   * @return Bottle The next bottle in the iteration
   * @throws NoSuchElementException if there are no more elements in the iteration
   */
  @Override
  public Bottle next() throws NoSuchElementException {
    if (bottleQueue.isEmpty()) {
      throw new NoSuchElementException();
    }
    return bottleQueue.dequeue();
  }
}
