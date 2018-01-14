package com.kazakevich;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by kazakevich_d on 13.01.2018.
 */
public class Dictionary extends HashSet<String> {

    private Dictionary() {
        try {
            URL url = getClass().getClassLoader().getResource("dictionary.txt");
            if (url != null) {
                try (Stream<String> lines = Files.lines(Paths.get(url.toURI()))) {
                    lines.forEach(this::add);
                }
            } else {
                throw new FileNotFoundException("Dictionary file not found.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class SingletonHelper {
        private static final Dictionary INSTANCE = new Dictionary();
    }

    public static Set<String> getInstance() {
        return SingletonHelper.INSTANCE;
    }
}