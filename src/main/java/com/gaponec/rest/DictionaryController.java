package com.gaponec.rest;

import com.gaponec.domain.Dictionary;
import com.gaponec.objects.Statistics;
import com.gaponec.service.CheckService;
import com.gaponec.service.Convector;
import com.gaponec.service.XmlParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class DictionaryController {

    Dictionary dictionary;
    CheckService checkService;

    @Autowired
    XmlParser xmlParser;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/parse")
    public String parse(@RequestParam("file") MultipartFile multipartFile) throws IOException, InvalidFormatException {
        File file = Convector.convertFromMultipart(multipartFile);
        System.out.println(file.exists());

        this.dictionary = xmlParser.parse(file);


        return "redirect:/check";
    }

    @RequestMapping("/check")
    public String check(Model model){
        model.addAttribute("workPairs",dictionary.getWordPairs());
        checkService = new CheckService(dictionary);

        return "check";
    }

    @RequestMapping(value = "/getRandomWord", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getWord(){
        String randomWord = checkService.getRandomWord();

        return randomWord;
    }

    @RequestMapping(value = "/checkWord", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkWord(@RequestParam(value = "answer") String answer){
        if(checkService.checkAnswer(answer)){
            return "right";
        } else {
            return "wrong. Right is:<b> " + checkService.getCurrentWordPair().getWordENG().getName() + "</b>";
        }
    }


    @RequestMapping("/getStatistics")
    @ResponseBody
    public String getStatistics(){
        JSONObject object = new JSONObject();
        object.put("right",checkService.getStatistics().getRightAnswers());
        object.put("wrong",checkService.getStatistics().getWrongAnswers());

        return object.toString();
    }


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "Hello";
    }


}
