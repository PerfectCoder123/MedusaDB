package MedussaDatabase.MedusaEngine;

import java.util.ArrayList;
import java.util.List;

public class QueryInterperator {
    private List<String> queries;

    QueryInterperator(List<String> queries) {
        this.queries = queries;
    }

    public List<Integer> interperate() {
        List<Integer> methodCodeList = new ArrayList<>();
        for (String query : queries) {
        int code = getMethodCode(query);
        methodCodeList.add(code);
        }
        return methodCodeList;
    }

    public int getMethodCode(String query) {
        if (query.equalsIgnoreCase(QueryKeyword.CREATE))
            return QueryKeyword.create;
        if (query.equalsIgnoreCase(QueryKeyword.DESC))
            return QueryKeyword.desc;
        if (query.equalsIgnoreCase(QueryKeyword.DELETE))
            return QueryKeyword.delete;
        if (query.equalsIgnoreCase(QueryKeyword.SELECT))
            return QueryKeyword.select;
        if (query.equalsIgnoreCase(QueryKeyword.USE))
            return QueryKeyword.use;
        if (query.equalsIgnoreCase(QueryKeyword.ALTER))
            return QueryKeyword.alter;
        if (query.equalsIgnoreCase(QueryKeyword.DROP))
            return QueryKeyword.drop;
        if (query.equalsIgnoreCase(QueryKeyword.SHOW))
            return QueryKeyword.show;
        if (query.equalsIgnoreCase(QueryKeyword.INSERT))
            return QueryKeyword.insert;
        return 0;
    }


}
