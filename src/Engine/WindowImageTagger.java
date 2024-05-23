package Engine;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

public class WindowImageTagger extends JFrame {

    private JPanel panel = new JPanel();

    private JButton next_but = new JButton("Next");
    private JButton back_but = new JButton("Back");
    private JButton editTag_but = new JButton("Edit");

    public WindowImageTagger() throws FileNotFoundException {
        super("Editor Tags");
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon(WindowLogic.givenFile_getResourcePath()).getImage());
        setSize(800, 600);
        new ArrayImageReader();

    }


    private void onDraw() {

    }
}

class ArrayImageReader {
    private ArrayList<File> imageFileList = new ArrayList<>();
    private File input_files = new File("./run/intput");
    private File outputs_file = new File("./run/output");


    public ArrayImageReader() throws FileNotFoundException {
        imageFileList = getImageFileList();
    }

    private ArrayList<File> getImageFileList() throws FileNotFoundException {

        ArrayList<File> fileArrayList = new ArrayList<>();
        for (File file : outputs_file.listFiles()
        )

            if (new String(file.getName()).endsWith(".png")) {

                fileArrayList.add(file);

            } else {
                break;
            }

        return fileArrayList;
    }
}