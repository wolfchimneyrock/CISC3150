/********************************************************
 * Robert Wagner
 * CISC 3150 HW #10
 * 2017-12-11
 *
 * hw10.java:
 *   In which some Beatles speak their minds
 *
 ********************************************************/

import java.io.*;
import java.util.*;
import plugin.*;

public class hw10 {

    public static String niceName(File f) {
        String s = f.getName();
        return s.substring(0, s.lastIndexOf('.'));
    }

    public static File[] getPlugins() {
        File pluginFolder = new File("plugin");

        File[] files = pluginFolder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                boolean rightExtension = name.endsWith(".class");
                boolean notAbstract = !name.startsWith("myplugin");
                return rightExtension && notAbstract;
            }
        });
        return files;
    }

    public static String chooseFromMenu(File[] files) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a plugin to run:");
        int i = 0;
        for (File f : files) {
            System.out.printf("[%2d] %s\n", i, niceName(f));
            i++;
        }
        int choice = -1;
        do {
            System.out.print(">> ");
            choice = sc.nextInt();
            if (choice < 0 || choice >= files.length) {
                System.out.println("Invalid choice, try again.");
                continue;
            } else {
                break;
            }
        } while (true);
        sc.close();
        return "plugin." + niceName(files[choice]);
    }
     

        


    public static void main(String[] args) throws Exception{
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        File[] plugins = getPlugins();
        String s = chooseFromMenu(plugins);

        Class cls = cl.loadClass(s);

        myplugin p = (myplugin)cls.newInstance();
        System.out.println();
        System.out.print(s + ".whoSaysHello(): ");
        p.whoSaysHello();
        System.out.print(s + ".whoSaysBye():   ");
        p.whoSaysBye();
    }
}

