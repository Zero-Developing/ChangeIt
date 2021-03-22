package com.namespace.function;

import javax.lang.model.element.Element;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public class Actions {

    public static String[] list_of_attributes = {"lastModifiedTime", "lastAccessTime", "creationTime", "size", "isRegularFile", "isDirectory", "isSymbolicLink", "isOther", "filekey"};

    public static HashMap<String, Object> list_of_types = new HashMap<>(Map.ofEntries(
            entry("lastModifiedTime", FileTime.class),
            entry("lastAccessTime", FileTime.class),
            entry("creationTime", FileTime.class),
            entry("size", long.class),
            entry("isRegularFile", boolean.class),
            entry("isDirectory", boolean.class),
            entry("isSymbolicLink", boolean.class),
            entry("isOther", boolean.class),
            entry("filekey", Object.class)
    ));

    public static boolean changeAttribute(File file, String attribute, Object value){
        if(list_of_types.containsKey(attribute) && value.getClass() == list_of_types.get(attribute).getClass()) {
            //Check if file exists
            if (file.exists()) {
                try {
                    //If file exists, update attribute
                    Files.setAttribute(file.getAbsoluteFile().toPath(), attribute, value);
                    System.out.println("[✓] Changed attribute -> " + attribute);
                    return true;
                } catch (IOException e) {
                    System.err.println("Can't change attribute" + attribute + " --> " + e.getMessage());
                }
            } else {
                System.err.println(file.getName() + " not found");
            }
        }
        return false;
    }
}
