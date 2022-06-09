package com.google.sps.servlets;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

  public ArrayList<String> getMessages(){
    ArrayList<String> messages = new ArrayList<String>();
    messages.add("Did you click me?");
    messages.add("Click me again, I dare you!");
    messages.add("Why would you click me?!");
    return messages;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");
    Gson gson = new Gson();
    ArrayList<String> messages = getMessages();
    String json = gson.toJson(messages);
    response.getWriter().println(json);
  }
}
