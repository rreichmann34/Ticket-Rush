///////////////////////////////////////////////////////////////////////////////
//
// Title: The TicketQueueIterator class is a way for the program to iterate through a queue
// without changing the contents of the queue.
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

public class TicketQueueIterator implements Iterator<TicketSiteUser> {
  private TicketQueue userQueue; // Contains a deep copy of the queue that is passed to the
                                 // constructor

  /**
   * Basic constructor that creates a deep copy of the inputted queue
   * 
   * @param queue the queue to copy and later iterate through
   * 
   * @throws IllegalArgumentException if the inputted queue is null
   */
  public TicketQueueIterator(TicketQueue queue) {
    if (queue == null) {
      throw new IllegalArgumentException("The inputted queue is null!");
    }
    userQueue = queue.deepCopy();
  }

  /**
   * Checks to see if there are more nodes in the queue
   * 
   * @return true if there are more nodes in the queue, false otherwise
   */
  public boolean hasNext() {
    if (userQueue.size() > 0) {
      return true;
    }
    return false;
  }

  /**
   * This method returns the next node in the queue and dequeues it from the deep copy
   * 
   * @return the next node in the queue
   * 
   * @throws NoSuchElementException if the deep copy is empty
   */
  public TicketSiteUser next() {
    if (userQueue.isEmpty()) {
      throw new NoSuchElementException("There are no users currently in the queue!");
    }
    return userQueue.dequeue();
  }
}
