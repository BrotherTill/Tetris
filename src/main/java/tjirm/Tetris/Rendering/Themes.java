package main.java.tjirm.Tetris.Rendering;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Themes {

    private final static HashMap<String, BufferedImage> RetroLook = new HashMap<>();
    private final static HashMap<String, BufferedImage> Patterns = new HashMap<>();

    public static void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for(Map.Entry<String, BufferedImage> entry : RetroLook.entrySet()){
                String name = entry.getKey();
                BufferedImage image = entry.getValue();
                File file = new File("src/Main/resources/Images/retro" + name + ".png");
                try {
                    ImageIO.write(image, "png", file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            for(Map.Entry<String, BufferedImage> entry : Patterns.entrySet()){
                String name = entry.getKey();
                BufferedImage image = entry.getValue();
                File file = new File("src/Main/resources/Images/pattern" + name + ".png");
                try {
                    ImageIO.write(image, "png", file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        List<File> files = Stream.of(new File("src/Main/resources/Images/").listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().startsWith("retro") && file.getName().endsWith(".png"))
                .collect(Collectors.toList());
        for(File file : files) {
            System.out.println(file.getName());
            try {
                String name = file.getName().substring(5,file.getName().length() - 4);
                RetroLook.put(name, ImageIO.read(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        files = Stream.of(new File("src/Main/resources/Images/").listFiles())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.getName().startsWith("pattern") && file.getName().endsWith(".png"))
                .collect(Collectors.toList());
        for(File file : files) {
            System.out.println(file.getName());
            try {
                String name = file.getName().substring(7,file.getName().length() - 4);
                Patterns.put(name, ImageIO.read(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addRetroLook(String name, BufferedImage image) {
        if(RetroLook.containsKey(name))
            return;
        RetroLook.put(name, image);
    }
    public static int getRetroLookSize() {
        return RetroLook.size();
    }
    public static BufferedImage getRetroLook(String name) {
        if(!RetroLook.containsKey(name)) {
            JOptionPane.showMessageDialog(null,
                    "The Image for this Theme is missing",
                    "Critical Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new NullPointerException("Looked for a Theme for which the File does not Exist");
        }
        return RetroLook.get(name);
    }

    public static void addPattern(String name, BufferedImage image) {
        if(Patterns.containsKey(name))
            return;
        Patterns.put(name, image);
    }
    public static int getPatternSize() {
        return Patterns.size();
    }
    public static BufferedImage getPattern(String name) {
        if(!Patterns.containsKey(name)) {
            JOptionPane.showMessageDialog(null,
                    "The Image for this Theme is missing",
                    "Critical Error",
                    JOptionPane.ERROR_MESSAGE);
            throw new NullPointerException("Looked for a Theme for which the File does not Exist");
        }
        return Patterns.get(name);
    }

    public enum themes {
        minimalistic,
        color,
        pattern,
        retro,
        rainbow,
    }

}
