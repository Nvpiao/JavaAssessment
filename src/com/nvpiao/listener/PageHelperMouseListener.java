package com.nvpiao.listener;

import com.nvpiao.constants.DocViewerConstants;
import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.frame.DocViewerFrame;

import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * com.nvpiao.listener.PageHelperMouseListener.java
 * <p>
 * A simple class of for com.nvpiao.listener.PageHelperMouseListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   26 December 2019
 */
public class PageHelperMouseListener implements MouseListener {
    /**
     * parent frame
     */
    private final DocViewerFrame docViewerFrame;

    public PageHelperMouseListener(DocViewerFrame docViewerFrame) {
        this.docViewerFrame = docViewerFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        BaseDocument currentDoc = docViewerFrame.getCurrentDoc();
        // return if no document display.
        if (null == currentDoc) {
            return;
        }

        JLabel pageLabel = (JLabel) e.getSource();
        String pageLabelText = pageLabel.getText();
        switch (pageLabelText) {
            case DocViewerConstants.HOME_PAGE_LABEL:
                goToPage(currentDoc, DocumentConstants.HOME_PAGE_NUM_FLAG);
                break;
            case DocViewerConstants.PREV_PAGE_LABEL:
                goToPage(currentDoc, DocumentConstants.PREV_PAGE_NUM_FLAG);
                break;
            case DocViewerConstants.NEXT_PAGE_LABEL:
                goToPage(currentDoc, DocumentConstants.NEXT_PAGE_NUM_FLAG);
                break;
            case DocViewerConstants.END_OF_DOC_LABEL:
                goToPage(currentDoc, DocumentConstants.END_PAGE_NUM_FLAG);
                break;
            default:
        }
    }

    /**
     * go to specific page
     *
     * @param currentDoc  current document
     * @param pageNumFlag page num flag
     */
    private void goToPage(BaseDocument currentDoc, int pageNumFlag) {
        int currentPage = currentDoc.getCurrentPages();

        if (pageNumFlag == DocumentConstants.HOME_PAGE_NUM_FLAG) {
            if (DocumentConstants.HOME_PAGE_NUM == currentPage) {
                // has already been at home page
                return;
            }
            currentPage = DocumentConstants.HOME_PAGE_NUM;
        } else if (pageNumFlag == DocumentConstants.PREV_PAGE_NUM_FLAG) {
            if (1 == currentPage) {
                // has already been at home page
                return;
            }
            --currentPage;
        } else if (pageNumFlag == DocumentConstants.NEXT_PAGE_NUM_FLAG) {
            if (currentDoc.getTotalPage() == currentPage) {
                // has already been at last page
                return;
            }
            ++currentPage;
        } else if (pageNumFlag == DocumentConstants.END_PAGE_NUM_FLAG) {
            if (currentDoc.getTotalPage() == currentPage) {
                // has already been at last page
                return;
            }
            currentPage = currentDoc.getTotalPage();
        }
        // reset current page
        currentDoc.setCurrentPages(currentPage);

        // set current page
        docViewerFrame.getPageLabel().setText(String.valueOf(currentDoc.getCurrentPages()));
        // set selected chapter
        synchronized (this) {
            docViewerFrame.getChapterSelectionListener().setMouseChangeFalse();
            docViewerFrame.getChapterList().setSelectedIndex(currentDoc.getCurrentPages() - 1);
            docViewerFrame.getChapterSelectionListener().setMouseChangeTrue();
        }
        // set Viewer Text
        docViewerFrame.getViewerArea().setText(currentDoc.getDocPages().get(currentDoc.getCurrentPages() - 1));
        // set scroll to zero
        docViewerFrame.getViewerArea().setCaretPosition(0);
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
