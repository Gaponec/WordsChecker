package com.gaponec.objects;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Data
@ToString
public class WordPair implements Serializable {
    @Id @GeneratedValue
    private int id;

    private Word wordENG;
    private Word wordRUS;
}
