///////////////////////////////////////////////////////////////////////////////
//
// Title: The TicketQueue class contains a list of TicketSiteUser(s). There are methods for adding
// TicketSiteUser(s) to the queue, removing them from the queue, and looking at the queue without
// changing the elements in it.
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

public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {
  private LinkedNode<TicketSiteUser> back; // The last element in the queue
  private int capacity; // The max number of elements that can be stored in the queue
  private LinkedNode<TicketSiteUser> front; // The first element in the queue
  private int size; // The current number of elements in the queue

  /**
   * Basic constructor for a queue. It is initialized with a max capacity and default values for the
   * size, front and back fields.
   * 
   * @param capacity the max capacity this queue can have
   * 
   * @throws IllegalArgumentException if the capacity is less than 1
   */
  public TicketQueue(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("INVALID CAPACITY: The capacity must be at least 1!");
    }

    this.capacity = capacity;
    size = 0;
    front = null;
    back = null;
  }

  /**
   * Getter method for size
   * 
   * @return size
   */
  public int size() {
    return size;
  }

  /**
   * Getter method for capacity
   * 
   * @return capacity
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Sets the capacity to a new capacity
   * 
   * @param newCapacity the new max capacity given to the queue
   * 
   * @throws IllegalArgumentException if the capacity is less than 1
   */
  public void setCapacity(int newCapacity) {
    if (newCapacity < 1) {
      throw new IllegalArgumentException("INVALID CAPACITY: the capacity must be at least 1!");
    }
    capacity = newCapacity;
  }

  /**
   * Checks to see if the queue is currently full
   * 
   * @return true if the queue is full, false otherwise
   */
  public boolean isFull() {
    return size >= capacity;
  }

  /**
   * Checks to see if the queue is currently empty
   * 
   * @return true if the queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0 && front == null && back == null;
  }

  /**
   * Adds a new element to the queue
   * 
   * @throws IllegalStateException    if the queue is already full
   * @throws IllegalArgumentException if the user has already bought a ticket or is logged out
   */
  public void enqueue(TicketSiteUser newObject) {
    if (isFull()) {
      throw new IllegalStateException("The queue is currently full!");
    }
    if (!newObject.canBuyTicket()) {
      throw new IllegalArgumentException("This user is not able to buy a ticket!");
    }

    // If the list is empty, the new object should be the front and the back. Increment size as well
    if (isEmpty()) {
      front = new LinkedNode<TicketSiteUser>(newObject, null);
      back = front;
      size++;
      return;
    }

    // If there is currently only one element in the list, update the queue accordingly
    if (front.equals(back)) {
      LinkedNode<TicketSiteUser> newBack = new LinkedNode<TicketSiteUser>(newObject, null);
      front.setNext(newBack);
      back = newBack;
      size++;
    }
    // If there are multiple elements in the queue, update the list as normal
    else {
      LinkedNode<TicketSiteUser> newBack = new LinkedNode<TicketSiteUser>(newObject, null);
      back.setNext(newBack);
      back = newBack;
      size++;
    }
  }

  /**
   * Returns and removes the front node in the queue
   * 
   * @return the front node in the queue
   * 
   * @throws NoSuchElementException if the queue is currently empty
   */
  public TicketSiteUser dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is currently empty!");
    }

    // If there is only one element left in the queue, reset all fields to their default values
    if (front.equals(back)) {
      TicketSiteUser returnedNode = front.getData();
      front = null;
      back = null;
      size = 0;
      return returnedNode;
    }

    // If the queue has more than one element, the removedNode will be the front
    TicketSiteUser returnedNode = front.getData();
    front = front.getNext();
    size--;
    return returnedNode;
  }

  /**
   * Returns the first element in the list without removing it
   * 
   * @throws NoSuchElementException if there are no elements in the queue
   */
  public TicketSiteUser peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is currently empty!");
    }

    return front.getData();
  }

  /**
   * Creates and returns a deep copy of the current queue
   * 
   * @return a deep copy of the this queue
   */
  public TicketQueue deepCopy() {
    TicketQueue deepCopy = new TicketQueue(capacity);

    // If the queue is empty, return an empty TicketQueue
    if (this.isEmpty()) {
      return deepCopy;
    }

    LinkedNode<TicketSiteUser> currentNode = this.front;
    while (currentNode != null) { // Loops through the queue and adds the elements to the deep copy
      deepCopy.enqueue(currentNode.getData());
      currentNode = currentNode.getNext();
    }

    return deepCopy;
  }

  /**
   * Creates an iterator for this queue
   * 
   * @return the iterator for this queue
   */
  public Iterator<TicketSiteUser> iterator() {
    TicketQueueIterator iterator = new TicketQueueIterator(this);

    return iterator;
  }

  @Override
  /**
   * Returns a string version of this TicketQueue. It is of the form: "username: " + ticket + \n
   * 
   * @return a string version of this object
   */
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() + "\n";
      runner = runner.getNext();
    }
    return s;
  }
}
