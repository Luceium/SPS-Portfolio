package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/suggestions")
public class CommentHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //gets comment text
        String comment = Jsoup.clean(request.getParameter("text-comment"), Safelist.none());
        String name = request.getParameter("commenter-name");
        long timestamp = System.currentTimeMillis();

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Suggestion");
        FullEntity suggestionEntity =
            Entity.newBuilder(keyFactory.newKey())
                .set("name", name)
                .set("comment", comment)
                .set("timestamp", timestamp)
                .build();
        datastore.put(suggestionEntity);
        
        response.sendRedirect("/index.html");
    }
}