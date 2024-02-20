/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * @author xeban
 */
public class BSTBus {

    NodeBus root;

    public BSTBus() {
        root = null;
    }

    void add(Bus tr) {
        root = addrec(root, tr);
    }

    void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    NodeBus addrec(NodeBus root, Bus b) {

        if (root == null) {
            return new NodeBus(b);
        }
        if (b.getCode().compareTo(root.b.getCode()) < 0) {
            root.left = addrec(root.left, b);
        } else if (compareTo(b.getCode(), root.b.getCode()) > 0) {
            root.right = addrec(root.right, b);
        } else {
//            System.out.println(Main.ANSI_RED + "Duplicate bus code. Please enter a different bus code." + Main.ANSI_RESET);
        }
        return root;
    }

    boolean deleteNode(String x) {
        if (searchRec(root, x) != null) {
            root = remove(root, x);
            return true;
        } else {
            return false;
        }

    }

    NodeBus remove(NodeBus root, String x) {

        if (root == null) {
            return root;
        }

        if (x.compareToIgnoreCase(root.b.getCode()) < 0) {
            root.left = remove(root.left, x);
        } else if (x.compareToIgnoreCase(root.b.getCode()) > 0) {
            root.right = remove(root.right, x);
        } else {
            // Node with key x found, delete it
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node has two children
            // Replace with inorder successor (min of right subtree)
            NodeBus minRight = findMinNode(root.right);
            root.b = minRight.b;
            root.right = remove(root.right, minRight.b.getCode());
        }

        return root;
    }

    //locate and return node with min value
    NodeBus findMinNode(NodeBus node) {
        NodeBus current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    boolean search(String x) {
        NodeBus p = searchRec(root, x);
        if (p != null) {
            return true;
        } else {
            return false;
        }
    }

    boolean search2(String x) {
        NodeBus p = searchRec3(root, x);
        if (p != null) {
            p.b.display();
            return true;
        } else {
            return false;
        }
    }

    boolean searchName(String name) {
        return searchNameRec(root, name);
    }

    boolean searchNameRec(NodeBus curr, String name) {
        if (curr == null) {
            return false;
        } else {
            if (curr.b.getName().equalsIgnoreCase(name)) {
                return true;
            }
            boolean leftResult = searchNameRec(curr.left, name);
            boolean rightResult = searchNameRec(curr.right, name);
            return leftResult || rightResult;
        }
    }

    NodeBus searchRec(NodeBus curr, String x) {

        if (curr == null) {
            return null;
        } else {
            if (curr.b.getCode().equalsIgnoreCase(x)) {
                return curr;
            }
            NodeBus leftResult = searchRec(curr.left, x);
            NodeBus rightResult = searchRec(curr.right, x);
            if (leftResult != null || rightResult != null || curr.b.getCode().equalsIgnoreCase(x) || curr.b.getCode().toLowerCase().contains(x)) {
                return curr;
            }
            return null;
        }
    }

    NodeBus searchRec3(NodeBus curr, String x) {
        if (curr == null) {
            return null;
        } else {
            if (curr.b.getCode().toLowerCase().contains(x.toLowerCase())) {
                curr.b.display();
            }
            NodeBus leftResult = searchRec3(curr.left, x);
            NodeBus rightResult = searchRec3(curr.right, x);
            if (leftResult != null || rightResult != null || curr.b.getCode().toLowerCase().contains(x.toLowerCase())) {
                return curr;
            }
            return null;
        }
    }

    NodeBus searchRec2(NodeBus curr, String x) {
        if (curr == null) {
            return null;
        } else if (curr.b.getCode().equalsIgnoreCase(x)) {
            return curr;
        } else if (x.compareToIgnoreCase(curr.b.getCode()) < 0) {
            return searchRec2(curr.left, x);
        } else {
            return searchRec2(curr.right, x);
        }
    }

    public void preOrder(NodeBus root) {
        if (root != null) {
            root.b.display();
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(NodeBus root) {
        if (root != null) {
            inOrder(root.left);
            root.b.display();
            inOrder(root.right);
        }
    }

    public void postOrder(NodeBus root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            root.b.display();
        }
    }

    int count(NodeBus root) {
        if (root == null) {
            return 0;
        } else {
            int leftCount = count(root.left);
            int rightCount = count(root.right);
            return leftCount + rightCount + 1;
        }
    }

    void printBreadthFirst() {
        if (root == null) {
            return;
        }

        Queue<NodeBus> queue = new LinkedList<>();
        LinkedList<NodeBus> list = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            NodeBus current = queue.poll();
            current.b.display();
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    int compareTo(String x, String y) {
        return x.compareToIgnoreCase(y);
    }

    public void deleByCopy(String bcode) {
        NodeBus parent = null;
        NodeBus current = root;
        while (current != null && !current.b.getCode().equalsIgnoreCase(bcode)) {
            parent = current;
            if (compareTo(bcode, current.b.getCode()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            System.out.println(Main.ANSI_RED + "Bcode not found." + Main.ANSI_RESET);
            return;
        } else {
            System.out.println(Main.ANSI_GREEN + "Deletion completed!" + Main.ANSI_RESET);
        }

        // Case 1: Node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } // Case 2: Node to be deleted has only one child
        else if (current.left == null || current.right == null) {
            NodeBus child = (current.left != null) ? current.left : current.right;
            if (current == root) {
                root = child;
            } else if (current == parent.left) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } // Case 3: Node to be deleted has two children
        else {
            NodeBus successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (current == parent.left) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
    }

// Helper method to find the successor
    NodeBus getSuccessor(NodeBus deleteNode) {
        NodeBus successor = null;
        NodeBus successorParent = null;
        NodeBus current = deleteNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        return successor;
    }
}
