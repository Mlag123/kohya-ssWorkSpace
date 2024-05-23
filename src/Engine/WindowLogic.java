package Engine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class WindowLogic extends JFrame {
    private JPanel Wind;
    private JCheckBox comboBoxTags;
    private JButton button;
    private JTextField jTextName;
    private JTextField jTextTags;
    private JButton editortags;
    private Boolean text_file_create_bool = false;
    private Logic logic;

    public static URL givenFile_getResourcePath() {
        URL resURL = WindowLogic.class.getResource("/favicon.png");
        return resURL;
    }

    private ImageIcon icon = new ImageIcon(givenFile_getResourcePath());

    public WindowLogic() throws InterruptedException {
        super("Kohya-ss Workspace");
        logic = new Logic();
        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(this.Wind);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextName.getText();
                String tags_text = jTextTags.getText();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Введите, как будут называется картинки");
                } else {

                    logic.Rename(text);
                    JOptionPane.showMessageDialog(null, "Готово!");

                    if (text_file_create_bool) {
                        try {
                            logic.createTextFile(text, tags_text);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Не удалось создать текстовые файлы");
                        }
                    }
                }
            }
        });
        comboBoxTags.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text_file_create_bool = comboBoxTags.isSelected();

            }
        });
        editortags.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new WindowImageTagger();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Deprecated
    public void changeFormatJPGtoPNG() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("./run/input/aaa.jpg"));
        ImageIO.write(bufferedImage, "png", new File("./run/output/aaa.png"));
    }


}
