package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> anagrams = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            br.lines()
                    .map(String::toLowerCase)
                    .filter(x -> x.matches("[а-я]+") && x.length() >= 3)
                    .collect(Collectors.toList())
                    .forEach(c -> {
                        char[] chs = c.toCharArray();
                        Arrays.sort(chs);
                        String el = new String(chs);
                        anagrams.computeIfAbsent(el, x -> new TreeSet<>()).add(c); });
        } catch (IOException ignored) {}

        return anagrams.values().stream()
                .filter(x -> x.size() >= 2)
                .collect(Collectors.toList());
    }
}
