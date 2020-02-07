package com.nvpiao.listener;

import com.nvpiao.constants.DocViewerConstants;
import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.frame.DocViewerFrame;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Map;

/**
 * com.nvpiao.listener.StatisticActionListener.java
 * <p>
 * A simple class of for com.nvpiao.listener.StatisticActionListener
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   26 December 2019
 */
public class StatisticActionListener implements ActionListener {

    /**
     * parent frame
     */
    private final DocViewerFrame docViewerFrame;

    public StatisticActionListener(DocViewerFrame docViewerFrame) {
        this.docViewerFrame = docViewerFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case DocViewerConstants.ABOUT:
                showAboutDialog();
                break;
            case DocViewerConstants.SHOW_STATUS:
                showCurrentDocStatus();
                break;
            case DocViewerConstants.TOP_TEN:
                showTopTenWords();
                break;
            default:
        }
    }

    /**
     * show top 10 frequency words
     */
    private void showTopTenWords() {
        BaseDocument currentDoc = docViewerFrame.getCurrentDoc();
        if (null == currentDoc) {
            return;
        }
        // top10 words-frequency pairs
        Map<String, Integer> topTenWords = currentDoc.getTopTenWords();
        StringBuilder stringBuilder = new StringBuilder();

        // sort by value and get a text
        topTenWords.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(DocumentConstants.TOP_TEN_LIMIT)
                .forEach(entity -> stringBuilder.append(DocumentConstants.TOP_TEN_WORDS)
                        .append(entity.getKey()).append(DocumentConstants.TOP_TEN_FREQUENCY)
                        .append(entity.getValue()).append(DocumentConstants.FILE_RETURN));

        // show top10 words-frequency pairs
        JOptionPane.showMessageDialog(docViewerFrame, stringBuilder.toString(),
                DocumentConstants.TOP_TEN, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show the status of current doc
     */
    private void showCurrentDocStatus() {
        BaseDocument currentDoc = docViewerFrame.getCurrentDoc();
        if (null == currentDoc) {
            return;
        }

        JOptionPane.showMessageDialog(docViewerFrame, currentDoc.toString(),
                DocumentConstants.STATUS, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * show about dialog
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(docViewerFrame, DocumentConstants.ABOUT_INFO,
                DocumentConstants.ABOUT, JOptionPane.INFORMATION_MESSAGE);
    }
}
