package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.sps.apihelper.DatastoreHelper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/suggestions")
public class CommentHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //gets information
        String comment = Jsoup.clean(request.getParameter("text-comment"), Safelist.none());
        String name = request.getParameter("commenter-name");

        DatastoreHelper.write(name, comment);
        
        response.sendRedirect("/index.html");
    }
}