package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/suggestions")
public class CommentHandlerServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //gets comment text
        String comment = request.getParameter("text-comment");
        String name = request.getParameter("commenter-name");

        //log comment for later
        System.out.println("Commenter: " + name);
        System.out.println("Comment: " + comment);

        //build response
        response.setContentType("text/html;");
        response.getWriter().println("Your comment was logged. Thank you, " + name + "!");
        response.sendRedirect("/index.html");
    }
}