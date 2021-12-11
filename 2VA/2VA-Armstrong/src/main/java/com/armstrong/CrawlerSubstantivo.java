package com.armstrong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CrawlerSubstantivo {
    public static boolean main(String palavra) {

        Document document;
        boolean ehSubstativo = false;

        try {
            document = Jsoup.connect("https://pt.wiktionary.org/wiki/"+palavra)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.
                    .ignoreHttpErrors(true).get();


            Elements substantivos = document.select("div.vector-body > div.mw-body-content > div.mw-parser-output > h2 > span");
            Element substantivo = substantivos.first();

            /*for (Element sub : substantivos) {
                if(sub.toString().contains("Substantivo")){
                    substantivo = sub;
                    break;
                }
            }*/

            assert substantivo != null;
            ehSubstativo = substantivo.toString().contains("Substantivo");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e){
            return false;
        }

        return ehSubstativo;
    }

}