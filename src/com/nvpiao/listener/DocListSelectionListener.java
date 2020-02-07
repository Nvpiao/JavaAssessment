package com.nvpiao.listener;

import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.frame.DocViewerFrame;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * com.nvpiao.listener.DocListSelectionListener.java
 * <p>
 * A simple class of for com.nvpiao.listener.DocListSelectionListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   24 December 2019
 */
public class DocListSelectionListener implements ListSelectionListener {

    /**
     * parent frame
     */
    private final DocViewerFrame docViewerFrame;

    /**
     * doc list from parent frame
     */
    private JList<String> docList;

    public DocListSelectionListener(DocViewerFrame docViewerFrame) {
        this.docViewerFrame = docViewerFrame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // if null
        if (null == docList) {
            docList = docViewerFrame.getDocList();
        }
        String selectedValue = docList.getSelectedValue();
        if (null != selectedValue) {
            // remove suffix
            String eleWithoutSuffix = selectedValue;
            int suffixIndex = selectedValue.indexOf(DocumentConstants.DOC_LOADED_SUFFIX);
            if (-1 != suffixIndex) {
                eleWithoutSuffix = selectedValue.substring(0, suffixIndex);
            }
            // set value
            docViewerFrame.getSelectedFiled().setText(eleWithoutSuffix);
            docViewerFrame.getSearchField().setText(eleWithoutSuffix);
        }
    }
}
