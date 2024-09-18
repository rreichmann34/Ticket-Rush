///////////////////////////////////////////////////////////////////////////////
//
// Title: The TicketQueueTester class tests the methods from the TicketQueue class.
//
// Course: CS 300 Fall 2023
//
// Author: Remington Reichmann
// Email: rreichmann@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
import java.util.Iterator;

public class TicketQueueTester {

  /**
   * Helper method that tests to make sure the constructor throws an exception when thrown an
   * invalid capacity
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testConstructorException() {
    try {
      TicketQueue queue = new TicketQueue(-1); // This line should throw an IllegalArgumentException
    } catch (IllegalArgumentException e) {
      return true;
    }
    // Any other exceptions should be caught and return false
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tests the TicketQueue constructor with a valid implementation
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testValidConstructor() {
    try {
      int capacity = 15;
      TicketQueue queue = new TicketQueue(capacity);

      // The queue should be empty
      if (!queue.isEmpty()) {
        return false;
      }
      // The queue should not be full
      if (queue.isFull()) {
        return false;
      }
      // Current size of the queue should be 0
      if (queue.size() != 0) {
        return false;
      }
      // Capacity should be 15
      if (queue.capacity() != capacity) {
        return false;
      }
      if (!queue.toString().equals("")) {
        return false;
      }
      // If all of the methods defined in case 2 pass, return true
      return true;
    } catch (Exception e) {
      return false; // Return false because there shouldn't be an exception thrown
    }
  }

  /**
   * Tests the TicketQueue constructor when 0 is passed as a parameter. It should throw an error.
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testConstructorExceptionWithZero() {
    try {
      TicketQueue queue = new TicketQueue(0); // This line should throw an IllegalArgumentException
    } catch (IllegalArgumentException e) {
      return true;
    } catch (Exception e) {
      return false; // Any other exception that is caught should return false
    }
    return false;
  }

  /**
   * Tests the constructor with 3 different tests: 1) Pass a negative number into the constructor 2)
   * Test a valid implementation, meaning pass a positive, nonzero number into the constructor 3)
   * Test the constructor with 0 as a parameter. It should still throw an exception
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testConstructor() {
    boolean test1 = testConstructorException();
    boolean test2 = testValidConstructor();
    boolean test3 = testConstructorExceptionWithZero();

    return test1 && test2 && test3;
  }

  /**
   * Tests the case where the dequeue method should throw an exception
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testDequeueException() {
    try {
      TicketQueue queue = new TicketQueue(10);

      queue.dequeue(); // This line should throw a NoSuchElementException
    } catch (NoSuchElementException e) {
      return true;
    }
    // Return false if any unexpected exceptions are thrown
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Test the dequeue method on a valid implementation
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testValidDequeue() {
    try {
      TicketQueue queue = new TicketQueue(10);

      // Creating random users
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      TicketSiteUser user2 = new TicketSiteUser("username2", "1111111111", "5555555555555555");
      user2.login("username2", "1111111111");
      TicketSiteUser user3 = new TicketSiteUser("username3", "2222222222", "6666666666666666");
      user3.login("username3", "2222222222");

      // Add the random users to the queue
      queue.enqueue(user1);
      queue.enqueue(user2);
      queue.enqueue(user3);

      // Dequeue should remove and return the first user in the list
      if (!(queue.dequeue().equals(user1))) {
        return false;
      }
      if (!(queue.dequeue().equals(user2))) {
        return false;
      }
      if (!(queue.dequeue().equals(user3))) {
        return false;
      }

      // If dequeue returns the same value each time, return true
      return true;
    }
    // No exceptions should be caught. If any are, return false.
    catch (Exception e) {
      return false;
    }
  }

  /**
   * Tests the dequeue method after trying to dequeue elements after the elements have all been
   * removed
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testDequeueException2() {
    try {
      TicketQueue queue = new TicketQueue(10);

      // Create random users
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      TicketSiteUser user2 = new TicketSiteUser("username2", "1111111111", "5555555555555555");
      user2.login("username2", "1111111111");
      TicketSiteUser user3 = new TicketSiteUser("username3", "2222222222", "6666666666666666");
      user3.login("username3", "2222222222");

      // Add all users to the queue
      queue.enqueue(user1);
      queue.enqueue(user2);
      queue.enqueue(user3);

      // Dequeue should return the first node in the queue
      boolean placeholder = true;
      if (!(queue.dequeue().equals(user1))) {
        placeholder = false;
      }
      if (!(queue.dequeue().equals(user2))) {
        placeholder = false;
      }
      if (!(queue.dequeue().equals(user3))) {
        placeholder = false;
      }

      if (placeholder == true) {
        queue.dequeue(); // This line should throw a NoSuchElementException
      }
    } catch (NoSuchElementException e) {
      return true;
    }
    // If any other exceptions are thrown, catch them and return false.
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tests the dequeue method from TicketQueue based on the following cases: 1) Test dequeue on an
   * empty queue. It should throw a NoSuchElementException 2) Test dequeue on a valid implementation
   * 3) Test dequeue on an empty queue once all elements have been added and removed from the queue
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testDequeue() {
    boolean test1 = testDequeueException();
    boolean test2 = testValidDequeue();
    boolean test3 = testDequeueException2();

    return test1 && test2 && test3;
  }

  /**
   * Test adding a user to a queue that is already full. An IllegalStateException should be thrown.
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testQueueFull() {
    try {
      TicketQueue queue = new TicketQueue(2);

      // Create random users
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      TicketSiteUser user2 = new TicketSiteUser("username2", "1111111111", "5555555555555555");
      user2.login("username2", "1111111111");
      TicketSiteUser user3 = new TicketSiteUser("username3", "2222222222", "6666666666666666");
      user3.login("username3", "2222222222");

      // Add all users to the queue
      queue.enqueue(user1);
      queue.enqueue(user2);
      queue.enqueue(user3); // This line should throw an IllegalStateException
    } catch (IllegalStateException e) {
      return true;
    }
    // If any other exceptions are thrown, return false
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tests the case where the user cannot be added to the queue because they do not have the ability
   * to buy a ticket
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testCannotBuyTicket() {
    boolean test1 = false;
    try {
      TicketQueue queue = new TicketQueue(10);

      // Create a TicketSiteUser and log them out
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      user1.logout();

      queue.enqueue(user1); // This line should throw an IllegalArgumentException
    } catch (IllegalArgumentException e) {
      test1 = true;
    }
    // If any other exceptions are thrown, catch them and return false
    catch (Exception e) {
      test1 = false;
    }

    boolean test2 = false;
    try {
      TicketQueue queue = new TicketQueue(10);

      // Create a TicketSiteUser and log them out
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.buyTicket(new Ticket("name", "venue", "section", "seatNumber", 10.0));

      queue.enqueue(user1); // This line should throw an IllegalArgumentException
    } catch (IllegalArgumentException e) {
      test2 = true;
    }
    // If any other exceptions are thrown, catch them and return false
    catch (Exception e) {
      test2 = false;
    }

    return test1 && test2;
  }

  /**
   * Tests a valid implementation of enqueue from TicketQueue class
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testValidEnqueue() {
    try {
      TicketQueue queue = new TicketQueue(10);

      // Create random users
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      TicketSiteUser user2 = new TicketSiteUser("username2", "1111111111", "5555555555555555");
      user2.login("username2", "1111111111");
      TicketSiteUser user3 = new TicketSiteUser("username3", "2222222222", "6666666666666666");
      user3.login("username3", "2222222222");

      // Add all users to the queue
      queue.enqueue(user1);
      queue.enqueue(user2);
      queue.enqueue(user3);

      String expected = "username1: *\nusername2: *\nusername3: *";
      String actual = queue.toString().trim();

      // Check to make sure the queue contains the correct string
      if (expected.equals(actual)) {
        return true;
      }
    }
    // If any exceptions are thrown, catch them and return false
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Test the enqueue method from TicketQueue based on the following cases: 1) The first case tests
   * the enqueue method when the queue is full. When an additional user is added to the queue, it
   * will throw an exception 2) The second case tests the case where a user cannot buy a ticket.
   * This happens either when the user has already bought a ticket or they are not logged in. 3)
   * Test a valid implementation of enqueue
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testEnqueue() {
    boolean test1 = testQueueFull();
    boolean test2 = testCannotBuyTicket();
    boolean test3 = testValidEnqueue();

    return test1 && test2 && test3;
  }

  /**
   * Tests a valid implementation of the iterator method from TicketQueue
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testIteratorValidImplementation() {
    try {
      TicketQueue queue = new TicketQueue(10);

      // Create random users
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      TicketSiteUser user2 = new TicketSiteUser("username2", "1111111111", "5555555555555555");
      user2.login("username2", "1111111111");
      TicketSiteUser user3 = new TicketSiteUser("username3", "2222222222", "6666666666666666");
      user3.login("username3", "2222222222");

      // Adds users to the queue
      queue.enqueue(user1);
      queue.enqueue(user2);
      queue.enqueue(user3);

      TicketSiteUser[] expectedArray = new TicketSiteUser[] {user1, user2, user3};

      int numOfLoops = 0;
      Iterator<TicketSiteUser> iterator = queue.iterator();
      for (TicketSiteUser user : expectedArray) {
        // If the next value in the iterator is not what is expected, return false
        if (!iterator.hasNext() || !user.equals(iterator.next())) {
          return false;
        }
        numOfLoops++;
      }

      // The number of times the loop ran should be the length of the expected array
      if (numOfLoops != expectedArray.length) {
        return false;
      }

      // The queue size should remain unchanged
      if (queue.size() != 3) {
        return false;
      }

      String expected = "username1: *\nusername2: *\nusername3: *";
      String actual = queue.toString().trim();
      if (!expected.equals(actual)) {
        return false;
      }
    }
    // If any exceptions are thrown, catch them and return false
    catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tests the iterator when an exception is thrown
   * 
   * @return
   */
  private static boolean testIteratorThrowsException() {
    try {
      TicketQueue queue = new TicketQueue(10);
      Iterator<TicketSiteUser> iterator = queue.iterator();

      iterator.next(); // This line should throw a NoSuchElementException
    } catch (NoSuchElementException e) {
      return true;
    }
    // If any exceptions other exceptions are thrown, catch them and return false
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tests the iterator when elements are added and then removed from the queue.
   * 
   * @return
   */
  private static boolean testIteratorThrowsException2() {
    try {
      TicketQueue queue = new TicketQueue(10);
      Iterator<TicketSiteUser> iterator = queue.iterator();

      // Create random users
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      TicketSiteUser user2 = new TicketSiteUser("username2", "1111111111", "5555555555555555");
      user2.login("username2", "1111111111");
      TicketSiteUser user3 = new TicketSiteUser("username3", "2222222222", "6666666666666666");
      user3.login("username3", "2222222222");

      // Add and remove all users from the queue
      queue.enqueue(user1);
      queue.enqueue(user2);
      queue.enqueue(user3);
      queue.dequeue();
      queue.dequeue();
      queue.dequeue();

      iterator.next(); // This line should throw a NoSuchElementException
    } catch (NoSuchElementException e) {
      return true;
    }
    // If any other exceptions are thrown, catch them and return false
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tests the iterator method from TicketQueue on the following cases: 1) Check to make sure an
   * exception is thrown when the queue is empty 2) Check a valid implementation of iterator 3) Adds
   * users to the queue, removes them, and then tries to call next. The method should throw an
   * exception
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testIterator() {
    boolean test1 = testIteratorThrowsException();
    boolean test2 = testIteratorValidImplementation();
    boolean test3 = testIteratorThrowsException2();

    return test1 && test2 && test3;
  }

  /**
   * Tests peek on an empty queue. A NoSuchElementException should be thrown
   * 
   * @return true if all tests pass, false otherwise
   */
  private static boolean testPeekingAtEmptyQueue() {
    try {
      TicketQueue queue = new TicketQueue(2);

      queue.peek(); // This line should throw a NoSuchElementException
    } catch (NoSuchElementException e) {
      return true;
    }
    // If any other exceptions are thrown, catch them and return false
    catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Tests a valid implementation of peek method from TicketQueue
   * 
   * @return
   */
  private static boolean testValidPeekImplementation() {
    try {
      TicketQueue queue = new TicketQueue(10);

      // Creates a random user, adds them to the queue, and checks peek
      TicketSiteUser user1 = new TicketSiteUser("username1", "3333333333", "4444444444444444");
      user1.login("username1", "3333333333");
      queue.enqueue(user1);
      if (!queue.peek().equals(user1)) {
        return false;
      }

      // Creates a random user, adds them to the queue, and checks peek
      TicketSiteUser user2 = new TicketSiteUser("username2", "1111111111", "5555555555555555");
      user2.login("username2", "1111111111");
      queue.enqueue(user2);
      queue.dequeue();
      if (!queue.peek().equals(user2)) {
        System.out.println(queue.peek());
        return false;
      }

      // Creates a random user, adds them to the queue, and checks peek
      TicketSiteUser user3 = new TicketSiteUser("username3", "2222222222", "6666666666666666");
      user3.login("username3", "2222222222");
      queue.enqueue(user3);
      queue.dequeue();
      if (!queue.peek().equals(user3)) {
        return false;
      }
      // If peek returned the correct value each time, return true
      return true;
    }
    // If any exceptions are thrown, catch them and return false
    catch (Exception e) {
      return false;
    }
  }

  /**
   * Tests the peek method from TicketQueue on the following cases: 1) Check that peek throws a
   * NoSuchElementException when the queue is empty 2) Check that peek returns the front of the list
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean testPeek() {
    boolean test1 = testPeekingAtEmptyQueue();
    boolean test2 = testValidPeekImplementation();

    return test1 && test2;
  }

  private static boolean runAllTests() {
    // Constructor tests
    boolean test1 = false;
    System.out.print("Constructor Tests: ");
    if (testConstructor() == true) {
      System.out.println("PASSED");
      test1 = true;
    } else {
      System.out.println("FAILED");
    }

    // Dequeue tests
    boolean test2 = false;
    System.out.print("Dequeue Tests: ");
    if (testDequeue() == true) {
      System.out.println("PASSED");
      test2 = true;
    } else {
      System.out.println("FAILED");
    }

    // Enqueue tests
    boolean test3 = false;
    System.out.print("Enqueue Tests: ");
    if (testEnqueue() == true) {
      System.out.println("PASSED");
      test3 = true;
    } else {
      System.out.println("FAILED");
    }

    // Iterator tests
    boolean test4 = false;
    System.out.print("Iterator Tests: ");
    if (testIterator() == true) {
      System.out.println("PASSED");
      test4 = true;
    } else {
      System.out.println("FAILED");
    }

    // Peek tests
    boolean test5 = false;
    System.out.print("Peek Tests: ");
    if (testPeek() == true) {
      System.out.println("PASSED");
      test5 = true;
    } else {
      System.out.println("FAILED");
    }

    System.out.print("runAllTests: ");
    if (test1 && test2 && test3 && test4 && test5) {
      System.out.println("PASSED");
      return true;
    }
    System.out.println("FAILED");
    return false;
  }

  public static void main(String[] args) {
    runAllTests();
  }

}
