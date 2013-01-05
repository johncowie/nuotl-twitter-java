/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.domain;

/**
 *
 * @author john
 */
public enum YesNoIndicator {
    Y,
    N;
    
    public boolean isEnabled() {
        switch(this) {
            case Y:
                return true;
            case N:
            default:
                return false;
        }
    }
}
