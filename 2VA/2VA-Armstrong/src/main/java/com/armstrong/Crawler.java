package com.armstrong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Crawler {
    public static String main(String palavra) {
        //1. Pick a URL from the frontier
//        new Crawler().getPageLinks("https://pt.wiktionary.org/wiki/flor/");
        Document document = null;
        try {
            document = Jsoup.connect("https://pt.wiktionary.org/wiki/"+palavra)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.
                    .ignoreHttpErrors(true).get();
//            Element links  = document.select("div.vector-body > div.mw-body-content > div.mw-parser-output > p > i").first();
            Element genero = document.select("div.vector-body > div.mw-body-content > div.mw-parser-output > p > i").first();

//            System.out.println(links);

//            System.out.println((links.toString().replace("<i>", "").replace("</i>", "")));
            return (genero.toString().replace("<i>", "").replace("</i>", ""));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e){
            return "masculino";
        }

        return palavra;
    }

}