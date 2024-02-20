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
public class LinkedList {

    NodeCus head;
    NodeCus tail;
    int size;
    int check = 0;

    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void addToTail(String ccode, String cusName, String phone) {
        NodeCus p = new NodeCus(ccode, cusName, phone);
        if (isEmpty()) {
            head = p;
            tail = head; // Cập nhật tail khi danh sách ban đầu trống
        } else {
            tail.next = p;
            tail = p; // Cập nhật tail sau khi thêm nút mới
        }
    }

    NodeCus NodeSearchName(String name) {
        NodeCus current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    void printList(LinkedList list) {
        NodeCus currentNode = list.head;
        if (currentNode == null) {
            System.out.println(Main.ANSI_RED + "No data to display" + Main.ANSI_RESET);
            return;
        }
        System.out.println("-----------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-12s | \n", "ccode", "Customer_Name", "Phone");
        while (currentNode != null) {
            System.out.printf("| %-10s | %-15s | %-12s | \n", currentNode.code, currentNode.name, currentNode.phone);
            currentNode = currentNode.next;
        }
        System.out.println("-----------------------------------------------");
    }

    NodeCus NodeSearch(String xcode, LinkedList l) {

        check = 0;
        NodeCus current = l.head;
        NodeCus foundNode = null;

        while (current != null) {
            if (current.code != null && current.code.toLowerCase().contains(xcode.toLowerCase())) {
                check++;
                foundNode = current;
                break;
            }
            current = current.next;
        }
        return foundNode;
    }

    NodeCus findNodeByPhone(String phone) {
        NodeCus current = head;
        while (current != null) {
            if (current.phone.equals(phone)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}
