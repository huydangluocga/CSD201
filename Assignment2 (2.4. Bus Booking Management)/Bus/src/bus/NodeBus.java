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
public class NodeBus {

    Bus b;

    NodeBus left, right;

    NodeBus(Bus b) {
        this.b = b;
        left = right = null;
    }
}
