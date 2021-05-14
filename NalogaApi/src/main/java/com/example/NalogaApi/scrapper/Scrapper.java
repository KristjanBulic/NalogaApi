package com.example.NalogaApi.scrapper;

import com.example.NalogaApi.counter.Counter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Vector;

public class Scrapper implements Runnable{

    private String url;
    private String className;
    private Vector<String> storage;
    private Counter counter;

    public Scrapper(String url, String className, Vector<String> storage, Counter counter) {
        this.url = url;
        this.className = className;
        this.storage = storage;
        this.counter = counter;
    }

    public static void getContent(String url, String className, Vector<String> storage, Counter counter){
        /**
         * Returns wanted text from web page
         */
        try {
            Document doc = Jsoup.connect(url).get();
            String recivedText = String.valueOf(doc.getElementsByClass(className).text());

                if(recivedText.equals(null) || recivedText.equals("")){
                    recivedText = String.valueOf(doc.getElementsByClass("et_pb_fullwidth_header_subhead").text());
                } // page /o-nas/ has text in different class

            storage.add(recivedText);
            counter.addSuccesful();
        } catch (IOException e) {
            e.printStackTrace();
            storage.add("Coulden't get text");
            counter.unSuccesful();
        }
    }

    @Override
    public void run() {
        getContent(this.url, this.className, this.storage, this.counter);
    }
}
