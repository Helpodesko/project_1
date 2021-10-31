/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

public class WordCounter {

    public Hashtable count_words(String contents) {
        Hashtable<String, Integer> count = new Hashtable<>();
        String[] specialCharacters = {",", "-", ".", ":", "\"", "?"};
        int totalWords = 0;
        String[] lines;
        if (contents != null) {
            contents = contents.toLowerCase();
            for (int i = 0; i < specialCharacters.length; i++) {
                contents = contents.replace(specialCharacters[i], "");
            }
            //split data based on new lines
            lines = contents.split("\n");
            // reading line by line
            for (String currentLine : lines) {
                //getting all words from line
                String[] words = currentLine.split(" ");
                for (String current_word : words) {
                    if (current_word.trim().equals("")) {
                        continue;
                    }
                    //if word alreay exist in the count (hashtable)
                    if (count.containsKey(current_word)) {
                        totalWords = (int) count.get(current_word);
                        totalWords++;
                    } else {
                        totalWords = 1;
                    }
                    count.put(current_word, totalWords);
                }
            }
        }
        return count;
    }

    public LinkedList top20(Hashtable count) {
        LinkedList<Entry<String, Integer>> top20List = new LinkedList<>();
        if (count != null) {
            if (!count.keySet().isEmpty()) {
            Set<Entry<String, Integer>> entries = count.entrySet();//get the enrry set of the hastable
            List<Entry<String, Integer>> listTobeSorted = new LinkedList<Entry<String, Integer>>(entries);//create a new linked list from the set
            Collections.sort(listTobeSorted, new Comparator<Entry<String, Integer>>() {
                @Override
                public int compare(Entry<String, Integer> firstEnty, Entry<String, Integer> secondEntry) {
                    if (firstEnty.getValue() < secondEntry.getValue()) {
                        return 1;
                    } else if (firstEnty.getValue() > secondEntry.getValue()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            int i =0;
            while(i<20){
                top20List.add(listTobeSorted.get(i));
                i++;
            }
        }
    }
    return top20List ;
}

public static void main(String args[]) {
        try {
            String contents = "";
            Scanner in = new Scanner(new FileReader("Fear.txt"));
            while (in.hasNextLine()) {
                contents += in.nextLine() + "\n";
            }
            WordCounter wc = new WordCounter();
            Hashtable count = wc.count_words(contents);
            LinkedList top20 = wc.top20(count);
            for (Object item : top20) {
                System.out.println(item);
            }
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
