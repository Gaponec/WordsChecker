package com.gaponec;

import com.gaponec.service.CheckService;
import com.gaponec.service.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application{

    @Bean
    public XmlParser xmlParser(){
        return new XmlParser();
    }


    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class,args);
    }
}
