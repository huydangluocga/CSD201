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
public class NodeBook {

    String bcode;
    String ccode;
    int book;
    NodeBook next;

    public NodeBook(String bcode, String ccode, int book) {
        this.bcode = bcode;
        this.ccode = ccode;
        this.book = book;
        this.next = null;
    }

}
