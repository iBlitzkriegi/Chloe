package me.blitzerino.chloe.registration;

import me.blitzerino.chloe.Chloe;

import java.io.*;

import static me.blitzerino.chloe.api.API.getAPI;

/**
 * Created by Blitz on 8/4/2016.
 */
public class Setadmins {
    public static void setAdmins(){
        File adminfile = new File("admins.txt");
        try {
            if(!adminfile.createNewFile()){
                String line;
                String fileName = "admins.txt";
                try {
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    while ((line = bufferedReader.readLine()) != null) {
                        Chloe.admins.add(line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getAPI().infoPrint("Admins added");
            }else{
                adminfile.createNewFile();
                String admins = "99895160896225280";
                String admin1 = "98208218022428672";
                BufferedWriter writer;
                try{
                    writer = new BufferedWriter(new FileWriter(adminfile));
                    writer.write(admins + "\n");
                    writer.write(admin1 + "\n");
                    Chloe.admins.add(admin1);
                    Chloe.admins.add(admins);
                    writer.close();
                    getAPI().infoPrint("Created a new admins file and added top priority admins to it.");
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
