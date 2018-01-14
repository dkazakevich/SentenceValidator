package com.kazakevich;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<String> dictionary = Dictionary.getInstance();
        Scanner sc = new Scanner(System.in);

        System.out.println("Print a sentence and press Enter key to process it:");

        while (sc.hasNextLine()) {
            String result = SentenceNormalizer.normalize(sc.nextLine(), dictionary);

            if (result.isEmpty()) {
                System.out.println("Not valid sentence.");
            } else {
                System.out.println("Valid sentence. E.g.:");
                System.out.println(result);
            }
        }
    }
}
