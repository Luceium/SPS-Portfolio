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
// import org.jsoup.Jsoup;
// import org.jsoup.safety.Whitelist;

@WebServlet("/suggestions")
public class CommentHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //gets comment text
        String comment = request.getParameter("text-comment");
        String name = request.getParameter("commenter-name");

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Suggestion");
        FullEntity suggestionEntity =
            Entity.newBuilder(keyFactory.newKey())
                .set("name", name)
                .set("comment", comment)
                .build();
        datastore.put(suggestionEntity);
        //log comment for later
        System.out.println("Commenter: " + name);
        System.out.println("Comment: " + comment);

        //build response
        response.setContentType("text/html;");
        response.getWriter().println("Your comment was logged. Thank you, " + name + "!");
        response.sendRedirect("/index.html");
    }
}