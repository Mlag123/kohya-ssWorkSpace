package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class WindowImageTagger extends JFrame {
    private JPanel main_panel;
    private JLabel image_label;
    private JButton nextButton;
    private JButton editButton;
    private JButton prevButton;
    private JTextField tags_editor;
    private JLabel file_name;
    private Logic logic;
    private ArrayList<File> image_array;
    private ArrayList<File> tags_array;
    private ArrayList<String> file_names = new ArrayList<>();
    private HashMap<String, ImageIcon> image_andName = new HashMap<>();

    private ArrayList<ImageIcon> imageIcons = new ArrayList<>();
    private int current_image = 0;

    public WindowImageTagger() throws InterruptedException {
        logic = new Logic();
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(main_panel);
        setResizable(true);
        setSize(768, 768);


        image_array = Logic.getAllImages();
        tags_array = Logic.getText();
        loadImage();

        file_name.setText(file_names.get(current_image));

        image_label.setIcon(image_andName.get(file_names.get(current_image)));


        listenerEvent();
    }


    public void readAndSetTextInLabel(int current){
        //tags
        for(File file:tags_array){
            String filename = file.getName().replace(".txt","");
            if (filename.equalsIgnoreCase(file_names.get(current))){
                tags_editor.setText(readFile(file));
            }
        }
    }
    public void writeTagsInFile(int current){
        for(File file:tags_array){
            String filename = file.getName().replace(".txt","");
            if (filename.equalsIgnoreCase(file_names.get(current))){
                writeToFile(file,tags_editor.getText());
            }
        }
    }
    private void listenerEvent() {
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_image++;
                if (current_image > file_names.size()-1) {
                    current_image = file_names.size()-1;
                }
                file_name.setText(file_names.get(current_image));
                image_label.setIcon(image_andName.get(file_names.get(current_image)));


                readAndSetTextInLabel(current_image);
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_image--;
                if (current_image < 0) {
                    current_image =0;
                }
                file_name.setText(file_names.get(current_image));

                image_label.setIcon(image_andName.get(file_names.get(current_image)));
                readAndSetTextInLabel(current_image);

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeTagsInFile(current_image);

            }
        });
    }


    public String readFile(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return content.toString();
    }
    public void writeToFile(File file, String content) {
        try {
            Files.write(file.toPath(), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void equilsTagsOnImagesName() {

    }

    public void loadImage() {

        for (File file : image_array) {
            String file_to_path = file.getPath();
            ImageIcon imageIcon = new ImageIcon(file_to_path);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
            System.out.println(file.getName().replace(".png", ""));
            image_andName.put(file.getName().replace(".png", ""), imageIcon);
            file_names.add(file.getName().replace(".png", ""));

        }
    }


}
