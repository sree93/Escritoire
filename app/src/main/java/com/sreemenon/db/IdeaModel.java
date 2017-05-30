package com.sreemenon.db;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by srinath on 29/5/17.
 */

public class IdeaModel extends SugarRecord {

    public String title;
    public String inception;
    public String matter;
    public String personalAnalysis;
    public Date updatedAt;
    public Date createdAt;

    public IdeaModel(String title, String inception, String matter, String personalAnalysis, Date updatedAt, Date createdAt) {
        this.title = title;
        this.inception = inception;
        this.matter = matter;
        this.personalAnalysis = personalAnalysis;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    @Override
    public long save() {
        long id = super.save();
        loadMatterKeywords(this.matter);
        loadInceptionKeywords(this.inception);
        return id;
    }

    private void loadMatterKeywords(String matter){
        Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(matter);
        List<String> strs=new ArrayList<String>();
        while (mat.find()) {
            String keyword = mat.group(1);
            KeywordModel keywodEntry = KeywordModel.GET_OR_CREATE_KEYWORD(keyword);
            MatterHashtagsModel hashtag = new MatterHashtagsModel(this, keywodEntry);
            hashtag.save();
                    
        }
    }

    private void loadInceptionKeywords(String inception){
        Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(inception);
        List<String> strs=new ArrayList<String>();
        while (mat.find()) {
            String keyword = mat.group(1);
            KeywordModel keywodEntry = KeywordModel.GET_OR_CREATE_KEYWORD(keyword);
            InceptionHashtagsModel hashtag = new InceptionHashtagsModel(this, keywodEntry);
            hashtag.save();

        }
    }
}
