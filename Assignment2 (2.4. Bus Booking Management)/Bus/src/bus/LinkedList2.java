/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

/**
 *
 * @author xeban
 */
public class LinkedList2 {

    NodeBook head;
    NodeBook tail;
    int size;
    int check = 0;

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void addToTail(String bcode, String ccode, int book) {
        NodeBook p = new NodeBook(bcode, ccode, book);
        if (isEmpty()) {
            head = p;
            tail = head;
        } else {
            NodeBook current = head;
            while (current != null) {
                if (current.bcode.equals(bcode) && current.ccode.equals(ccode)) {
                    current.book += book; // Cập nhật giá trị seat_booked
                    return;
                }
                current = current.next;
            }
            tail.next = p;
            tail = p;
        }
    }

    void printList(LinkedList2 list) {
        NodeBook currNode = list.head;
        System.out.printf("| %-10s | %-10s | %-11s | \n", "b_code", "c_code", "seat_booked");
        while (currNode != null) {
            System.out.printf("| %-10s | %-10s | %-11d | \n", currNode.bcode, currNode.ccode, currNode.book);
            currNode = currNode.next;
        }
    }

    NodeBook divideList(NodeBook a, NodeBook b, String option) {
        NodeBook r = null;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        Booking bookA = new Booking(a.bcode, a.ccode, a.book);
        Booking bookB = new Booking(b.bcode, b.ccode, b.book);
        if (option.equals("bcode")) {
            if (bookA.getBcode().compareTo(bookB.getBcode()) < 0) {
                r = a;
                r.next = divideList(a.next, b, option);
            } else {
                r = b;
                r.next = divideList(a, b.next, option);
            }
        } else if (option.equals("ccode")) {
            if (bookA.getCcode().compareTo(bookB.getCcode()) < 0) {
                r = a;
                r.next = divideList(a.next, b, option);
            } else {
                r = b;
                r.next = divideList(a, b.next, option);
            }
        }
        return r;
    }

    NodeBook mergeSort(NodeBook head, String option) {
        // Base case: if head is null or there is only one element in the list
        if (head == null || head.next == null) {
            return head;
        }
        // Get the middle of the list
        NodeBook mid = getMiddle(head);
        NodeBook nextMid = mid.next;
        // Set the next of the middle node to null
        mid.next = null;

        // Apply mergeSort on the left list
        NodeBook left = mergeSort(head, option);

        // Apply mergeSort on the right list
        NodeBook right = mergeSort(nextMid, option);

        // Merge the left and right lists
        NodeBook sortedList = divideList(left, right, option);
        return sortedList;
    }

    NodeBook getMiddle(NodeBook head) {
        NodeBook mid = null;
        NodeBook after = null;
        mid = after = head;
        while (after.next != null && after.next.next != null) {
            mid = mid.next;
            after = after.next.next;
        }
        return mid;
    }

    NodeBook NodeSearch(String x_bcode, String x_ccode, LinkedList2 l) {

        check = 0;
        NodeBook curr = l.head;

        while (curr != null) {
            if (x_ccode.equals(curr.ccode) && x_bcode.equals(curr.bcode)) {
                check++;
                break;
            } else {
                curr = curr.next;
            }
        }
        return curr;
    }

    boolean searchByCcode(String ccode) {
        NodeBook current = head;
        while (current != null) {
            if (current.ccode.equals(ccode)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    int getBookedSeatsByCcode(String ccode) {
        NodeBook current = head;
        while (current != null) {
            if (current.ccode.equals(ccode)) {
                return current.book;
            }
            current = current.next;
        }
        return 0;
    }

    boolean search() {
        return check > 0;
    }

}
