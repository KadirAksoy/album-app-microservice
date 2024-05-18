package com.kadiraksoy.noteservice.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class ESUtil {

    public static Query createMatchAllQuery() {
        return Query.of(q -> q.matchAll(new MatchAllQuery.Builder().build()));
    }


    public static Supplier<Query> buildQueryForFieldAndValue(String fieldName, String searchValue) {
        return () -> Query.of(q -> q.match(buildMatchQueryForFieldAndValue(fieldName, searchValue)));
    }


    public static MatchQuery buildMatchQueryForFieldAndValue(String fieldName, String searchValue) {
        return new MatchQuery.Builder()
                .field(fieldName)
                .query(searchValue)
                .build();
    }

    public static BoolQuery boolQuery(String key1, String value1, String key2, String value2) {
        return new BoolQuery.Builder()
                .filter(termQuery(key1.toString(), value1))
                .must(termQuery(key2.toString(), value2))
                .build();
    }


    public static Query termQuery(String field, String value) {
        return Query.of(q -> q.term(new TermQuery.Builder()
                .field(field)
                .value(value)
                .build()));
    }


    public static Query matchQuery(String field, String value) {
        return Query.of(q -> q.match(new MatchQuery.Builder()
                .field(field)
                .query(value)
                .build()));
    }


    // Otomatik tamamlama için bir sorgu oluşturur.
    // Verilen bir isme (name) göre arama yapmak üzere özelleştirilmiş bir analizör kullanır.
    public static Query buildAutoSuggestQuery(String name) {
        return Query.of(q -> q.match(createAutoSuggestMatchQuery(name)));
    }
    // Otomatik tamamlama için belirli bir isim için bir eşleştirme sorgusu oluşturan yardımcı bir metod.
    // Özel bir analizör kullanarak eşleşmeyi gerçekleştirir.
    public static MatchQuery createAutoSuggestMatchQuery(String name) {
        return new MatchQuery.Builder()
                .field("name")
                .query(name)
                .analyzer("custom_index")
                .build();
    }
}
