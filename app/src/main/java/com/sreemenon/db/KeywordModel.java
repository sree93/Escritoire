package com.sreemenon.db;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by srinath on 29/5/17.
 */

public class KeywordModel extends SugarRecord {
    String keyword;

    public KeywordModel(String keyword) {
        this.keyword = keyword;
    }

    public static KeywordModel GET_OR_CREATE_KEYWORD(String keyword){
        List<KeywordModel> keywordEntries = KeywordModel.find(KeywordModel.class, "keyword = ?", keyword);
        if (keywordEntries.size() < 1){
            KeywordModel keywordEntry = new KeywordModel(keyword);
            keywordEntry.save();
            return keywordEntry;
        }
        else {
            return keywordEntries.get(0);
        }
    }
}
