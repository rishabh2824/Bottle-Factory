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
import java.util.NoSuchElementException;
/**
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 */
public class BottleFactoryTester {

  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean bottleTester() {
    {
      try {
        Bottle bottle1 = new Bottle("");
      } catch (IllegalArgumentException e) {
      }
    }
    // test equals method
    {
      Bottle bottle1 = new Bottle("Red");
      Bottle bottle2 = new Bottle("Blue");

      if (bottle1.equals(bottle2))
        return false;
      if (!bottle1.equals(bottle1))
        return false;
    }
    // test toString method
    {
      Bottle.resetBottleCounter();
      Bottle bottle1 = new Bottle("Red");

      if (!bottle1.toString().equals("SN1Red:Empty:Open"))
        return false;

      bottle1.fillBottle();
      bottle1.sealBottle();
      if (!bottle1.toString().equals("SN1Red:Filled:Capped"))
        return false;
    }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean linkedBottleQueueTester() {
    try {


      // test constructor - verify fields and exception behavior (when capacity is invalid)
      {
        try {
          LinkedBottleQueue queue1 = new LinkedBottleQueue(0);
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
          return false;
        }
      }

      /*
       * test enqueue, dequeue and peek methods using different scenarios 1) all methods on an empty
       * queue 2) all methods on a full queue 3) all methods on a partially filled queue 4) Verify
       * queue contents (using peek and size) after a sequence of enqueue-dequeue and dequeue-enqueue
       * 5) Enqueue until queue is full and dequeue until queue is empty
       */
      {
        LinkedBottleQueue queue2 = new LinkedBottleQueue(5);
        Bottle bottle1 = new Bottle("Red");
        queue2.enqueue(bottle1);
        if (queue2.size() != 1 && !queue2.peek().equals(bottle1))
          return false;
        Bottle expected = queue2.dequeue();
        if (queue2.size() != 0 && !expected.equals(bottle1))
          return false;
        try {
          queue2.peek();
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
          return false;
        }
      }

      {
        LinkedBottleQueue queue2 = new LinkedBottleQueue(1);
        Bottle bottle1 = new Bottle("Red");
        queue2.enqueue(bottle1);
        if (queue2.size() != 1 && !queue2.peek().equals(bottle1))
          return false;
        try {
          queue2.enqueue(bottle1);
        } catch (IllegalStateException e) {
        } catch (Exception e) {
          return false;
        }
      }

      {
        LinkedBottleQueue queue3 = new LinkedBottleQueue(5);
        Bottle bottle1 = new Bottle("Red");
        queue3.enqueue(bottle1);
        if (queue3.size() != 1 && !queue3.peek().equals(bottle1))
          return false;

        queue3.enqueue(bottle1);
        if (queue3.size() != 2 && !queue3.peek().equals(bottle1))
          return false;
      }

      {
        LinkedBottleQueue queue4 = new LinkedBottleQueue(5);
        Bottle b1 = new Bottle("Red");
        Bottle b2 = new Bottle("Green");
        Bottle b3 = new Bottle("Blue");
        Bottle b4 = new Bottle("Green");
        queue4.enqueue(b1);
        queue4.enqueue(b2);
        queue4.enqueue(b3);
        queue4.enqueue(b4);

        Bottle expected = queue4.dequeue();
        if (queue4.size() != 3 && !expected.equals(b1))
          return false;
        expected = queue4.dequeue();
        if (queue4.size() != 2 && !expected.equals(b2))
          return false;
        expected = queue4.dequeue();

        if (queue4.size() != 1 && !expected.equals(b3))
          return false;

        expected = queue4.dequeue();
        if (queue4.size() != 0 && !expected.equals(b4))
          return false;

      }
      // test copy method
      {
        LinkedBottleQueue testQ = new LinkedBottleQueue(5);
        Bottle bottle1 = new Bottle("Yellow");
        Bottle bottle2 = new Bottle("Red");
        Bottle bottle3 = new Bottle("Blue");
        Bottle bottle4 = new Bottle("Brown");

        testQ.enqueue(bottle1);
        testQ.enqueue(bottle2);
        testQ.enqueue(bottle4);

        LinkedBottleQueue copyQ = (LinkedBottleQueue) testQ.copy();

        copyQ.enqueue(bottle3);
        if (testQ.size() != 3 && !testQ.peek().equals(bottle4) && copyQ.size() != 4)
          return false;
        copyQ.dequeue();

        if (testQ.isEmpty())
          return false;
        if (copyQ.peek() == testQ.peek())
          return false;
      }

      // test toString method
      {
        LinkedBottleQueue testQ = new LinkedBottleQueue(5);
        Bottle bottle1 = new Bottle("Yellow");
        bottle1.fillBottle();
        Bottle bottle2 = new Bottle("Green");
        Bottle bottle3 = new Bottle("Blue");
        bottle3.fillBottle();
        bottle3.sealBottle();
        testQ.enqueue(bottle1);
        testQ.enqueue(bottle2);
        testQ.enqueue(bottle3);
        String expected = bottle1.toString() + "\n" + bottle2.toString() + "\n" + bottle3.toString();
        if (!testQ.toString().equals(expected))
          return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue
   * class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean circularBottleQueueTester() {

    // test constructor - verify fields and exception behavior
    {
      try {
        CircularBottleQueue testQ = new CircularBottleQueue(0);
      } catch (IllegalArgumentException e) {
      } catch (Exception e) {
        return false;
      }
    }
    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all 3 methods on an empty
     * queue 2) all 3 methods on a full queue 3) all 3 methods on a partially filled queue 4) Verify
     * queue contents and size after a sequence of enqueue-dequeue and dequeue-enqueue 5) Enqueue
     * until queue is full and dequeue until queue is empty
     */
    {
      CircularBottleQueue testQ = new CircularBottleQueue(5);
      Bottle b1 = new Bottle("Red");
      testQ.enqueue(b1);
      if (testQ.size() != 1 && !testQ.peek().equals(b1))
        return false;
      Bottle expected = testQ.dequeue();
      if (testQ.size() != 0 && !expected.equals(b1))
        return false;
      try {
        testQ.peek();
      } catch (NoSuchElementException e) {
      } catch (Exception e) {
        return false;
      }
    }

    {
      CircularBottleQueue testQ = new CircularBottleQueue(1);
      Bottle b1 = new Bottle("Red");
      testQ.enqueue(b1);
      if (testQ.size() != 1 && !testQ.peek().equals(b1))
        return false;
      try {
        testQ.enqueue(b1);
      } catch (IllegalStateException e) {
      } catch (Exception e) {
        return false;
      }
    }

    {
      CircularBottleQueue testQ = new CircularBottleQueue(5);
      Bottle b1 = new Bottle("Red");
      testQ.enqueue(b1);
      if (testQ.size() != 1 && !testQ.peek().equals(b1))
        return false;

      testQ.enqueue(b1);
      if (testQ.size() != 2 && !testQ.peek().equals(b1))
        return false;
    }

    {
      CircularBottleQueue testQ = new CircularBottleQueue(5);
      Bottle b1 = new Bottle("Red");
      Bottle b2 = new Bottle("Green");
      Bottle b3 = new Bottle("Blue");
      testQ.enqueue(b1);
      testQ.enqueue(b2);
      testQ.enqueue(b3);

      System.out.println(testQ.toString());
      Bottle expected = testQ.dequeue();
      if (testQ.size() != 2 && !expected.equals(b1))
        return false;
      expected = testQ.dequeue();
      if (testQ.size() != 1 && !expected.equals(b2))
        return true;
      expected = testQ.dequeue();
      if (testQ.size() != 0 && !expected.equals(b3))
        return false;
    }
    // test copy method

    // test toString method

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator
   * class
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   * detected
   */
  public static boolean bottleQueueIteratorTester() {

    /*
     * test 01: Create a LinkedBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the queue. Verify if all the bottles are returned correctly
     */
    {
      LinkedBottleQueue testQ = new LinkedBottleQueue(5);
      Bottle bottle1 = new Bottle("Red");
      Bottle bottle2 = new Bottle("Green");
      bottle2.fillBottle();
      Bottle bottle3 = new Bottle("Blue");
      bottle3.fillBottle();
      bottle3.sealBottle();
      testQ.enqueue(bottle1);
      testQ.enqueue(bottle2);
      testQ.enqueue(bottle3);
      String expected = bottle1.toString() + bottle2.toString() + bottle3.toString();
      String output = "";
      int i = 0;
      for (Bottle b : testQ) {
        output += b.toString();
      }
      if (!output.equals(expected))
        return false;

      LinkedBottleQueue copyQ = (LinkedBottleQueue) testQ.copy();
      if (testQ.isEmpty())
        return false;
    }

    /*
     * test 02: Create a CircularBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the queue. Verify if all the bottles are returned correctly
     */


    return true;
  }

  /**
   * Runs all the tester methods defined in this class.
   *
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed"));
    System.out.println("bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed"));
    System.out.println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" : "Failed"));

    return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester() &&
        circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   *
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed"));
  }
}
