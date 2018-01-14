package com.kazakevich;

import java.util.*;

/**
 * Created by kazakevich_d on 13.01.2018.
 */
public class SentenceNormalizer {
    //max word length to improve performance
    private static final int MAX_WORD_LENGTH = 10;

    public static boolean isValid(String sentence, Set<String> dictionary) {
        return !normalize(sentence, dictionary).isEmpty();
    }

    public static String normalize(String sentence, Set<String> dictionary) {
        String result = "";

        //splits sentence to parts around matches of a punctuation
        for (String sentencePart : sentence.toLowerCase().split("[^\\w\\s]")) {
            String normalized = normalizePart(sentencePart, dictionary);

            if (normalized != null) {
                result += normalized;
            } else {
                //part is not valid
                result = "";
                break;
            }
        }

        return result;
    }

    private static String normalizePart(String sentence, Set<String> dictionary) {
        String normSentence = null;

        //checks the end of sentence
        if (sentence.trim().isEmpty()) {
            normSentence = sentence.trim();
        } else {
            for (int i = 0; i < sentence.length() && i < MAX_WORD_LENGTH; i++) {
                String dictWord = getDictionaryWord(sentence.substring(0, i + 1), dictionary);

                if (dictWord != null) {
                    //found dictionary word and try to find the following ones
                    String normSubSentence = normalizePart(sentence.substring(i + 1), dictionary);

                    if (normSubSentence != null) {
                        //sentence is valid
                        normSentence = dictWord + " " + normSubSentence;
                        break;
                    }
                }
            }
        }

        return normSentence;
    }

    private static String getDictionaryWord(String scrambledWord, Set<String> dictionary) {
        String dictWord = null;

        if (dictionary.contains(scrambledWord)) {
            //dictionary contains initial word
            dictWord = scrambledWord;
        } else {
            //makes permutations to find dictionary word
            Set<String> permutations = getWordPermutations(scrambledWord);

            for (String permutation : permutations) {
                if (dictionary.contains(permutation)) {
                    //dictionary contains permutation
                    dictWord = permutation;
                    break;
                }
            }
        }

        return dictWord;
    }

    private static Set<String> getWordPermutations(String word) {
        Set<String> result = new HashSet<>();

        getWordPermutations(result, "", word);

        return result;
    }

    private static void getWordPermutations(Set<String> result, String part1, String part2) {
        int len = part2.length();

        if (len == 0) {
            result.add(part1);
        } else {
            for (int i = 0; i < len; i++)
                getWordPermutations(result, part1 + part2.charAt(i),
                        part2.substring(0, i) + part2.substring(i + 1, len));
        }
    }
}
