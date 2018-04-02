package com.gaponec.service;

import com.gaponec.domain.Dictionary;
import com.gaponec.objects.Statistics;
import com.gaponec.objects.WordPair;
import lombok.Data;

import java.util.Random;

@Data
public class CheckService {
    private Dictionary dictionary;
    private Statistics statistics;

    private WordPair currentWordPair;

    public CheckService(){
        statistics = new Statistics();
    }

    public CheckService(Dictionary dictionary){
        this.dictionary = dictionary;

        statistics = new Statistics();
    }

    public CheckService(Dictionary dictionary, Statistics statistics){
        this.dictionary = dictionary;
        this.statistics = statistics;

        statistics.setUnsolved(dictionary.getWordPairs().size());
    }

    public String getRandomWord(){
        int size = dictionary.getWordPairs().size();

        if(size == 0){
            return null;
        }

        Random random = new Random();

        int randomNum = random.nextInt(size);

        currentWordPair = dictionary.getWordPairs().get(randomNum);
        dictionary.getWordPairs().remove(randomNum);

        return currentWordPair.getWordRUS().getName();
    }

    public boolean checkAnswer(String engWord){
        if(engWord.compareToIgnoreCase(currentWordPair.getWordENG().getName()) == 0){

            statistics.decrementUnsolved();
            statistics.incrementRight();

            return true;
        }else {

            statistics.decrementUnsolved();
            statistics.incrementWrong();

            return false;
        }
    }
}
