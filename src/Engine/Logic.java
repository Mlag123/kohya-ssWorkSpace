package Engine;

import logging.Logging;
import logging.logs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Logic {

    private String root = "./run";

    private List<File> arrFiles;
    private List<File> sizeFiles;
    private List<File> fileForRename;
    private int a = 1;
    private String rename = "/image" + a + ".png";

    public Logic() throws InterruptedException {
        File input = new File("./run/input");
        File output = new File("./run/output");
        if (!input.isDirectory()) input.mkdirs();
        if (!output.isDirectory()) output.mkdirs();
        Logging.log(logs.info, "Workspace created");

    }


    public void Rename(String rename_text) {
        String rename = rename_text;
        File input = new File("./run/input");
        // File output = new File("./run/output"); fixme 2462462462462462462462462
        arrFiles = new ArrayList<>();
        for (File file : input.listFiles()
        )
            arrFiles.add(file);
        {
            sizeFiles = arrFiles;

        }
        for (int i = 0; i < arrFiles.size(); i++) {
            a = i;
            File file = arrFiles.get(i);
            if (i == 0) {
                File renameFile = new File("./run/output" + "/" + rename + ".png");
                file.renameTo(renameFile);
            }
            File renameFile = new File("./run/output" + "/" + rename + i + ".png");
            //   System.out.println(a);
            if (file.renameTo(renameFile)) ;
            // Thread.sleep(600);
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
                if (textFile.createNewFile()){
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
