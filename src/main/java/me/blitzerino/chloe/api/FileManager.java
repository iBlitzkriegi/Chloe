package me.blitzerino.chloe.api;

import java.io.*;

/**
 * Created by Blitz on 10/3/2016.
 */
public class FileManager {
    private static FileManager instance;
    private FileManager(){
        instance = this;
    }
    public static FileManager getFileManager(){
        if(instance == null){
            return new FileManager();
        }else{
            return instance;
        }
    }
    // Todo File Managment\\
    public void removeTodo(String thing, String file) throws IOException {
        File inputFile = new File(file);
        File tempFile = new File("myTempFile.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while((currentLine = reader.readLine()) != null) {if(null!=currentLine && !currentLine.startsWith(thing.trim())){writer.write(currentLine + System.getProperty("line.separator"));}
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
    // ChangeLog file Manager\\
    public void resetChangelog(String file){
        File inputFile = new File(file);
        try {
            if(!inputFile.createNewFile()){
                inputFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File tempFile = new File("myTempFile.txt");
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
    public void addChangeLog(String toadd, String file){
        File input = new File(file);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
        } catch (IOException r) {
            r.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(toadd);

        } catch (IOException b) {
            b.printStackTrace();
        }
        try {
            bw.newLine();
        } catch (IOException t) {
            t.printStackTrace();
        }
        try {
            bw.flush();
        } catch (IOException m) {
            m.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException p) {
            p.printStackTrace();
        }
    }

}
