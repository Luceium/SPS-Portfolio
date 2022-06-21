package com.google.sps.apihelper;

import java.util.ArrayList;
import java.util.List;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.sps.data.Comment;

public class DatastoreHelper{
    public static void write(String name, String comment){
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Suggestion");
        FullEntity suggestionEntity =
            Entity.newBuilder(keyFactory.newKey())
                .set("name", name)
                .set("comment", comment)
                .set("timestamp", timestamp)
                .build();
        datastore.put(suggestionEntity);
    }

    public static List<Comment> read(){
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Suggestion").setOrderBy(OrderBy.desc("timestamp")).build();
        QueryResults<Entity> results = datastore.run(query);

        List<Comment> comments = new ArrayList<Comment>();
        while (results.hasNext()) {
            Entity entity = results.next();

            long id = entity.getKey().getId();
            String commentConetent = entity.getString("comment");
            long timestamp = System.currentTimeMillis();

            Comment comment = new Comment(id, commentConetent, timestamp);
            comments.add(comment);
        }
        
        return comments;
    }
}