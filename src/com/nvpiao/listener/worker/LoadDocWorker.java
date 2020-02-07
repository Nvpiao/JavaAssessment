package com.nvpiao.listener.worker;

import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.entity.base.BaseDocument;
import com.nvpiao.entity.object.Novel;
import com.nvpiao.entity.object.Play;
import com.nvpiao.entity.object.Poem;
import com.nvpiao.frame.DocViewerFrame;
import com.nvpiao.utils.FileUtils;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * com.nvpiao.listener.worker.LoadDocWorker.java
 * <p>
 * A simple class of for com.nvpiao.listener.worker.LoadDocWorker
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   25 December 2019
 */
public class LoadDocWorker extends SwingWorker<Void, Integer> {
    /**
     * parent frame
     */
    private DocViewerFrame docViewerFrame;

    /**
     * document list
     */
    private List<BaseDocument> documents;

    /**
     * name of document
     */
    private String docName;

    public LoadDocWorker(DocViewerFrame docViewerFrame, List<BaseDocument> documents,
                         String docName) {
        this.docViewerFrame = docViewerFrame;
        this.documents = documents;
        this.docName = docName;
    }

    @Override
    protected Void doInBackground() throws Exception {
        // set initial progress to 0
        setProgress(DocumentConstants.INITIAL_PROGRESS_ZERO);

        // read document
        FileUtils.openFile(docName);
        BaseDocument doc = getDocument();
        assert doc != null;
        doc.setFileName(docName);

        int lineNum = 1;
        while (!FileUtils.eof()) {
            String line = FileUtils.readString().trim();
            // empty line
            if (DocumentConstants.EMPTY_LINE_LEN == line.length()) {
                continue;
            }

            // text:
            if (DocumentConstants.TEXT_START.equals(line)) {
                handleText(doc, lineNum);
                break;
            }

            // handle header
            if (lineNum < 8 && line.contains(DocumentConstants.FILE_HEADER_SEPARATOR)) {
                handleHeader(doc, line);
            }
            // set progress
            setProgress(++lineNum);
        }
        // add to list of doc
        documents.add(doc);
        return null;
    }

    /**
     * handle text of doc
     *
     * @param doc      document
     * @param progress porgress
     */
    private void handleText(BaseDocument doc, int progress) {
        if (doc instanceof Poem) {
            // poem
            handlePoem(doc, progress);
        } else if (doc instanceof Play) {
            // play
            handlePlay(doc, progress);
        } else if (doc instanceof Novel) {
            // novel
            handleNovel(doc, progress);
        }
    }

