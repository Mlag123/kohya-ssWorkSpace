package Engine;

import logging.Logging;
import logging.logs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Logic {

    private String input_path = "./run/input";
    private String output_path = "./run/output";

    private List<File> arrFiles;
    private List<File> sizeFiles;
    private int targetSize = 512;

    public Logic() throws InterruptedException {
        File input = new File(input_path);
        File output = new File(output_path);
        if (!input.isDirectory()) input.mkdirs();
        if (!output.isDirectory()) output.mkdirs();
        Logging.log(logs.info, "Workspace created");

    }

    public void readFiles() {
        File input = new File("./run/input");
        arrFiles = new ArrayList<>();
        for (File file : input.listFiles()) {
            if (file.isFile()) {
                arrFiles.add(file);
            }
        }
        sizeFiles = arrFiles;
    }


    public void edit_image() {

    }


    public void Rename(String rename_text, int targetSize) {
        readFiles();

        if (targetSize == 0 | targetSize <= 64|targetSize>=4096) {
            JOptionPane.showMessageDialog(null,"Image size can't be less 64x64 or 4096x4096");
            return;
        } else {
            this.targetSize = targetSize;
        }

        for (int i = 0; i < arrFiles.size(); i++) {
            File file = arrFiles.get(i);

            // имя выходного файла
            String newName = (i == 0) ? rename_text + ".png" : rename_text + i + ".png";
            File outputFile = new File("./run/output/" + newName);

            System.out.println("name: " + output_path);

            try {
                String fileName = file.getName().toLowerCase();

                if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                    BufferedImage original = ImageIO.read(file);
                    if (original != null) {
                        // масштабирование с сохранением пропорций
                        double scale = Math.min(
                                (double) targetSize / original.getWidth(),
                                (double) targetSize / original.getHeight()
                        );
                        int newW = (int) (original.getWidth() * scale);
                        int newH = (int) (original.getHeight() * scale);

                        Image scaled = original.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);

                        // создаём пустую 512x512 картинку
                        BufferedImage resized = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = resized.createGraphics();

                        // фон (чёрный)
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(0, 0, targetSize, targetSize);

                        // рисуем картинку по центру
                        int x = (targetSize - newW) / 2;
                        int y = (targetSize - newH) / 2;
                        g2d.drawImage(scaled, x, y, null);
                        g2d.dispose();

                        // сохраняем в PNG
                        ImageIO.write(resized, "png", outputFile);
                    }
                } else {
                    Logging.log("Пропущен файл: " + file.getName());
                }

            } catch (IOException e) {
                Logging.log(logs.error, "Ошибка при обработке файла: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    public void createTextFile(String name, String tags_text) throws IOException {
        String nameText = name;
        File input = new File("./run/input");
        FileWriter fileWriter;

        for (int i = 0; i < sizeFiles.size(); i++) {


            if (i == 0) {
                File textFile = new File("./run/output" + "/" + nameText + ".txt");
                textFile.createNewFile();
                fileWriter = new FileWriter(textFile);
                fileWriter.write(tags_text);
                fileWriter.flush();
                fileWriter.close();


            } else {
                File textFile = new File("./run/output" + "/" + nameText + i + ".txt");
                if (textFile.createNewFile()) {
                    fileWriter = new FileWriter(textFile);
                    fileWriter.write(tags_text);
                    fileWriter.flush();
                    fileWriter.close();
                }


            }
        }

        Logging.log(logs.debug, String.valueOf(sizeFiles.size()));


    }

    public static void main(String[] args) throws InterruptedException {
        //    new Window();
        new WindowLogic();

    }

}
