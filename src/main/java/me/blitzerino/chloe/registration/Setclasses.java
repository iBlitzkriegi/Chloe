package me.blitzerino.chloe.registration;

import me.blitzerino.chloe.Chloe;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blitz on 8/4/2016.
 */
public class Setclasses {
    // This class is not created by the developer but found here: https://coderanch.com/t/485985/vc/Iterating-classes-package-Java-project and editied to meet my needs \\
    public static Class[] getClassesInPackage(String pckgname) {
        File directory = getPackageDirectory(pckgname);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Could not get directory resource for package " + pckgname + ".");
        }

        return getClassesInPackage(pckgname, directory);
    }

    private static Class[] getClassesInPackage(String pckgname, File directory) {
        List<Class> classes = new ArrayList<Class>();
        for (String filename : directory.list()) {
            if (filename.endsWith(".class")) {
                String classname = buildClassname(pckgname, filename);
                try {
                    classes.add(Class.forName(classname));
                } catch (ClassNotFoundException e) {
                    System.err.println("Error creating class " + classname);
                }
            }
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static String buildClassname(String pckgname, String filename) {
        return pckgname + '.' + filename.replace(".class", "");
    }

    private static File getPackageDirectory(String pckgname) {
        ClassLoader cld = Thread.currentThread().getContextClassLoader();
        if (cld == null) {
            throw new IllegalStateException("Can't get class loader.");
        }

        URL resource = cld.getResource(pckgname.replace('.', '/'));
        if (resource == null) {
            throw new RuntimeException("Package " + pckgname + " not found on classpath.");
        }

        return new File(resource.getFile());
    }
    public static void setClasses(){
        Class[] classes = getClassesInPackage("me.blitzerino.chloe.api");
        Class[] classes1 = getClassesInPackage("me.blitzerino.chloe.commands.administration");
        Class[] classes6 = getClassesInPackage("me.blitzerino.chloe.commands.memes");
        Class[] classes7 = getClassesInPackage("me.blitzerino.chloe.commands.misc");
        Class[] classes8 = getClassesInPackage("me.blitzerino.chloe.commands.searching");
        Class[] classes2 = getClassesInPackage("me.blitzerino.chloe.listeners");
        Class[] classes3 = getClassesInPackage("me.blitzerino.chloe.others");
        Class[] classes4 = getClassesInPackage("me.blitzerino.chloe.player.commands");
        Class[] classes9 = getClassesInPackage("me.blitzerino.chloe.player.events");
        Class[] classes5 = getClassesInPackage("me.blitzerino.chloe.registration");
        for(Class c : classes){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes1){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes2){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes3){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes4){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes5){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes6){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes7){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes8){
            Chloe.classes.add(c.getSimpleName());
        }for(Class c : classes9){
            Chloe.classes.add(c.getSimpleName());
        }

    }
}
