package com.nvpiao.utils;

import com.nvpiao.constants.DocumentConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * com.nvpiao.utils.FileUtils.java
 * <p>
 * A simple class for com.nvpiao.utils.FileUtils
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   22 December 2019
 */
public class FileUtils {

    private static final String DOC_SUFFIX = ".txt";

    private static final String RETURN = "\n";

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * opened file
     */
    private static BufferedReader bReader;

    /**
     * end of file
     */
    private static boolean endOfFile;

    /**
     * name of file
     */
    private static String fileName;

    /**
     * search path of file utils
     */
    private String searchPath = DocumentConstants.DEF_FILE_SEARCH_PATH;

    public FileUtils() {
    }

    public FileUtils(String searchPath) {
        this.searchPath = searchPath;
    }

    /**
     * open file with utf-8 charset
     *
     * @param docName name of file
     */
    public static void openFile(String docName)
            throws FileNotFoundException, UnsupportedEncodingException {
        openFile(docName, DEFAULT_CHARSET);
    }

    /**
     * open file
     *
     * @param docName name of file
     * @param charset name of charset
     */
    public static void openFile(String docName, String charset)
            throws FileNotFoundException, UnsupportedEncodingException {
        fileName = docName;
        endOfFile = Boolean.FALSE;
        FileInputStream fis =
                new FileInputStream(DocumentConstants.DEF_FILE_SEARCH_PATH + docName);
        InputStreamReader isReader = new InputStreamReader(fis, charset);
        bReader = new BufferedReader(isReader);
    }

    /**
     * eof check
     *
     * @return {@code true} if it reach the end of file, otherwise {@code false}
     */
    public static boolean eof() {
        return endOfFile;
    }

    /**
     * read line
     *
     * @return line of file
     */
    public static String readString() {
        String line = null;
        try {
            line = bReader.readLine();
            if (null == line) {
                endOfFile = Boolean.TRUE;
                bReader.close();
                System.out.println("File [" + fileName + "] reach the end.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line + RETURN;
    }


    /**
     * load document list
     *
     * @return list of document
     */
    public Vector<String> findDoc() {
        Vector<String> docList = new Vector<>();

        // search directory
        File searchDir = new File(searchPath);
        if (searchDir.exists()) {
            // get all file
            File[] fileList = searchDir.listFiles();
            if (null == fileList) {
                System.out.println("An error occur while accessing the filesystem.");
                return docList;
            }

            // filter txt file
            for (File file : fileList) {
                String fileName = file.getName();
                if (fileName.endsWith(DOC_SUFFIX)) {
                    docList.add(fileName);
                }
            }
        } else {
            System.out.println("Search Directory [" + searchPath + "] not exists.");
        }

        return docList;
    }
}
