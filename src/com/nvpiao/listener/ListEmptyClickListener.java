package com.nvpiao.listener;

import javax.swing.JList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * com.nvpiao.listener.ListEmptyClickListener.java
 * <p>
 * A simple class of for com.nvpiao.listener.ListEmptyClickListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   28 December 2019
 */
public class ListEmptyClickListener implements MouseListener {

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        JList<String> list = (JList<String>) e.getSource();
        if (list.locationToIndex(e.getPoint()) == -1) {
            list.clearSelection();
        }
    }

    /************************************
     *      below code are ignored.      *
     ************************************/
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
