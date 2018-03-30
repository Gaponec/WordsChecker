package com.gaponec.rest;

import com.gaponec.domain.Dictionary;
import com.gaponec.objects.Statistics;
import com.gaponec.service.CheckService;
import com.gaponec.service.Convector;
import com.gaponec.service.XmlParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
}
