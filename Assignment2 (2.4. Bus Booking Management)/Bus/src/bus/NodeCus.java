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
public class NodeCus extends Customer {

    NodeCus next;

    public NodeCus(String code, String name, String phone) {
        super(code, name, phone);      
    }

     public void setNext(NodeCus next) {
        this.next = next;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
