package Engine;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class WindowImageTagger extends JFrame {

    private JPanel panel = new JPanel();

    private JButton next_but = new JButton("Next");
    private JButton back_but = new JButton("Back");
    private JButton editTag_but = new JButton("Edit");

    public WindowImageTagger() {
        super("Editor Tags");
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(new ImageIcon(WindowLogic.givenFile_getResourcePath()).getImage());
        setSize(800, 600);

    }


    private void onDraw() {

    }
}
class ArrayImageReader{
    private ArrayList<File> imageFileList;
    public ArrayImageReader(){

    }
}