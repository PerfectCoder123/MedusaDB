package MedussaDatabase.MedusaEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QueryTokenizer {
    private String query;

    QueryTokenizer(String query) {
        this.query = query;
    }

    public List<String> generateToken() {

        List<String> responseList = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(query);
        while (tokens.hasMoreElements()) {
            responseList.add(tokens.nextToken());
        }
        return responseList;
    }
}