import com.nvpiao.constants.DocViewerConstants;
import com.nvpiao.constants.DocumentConstants;
import com.nvpiao.frame.DocViewerFrame;
import com.nvpiao.utils.FileUtils;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Vector;

/**
 * DocumentViewer.java
 * <p>
 * A simple class for ShapeDrawer
 *
 * @author MingLiu (Mliu54@sheffield.ac.uk)
 * @version 1.0   21 December 2019
 */
public class DocumentViewer {
    /**
     * Main method
     *
     * @param args The default no arguments
     */
    public static void main(String[] args) {
        // Start main GUI thread
        SwingUtilities.invokeLater(() -> {
            // get all doc list
            Vector<String> docList =
                    new FileUtils(DocumentConstants.DEF_FILE_SEARCH_PATH)
                            .findDoc();

            JFrame f = new DocViewerFrame(docList);
            f.setTitle(DocViewerConstants.DOCUMENT_VIEWER_TITLE);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        });
    }
}
