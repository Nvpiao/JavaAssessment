package com.nvpiao.listener;

import com.nvpiao.constants.DocViewerConstants;
import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.frame.DocViewerFrame;
import com.nvpiao.listener.worker.LoadDocWorker;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * com.nvpiao.listener.LoadAndDisActionListener.java
 * <p>
 * A simple class of for com.nvpiao.listener.LoadAndDisActionListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   24 December 2019
 */
public class LoadAndDisActionListener implements ActionListener, PropertyChangeListener {
    /**
     * parent frame
     */
    private final DocViewerFrame docViewerFrame;

    /**
     * progress monitor
     */
    private ProgressMonitor progressMonitor;

    /**
     * task swing worker
     */
    private LoadDocWorker loadDocWorker;

    /**
     * load file name
     */
    private String searchText;

    public LoadAndDisActionListener(DocViewerFrame docViewerFrame) {
        this.docViewerFrame = docViewerFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(DocViewerConstants.LOAD_BUTTON)) {
            loadButtonAction();
        } else if (command.equals(DocViewerConstants.DISPLAY_TEXT)) {
            disButtonAction();
        }
    }

    /**
     * load doc file
     */
    private void loadButtonAction() {
        List<BaseDocument> documents = docViewerFrame.getDocuments();
        Vector<String> docFileList = docViewerFrame.getDocFileList();
        JTextField searchField = docViewerFrame.getSearchField();
        searchText = searchField.getText().trim();

        if (docFileList.contains(searchText)) {
            // async load document with progress bar
            loadDocument(documents, searchText);
        } else {
            if (!searchText.isEmpty()) {
                // warning
                String errMsg = "Document: [" + searchText + "] does not exist.";
                JOptionPane.showMessageDialog(docViewerFrame, errMsg,
                        DocumentConstants.ERROR, JOptionPane.ERROR_MESSAGE);
                System.out.println(errMsg);
            }
            // reset selected text
            docViewerFrame.getSelectedFiled().setText(DocViewerConstants.NO_SELECTED_DOC);
        }
    }

    /**
     * async loading document
     *
     * @param documents documents list
     * @param docName   document name
     */
    private void loadDocument(List<BaseDocument> documents, String docName) {
        BaseDocument foundDoc = checkLoadDocument(documents, docName);
        // return direct
        if (null != foundDoc) {
            String warnMsg = "Document: [" + searchText + "] has been loaded.";
            System.out.println(warnMsg);
            JOptionPane.showMessageDialog(docViewerFrame, warnMsg,
                    DocumentConstants.WARNINGS, JOptionPane.WARNING_MESSAGE);
            // set search text file to empty
            docViewerFrame.getSearchField().setText("");
            return;
        }

        // progress monitor
        progressMonitor = new ProgressMonitor(docViewerFrame,
                DocumentConstants.PROGRESS_LOADING_MSG_INFO,
                DocumentConstants.PROGRESS_LOADING_NOTE_INFO,
                DocumentConstants.INITIAL_PROGRESS_ZERO,
                DocumentConstants.MAXIMUM_PROGRESS_ONE_HUNDRED);
        loadDocWorker = new LoadDocWorker(docViewerFrame, documents, docName);
        loadDocWorker.addPropertyChangeListener(this);
        loadDocWorker.execute();
    }

    /**
     * check if document has already been loaded.
     *
     * @param documents document list
     * @param docName   document name
     * @return return found document object or null
     */
    private BaseDocument checkLoadDocument(List<BaseDocument> documents, String docName) {
        // find if loaded
        return documents.stream()
                .filter(doc -> docName.equals(doc.getFileName()))
                .findAny()
                .orElse(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (DocumentConstants.PROPERTY_CHANGE_PROGRESS.equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            progressMonitor.setProgress(progress);
            if (progressMonitor.isCanceled()) {
                loadDocWorker.cancel(Boolean.TRUE);
                System.out.println("Document: [" + searchText + "] has been canceled.");
            }
        }
    }

    /**
     * display text
     * set displayed name
     * set num of words
     * set pages
     * set chapters
     */
    private void disButtonAction() {
        List<BaseDocument> documents = docViewerFrame.getDocuments();
        JTextField selectedFiled = docViewerFrame.getSelectedFiled();
        searchText = selectedFiled.getText().trim();
        BaseDocument foundDoc = checkLoadDocument(documents, searchText);

        // check document
        if (checkDocumentLoaded(foundDoc)) {
            return;
        }

        // set current document to found document
        docViewerFrame.setCurrentDoc(foundDoc);
        // display document and other information.
        displayDocument(foundDoc);

    }

    /**
     * display found document and other information
     *
     * @param foundDoc found document
     */
    private void displayDocument(BaseDocument foundDoc) {
        // set displayed name
        docViewerFrame.getDisNameLabel().setText(foundDoc.getTitle());

        // set num of words
        docViewerFrame.getWordsNumLabel().setText(String.valueOf(foundDoc.getTotalWords()));

        // set pages
        docViewerFrame.getPagesNumLabel().setText(String.valueOf(foundDoc.getTotalPage()));

        // set current page
        docViewerFrame.getPageLabel().setText(String.valueOf(foundDoc.getCurrentPages()));

        // set chapters
        DefaultListModel<String> chapterModel = new DefaultListModel<>();
        foundDoc.getChapterToPage().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .forEach(chapterModel::addElement);
        docViewerFrame.getChapterList().setModel(chapterModel);
        // set selected chapter
        synchronized (this) {
            docViewerFrame.getChapterSelectionListener().setMouseChangeFalse();
            docViewerFrame.getChapterList().setSelectedIndex(foundDoc.getCurrentPages() - 1);
            docViewerFrame.getChapterSelectionListener().setMouseChangeTrue();
        }
        // set Viewer Text
        docViewerFrame.getViewerArea().setText(foundDoc.getDocPages().get(foundDoc.getCurrentPages() - 1));
        // set scroll to zero
        docViewerFrame.getViewerArea().setCaretPosition(0);
    }

    /**
     * check if document has been loaded or exsist
     *
     * @param foundDoc found docuemnt
     * @return return {@code true} if need return, otherwise {@code false}
     */
    private boolean checkDocumentLoaded(BaseDocument foundDoc) {
        if (searchText.equals(DocViewerConstants.NO_SELECTED_DOC)) {
            return Boolean.TRUE;
        }

        // return direct if it is not loaded
        if (null == foundDoc) {
            String warnMsg = "Document: [" + searchText + "] has not been loaded.";
            System.out.println(warnMsg);
            JOptionPane.showMessageDialog(docViewerFrame, warnMsg,
                    DocumentConstants.WARNINGS, JOptionPane.WARNING_MESSAGE);
            return Boolean.TRUE;
        }

        BaseDocument currentDoc = docViewerFrame.getCurrentDoc();
        // the same with current display document
        return null != currentDoc
                && currentDoc.getFileName()
                .equals(foundDoc.getFileName());
    }
}
