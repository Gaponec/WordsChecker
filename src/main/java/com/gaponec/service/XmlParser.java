package com.gaponec.service;

import com.gaponec.domain.Dictionary;
import com.gaponec.objects.Word;
import com.gaponec.objects.WordPair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class XmlParser {

    private final static String ENG = "английский";
    private final static String RUS = "русский";

    public Dictionary parse(File file) throws IOException, InvalidFormatException {

        //Creating out workbook for parsing
        Workbook workbook = WorkbookFactory.create(file);

        //Getting all sheets from file
        Iterator<Sheet> sheets = workbook.sheetIterator();

        Sheet firstSheet = workbook.getSheetAt(0);
        Dictionary dictionary = new Dictionary(firstSheet.getSheetName());

        workbook.forEach(sheet -> {
            sheet.forEach(row->{

                Word word1 = new Word();
                Word word2 = new Word();


                row.forEach(cell -> {

                    switch (cell.getColumnIndex()){
                        case 0:
                            word1.setLanguage(cell.getStringCellValue());
                            break;
                        case 1:
                            word2.setLanguage(cell.getStringCellValue());
                            break;
                        case 2:
                            word1.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            word2.setName(cell.getStringCellValue());
                            break;
                    }

                });

                WordPair wordPair = new WordPair();

                if(word1.getLanguage().equals(ENG)){
                    wordPair.setWordENG(word1);
                    wordPair.setWordRUS(word2);
                } else if(word1.getLanguage().equals(RUS)){
                    wordPair.setWordENG(word2);
                    wordPair.setWordRUS(word1);
                }

                dictionary.addWordPair(wordPair);

            });
        });

        return dictionary;
    }
}
