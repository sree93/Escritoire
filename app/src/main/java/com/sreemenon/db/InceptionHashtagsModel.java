package com.sreemenon.db;

import com.orm.SugarRecord;

/**
 * Created by srinath on 29/5/17.
 */

public class InceptionHashtagsModel extends SugarRecord {
    public IdeaModel idea;
    public KeywordModel keyword;

    public InceptionHashtagsModel(IdeaModel idea, KeywordModel keyword) {
        this.idea = idea;
        this.keyword = keyword;
    }
}
