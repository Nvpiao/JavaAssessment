package com.nvpiao.listener;

import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.frame.DocViewerFrame;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.regex.Pattern;

/**
 * com.nvpiao.listener.LoadNameChangeListener.java
 * <p>
 * A simple class of for com.nvpiao.listener.LoadNameChangeListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   24 December 2019
 */
public class LoadNameChangeListener implements DocumentListener {

    /**
     * parent frame
     */
    private final DocViewerFrame docViewerFrame;

    /**
     * jList from parent frame
     */
    private JList<String> docList;

    /**
     * initial model from docList
     */
    private ListModel<String> initModel;

    public LoadNameChangeListener(DocViewerFrame docViewerFrame) {
        this.docViewerFrame = docViewerFrame;
    }

    public ListModel<String> getInitModel() {
        return initModel;
    }

    public void setInitModel(ListModel<String> initModel) {
        this.initModel = initModel;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changeWords(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changeWords(e);
    }

    /**
     * notify the Jlist to change
     *
     * @param e document event
     */
    private void changeWords(DocumentEvent e) {
        try {
            Document document = e.getDocument();
            String text = document.getText(0, document.getLength()).trim();
            String textRegex = DocumentConstants.SEARCH_REGEX_PREFIX_SUFFIX + text + ".*";
            Pattern pattern = Pattern.compile(textRegex, Pattern.CASE_INSENSITIVE);

            // get docList Object
            if (null == this.docList) {
                this.docList = docViewerFrame.getDocList();
                this.initModel = this.docList.getModel();
            }

            // new model
            DefaultListModel<String> filterModel = new DefaultListModel<>();
            //filter model
            for (int i = 0; i < initModel.getSize(); i++) {
                String ele = initModel.getElementAt(i);
                // remove suffix
                String eleWithoutSuffix = ele;
                int suffixIndex = ele.indexOf(DocumentConstants.DOC_LOADED_SUFFIX);
                if (-1 != suffixIndex) {
                    eleWithoutSuffix = ele.substring(0, suffixIndex);
                }
                // matcher
                if (pattern.matcher(eleWithoutSuffix).find()) {
                    filterModel.addElement(ele);
                }
            }
            docList.setModel(filterModel);

        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Do Noting
    }
}
