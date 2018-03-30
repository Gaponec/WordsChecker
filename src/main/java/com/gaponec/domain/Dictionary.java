package com.gaponec.domain;

import com.gaponec.objects.WordPair;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@ToString
@Data
public class Dictionary implements Serializable {

    @Id @GeneratedValue
    private int id;

    private String name;

    private ArrayList<WordPair> wordPairs;

    public Dictionary(String name){
        this.name=name;
        wordPairs = new ArrayList<>();
    }

    public void addWordPair(WordPair wordPair){
        this.wordPairs.add(wordPair);
    }
}
