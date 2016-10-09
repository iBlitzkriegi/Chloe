package me.blitzerino.chloe.others.memes;

import me.blitzerino.chloe.Chloe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/4/2016.
 */
public class Memecatch {
    public static ArrayList<String> images = new ArrayList<>();
    public static HashMap<String, File> imageCache = new HashMap<>();
    public static HashMap<String, String> gifCache = new HashMap<>();
    public synchronized static void cacheImage(String url, String extension, String name){
        try {
            if(extension.equalsIgnoreCase("gif")){
                gifCache.put(name, url);
                return;
            }
            File imgf = new File(name + "." + extension);
            BufferedImage img = ImageIO.read(new URL(url));
            ImageIO.write(img, extension, imgf);
            imageCache.put(name, imgf);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void cacheImages(){
        try{
            File file = new File("memes.txt");
            if(file.createNewFile()){
                String chloepfp = "https://iconicgamer.files.wordpress.com/2013/12/gamer_girl_by_dignity13-d650j73.jpg!jpg!pfp";
                PrintWriter writer = new PrintWriter("memes.txt", "UTF-8").append("\n");
                writer.write(chloepfp);
                writer.close();
                getAPI().infoPrint("Image Cache file was created!");

            }
        }catch(IOException e){
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("memes.txt")));
            String line = "";
            while((line=br.readLine()) != null) {
                String[] another = line.split("!");
                cacheImage(another[0], another[1], another[2]);
                Chloe.images.add(another[2] + "." + another[1]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
