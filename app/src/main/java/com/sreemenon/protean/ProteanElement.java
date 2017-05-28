package com.sreemenon.protean;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srinath on 26/5/17.
 */

public class ProteanElement{

    private String richText;
    private Element element;
    private String tag;

    private String leftoverText;

    public static List<ProteanElement> GetProteanList(String richText){
        String remainingText = richText;
        List<ProteanElement> elementList = new ArrayList<>();
        while(!remainingText.trim().equals("")){
            ProteanElement currentElement = new ProteanElement(remainingText);
            elementList.add(currentElement);
            remainingText = currentElement.getLeftoverText();
        }
        return elementList;
    }

    public ProteanElement(String richText) {
        this.richText = richText;
        initialiseElement(richText);
    }

    private void initialiseElement(String richText){
        Document document = Jsoup.parseBodyFragment(richText);
        element = document.body().children().first();

        document.body().children().first().remove();
        tag = element.tagName();
        leftoverText = document.body().children().toString();
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
        initialiseElement(richText);
    }

    public String getTag() {
        return tag;
    }

    public String getLeftoverText() {
        return leftoverText;
    }

    public String getText(){
        return element.text();
    }

//    public Image getImage(){
//        return null;
//    }
}
