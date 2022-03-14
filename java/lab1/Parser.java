package nsu.fit.oop.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class Parser {
    private ArrayList<Entry<String, Integer>> vocabl;
    private int numWords;
    private final String inputFile;
    private final String outFile;

    public Parser(String filename){
        inputFile = filename;
        outFile = "out.csv";
        numWords = 0;
    }

    public Parser(String fileIn, String fileOut){
        inputFile = fileIn;
        outFile = fileOut;
        numWords = 0;
    }

    public void parse() throws IOException {
        FileReader reader = new FileReader(inputFile);
        HashMap<String, Integer> collect = new HashMap<>();
        char[] cbuf = new char[1000];
        while(reader.read(cbuf) > 0){
            String sbuf = new String(cbuf);
            String[] listWords = sbuf.split("[^a-zA-Zа-яёА-ЯЁ0-9]+");
            for (String word : listWords) {
                int kol = collect.get(word) != null? collect.get(word) + 1 : 1;
                collect.put(word, kol);
            }
        }
        for (String word : collect.keySet())
            numWords += collect.get(word);
        vocabl = new ArrayList<>(collect.entrySet());
        vocabl.sort(Entry.comparingByKey());
        Collections.reverse(vocabl);
        vocabl.sort(Entry.comparingByValue());
        Collections.reverse(vocabl);
        reader.close();
    }

    public void printing() throws IOException{
        FileWriter writer = new FileWriter(outFile);
        for (Entry<String, Integer> record : vocabl){
            writer.write(record.getKey() + ", ");
            writer.write(record.getValue() + ", ");
            writer.write(record.getValue().floatValue() / numWords * 100 + "\n");

        }
        writer.close();
    }
}
