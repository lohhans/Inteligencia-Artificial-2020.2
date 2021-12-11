package com.armstrong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CrawlerGenero {
    public static String main(String palavra) {

        Document document;
        try {
            document = Jsoup.connect("https://pt.wiktionary.org/wiki/"+palavra)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.
                    .ignoreHttpErrors(true).get();


            Elements generos = document.select("div.vector-body > div.mw-body-content > div.mw-parser-output > p > i");
            Element genero = null;

            for (Element gen : generos) {
                String replace = gen.toString().replace("<i>", "").replace("</i>", "");
                if(replace.equalsIgnoreCase("feminino") || replace.equalsIgnoreCase("masculino") || replace.equalsIgnoreCase("comum aos dois géneros")){
                    genero = gen;
                    break;
                }
            }

            assert genero != null;
            return (genero.toString().replace("<i>", "").replace("</i>", ""));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e){
            return "masculino";
        }

        return palavra;
    }

}