    /**
     * handel document of novel
     *
     * @param doc      document
     * @param progress progress
     */
    private void handleNovel(BaseDocument doc, int progress) {
        //cast
        Novel novel = (Novel) doc;
        List<String> novelList = new ArrayList<>();
        Map<String, Integer> chapterToPage = new HashMap<>();
        Pattern chapterRegex = Pattern.compile(DocumentConstants.NOVEL_CHAPTERS_REGEX);
        boolean firstLine = Boolean.TRUE;
        int pageNum = 0;

        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = FileUtils.readString();
            if (FileUtils.eof()) {
                break;
            }

            // new chapter
            boolean ifChapter = chapterRegex.matcher(line).find();
            if (ifChapter) {
                // add to list
                if (firstLine) {
                    firstLine = Boolean.FALSE;
                } else {
                    novelList.add(stringBuilder.toString());
                }

                // add chapters
                chapterToPage.put(line, ++pageNum);

                // clean string builder
                stringBuilder.delete(0, stringBuilder.length());

                // set progress
                ++progress;
                setProgress(Math.min(progress, DocumentConstants.TEMP_MAXIMUM_PROGRESS_NINETY));
            }
            stringBuilder.append(line);
        }
        // add the last
        novelList.add(String.valueOf(stringBuilder));
        novel.setDocPages(novelList);
        novel.setChapterToPage(chapterToPage);
        // set 100 progress
        setProgress(DocumentConstants.MAXIMUM_PROGRESS_ONE_HUNDRED);
    }

    /**
     * handel document of play
     *
     * @param doc      document
     * @param progress progress
     */
    private void handlePlay(BaseDocument doc, int progress) {
        //cast
        Play play = (Play) doc;
        List<String> playList = new ArrayList<>();
        Map<String, Integer> actToPage = new HashMap<>();
        int pageNum = 0;

        StringBuilder stringBuilder = new StringBuilder();
        boolean firstLine = Boolean.TRUE;
        while (true) {
            String line = FileUtils.readString();
            if (FileUtils.eof()) {
                break;
            }

            // new page
            if (firstLine || line.equals(DocumentConstants.END_OF_ACT)) {
                // find start of page
                while (DocumentConstants.END_OF_ACT.equals(line)
                        || DocumentConstants.FILE_RETURN.equals(line)) {
                    line = FileUtils.readString();
                    if (FileUtils.eof()) {
                        break;
                    }
                }
                // add to list
                if (firstLine) {
                    firstLine = Boolean.FALSE;
                } else {
                    playList.add(stringBuilder.toString());
                }

                // new page
                stringBuilder.delete(0, stringBuilder.length());
                actToPage.put(line, ++pageNum);

                // set progress
                progress += DocumentConstants.NOVEL_PROGRESS_STEPS;
                setProgress(Math.min(progress, DocumentConstants.TEMP_MAXIMUM_PROGRESS_NINETY));
            }
            stringBuilder.append(line);
        }
        // add the last
        playList.add(String.valueOf(stringBuilder));
        play.setDocPages(playList);
        play.setChapterToPage(actToPage);
        // set 100 progress
        setProgress(DocumentConstants.MAXIMUM_PROGRESS_ONE_HUNDRED);
    }

    /**
     * handel document of poem
     *
     * @param doc      document
     * @param progress progress
     */
    private void handlePoem(BaseDocument doc, int progress) {
        // cast
        Poem poem = (Poem) doc;
        List<String> poemList = new ArrayList<>();
        Map<String, Integer> poemToPage = new HashMap<>();
        int pageNum = 0;

        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = FileUtils.readString();
            if (FileUtils.eof()) {
                break;
            }

            if (line.equals(DocumentConstants.FILE_RETURN)) {
                // new poem
                poemList.add(String.valueOf(stringBuilder));
                poemToPage.put(DocumentConstants.POEM_CHAPTERS_SUFFIX + ++pageNum, pageNum);
                stringBuilder.delete(0, stringBuilder.length());
                // set progress
                progress += DocumentConstants.NOVEL_PROGRESS_STEPS;
                setProgress(Math.min(progress, DocumentConstants.TEMP_MAXIMUM_PROGRESS_NINETY));
            } else {
                // a poem
                stringBuilder.append(line);
            }
        }
        // add the last
        poemList.add(String.valueOf(stringBuilder));
        poemToPage.put(DocumentConstants.POEM_CHAPTERS_SUFFIX + ++pageNum, pageNum);

        poem.setDocPages(poemList);
        poem.setChapterToPage(poemToPage);
        // set 100 progress
        setProgress(DocumentConstants.MAXIMUM_PROGRESS_ONE_HUNDRED);
    }

    /**
     * get a document
     *
     * @return document
     */
    private BaseDocument getDocument() {
        String fileType = FileUtils.readString().trim();
        String[] typeSplits = fileType.split(DocumentConstants.FILE_HEADER_SEPARATOR);
        String type = typeSplits[1].trim();
        switch (type) {
            case DocumentConstants.POEM_NAME:
                return new Poem();
            case DocumentConstants.PLAY_NAME:
                return new Play();
            case DocumentConstants.NOVEL_NAME:
                return new Novel();
            default:
                return null;
        }
    }

    /**
     * handle header lines
     *
     * @param doc  document object
     * @param line line of document
     */
    private void handleHeader(BaseDocument doc, String line) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] kvSplits = line.split(DocumentConstants.FILE_HEADER_SEPARATOR);
        String key = kvSplits[0].trim();
        String val = kvSplits[1].trim();

        // handle basic header information
        setHeader(key, val, doc, DocumentConstants.BASE_FIELDS, BaseDocument.class);

        // handle header information of poem
        setHeader(key, val, doc, DocumentConstants.POEM_FIELDS, Poem.class);

        // handle header information of play
        setHeader(key, val, doc, DocumentConstants.PLAY_FIELDS, Play.class);

        // handle header information of novel
        setHeader(key, val, doc, DocumentConstants.NOVEL_FIELDS, Novel.class);
    }

    /**
     * set value of header in specific instance
     *
     * @param key           key of header
     * @param val           value of header
     * @param doc           document
     * @param kMethodMap    method map
     * @param documentClass class of document
     * @throws NoSuchMethodException     exception
     * @throws IllegalAccessException    exception
     * @throws InvocationTargetException exception
     */
    private void setHeader(String key, String val, BaseDocument doc,
                           Map<String, String> kMethodMap,
                           Class<? extends BaseDocument> documentClass)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (kMethodMap.containsKey(key)) {
            String method = kMethodMap.get(key);
            // get method
            Method declaredMethod = documentClass.getDeclaredMethod(method, String.class);
            // execute
            declaredMethod.invoke(doc, val);
        }
    }

    // https://blog.csdn.net/xietansheng/article/details/78389265
    @Override
    protected void done() {
        // get old model
        ListModel<String> model = docViewerFrame.getLoadNameChangeListener().getInitModel();
        //new model
        DefaultListModel<String> defModel = new DefaultListModel<>();

        for (int i = 0; i < model.getSize(); i++) {
            if (docName.equals(model.getElementAt(i))) {
                defModel.addElement(docName + DocumentConstants.DOC_LOADED_SUFFIX);
            } else {
                defModel.addElement(model.getElementAt(i));
            }
        }

        // reset model
        docViewerFrame.getLoadNameChangeListener().setInitModel(defModel);

        // set selected text file
        docViewerFrame.getSelectedFiled().setText(docName);

        // set search text file to empty
        docViewerFrame.getSearchField().setText("");

        String infoMsg = "Document: [" + docName + "] loads successfully.";
        System.out.println(infoMsg);
        JOptionPane.showMessageDialog(docViewerFrame, infoMsg,
                DocumentConstants.INFO, JOptionPane.INFORMATION_MESSAGE);
    }
}
