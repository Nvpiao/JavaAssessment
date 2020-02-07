package com.nvpiao.listener;

import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.frame.DocViewerFrame;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * com.nvpiao.listener.ChapterSelectionListener.java
 * <p>
 * A simple class for com.nvpiao.listener.ChapterSelectionListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   26 December 2019
 */
public class ChapterSelectionListener implements ListSelectionListener {

    /**
     * parent frame
     */
    private final DocViewerFrame docViewerFrame;

    /**
     * chapter list from parent frame
     */
    private JList<String> chapterList;

    /**
     * if changes cause by mouse
     */
    private boolean mouseChange;

    public ChapterSelectionListener(DocViewerFrame docViewerFrame) {
        this.docViewerFrame = docViewerFrame;
    }

    public void setMouseChangeTrue() {
        this.mouseChange = Boolean.TRUE;
    }

    public void setMouseChangeFalse() {
        this.mouseChange = Boolean.FALSE;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (null == chapterList) {
            chapterList = docViewerFrame.getChapterList();
        }

        String selectedValue = chapterList.getSelectedValue();
        if (null != selectedValue && mouseChange) {
            // must not null
            BaseDocument currentDoc = docViewerFrame.getCurrentDoc();

            Integer currentPage = currentDoc.getChapterToPage().get(selectedValue);
            currentDoc.setCurrentPages(currentPage);

            // set current page
            docViewerFrame.getPageLabel().setText(String.valueOf(currentDoc.getCurrentPages()));
            // set Viewer Text
            docViewerFrame.getViewerArea().setText(currentDoc.getDocPages().get(currentDoc.getCurrentPages() - 1));
            // set scroll to zero
            docViewerFrame.getViewerArea().setCaretPosition(0);
        }
    }
}
