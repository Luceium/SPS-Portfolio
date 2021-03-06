package com.google.sps.servlets;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.IOException;
import java.util.HashSet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/translate")
public class TranslateAPIServlet extends HttpServlet{

    private static HashSet<String> ACCEPTED_LANGUAGES = new HashSet<String>();

    public TranslateAPIServlet(){
        ACCEPTED_LANGUAGES.add("en");
        ACCEPTED_LANGUAGES.add("es");
        ACCEPTED_LANGUAGES.add("fr");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String langCode = request.getParameter("langCode");
        //Stops code from running if there is an invalid language
        if (!ACCEPTED_LANGUAGES.contains(langCode)){
            System.out.println("Language code: " + langCode + " is invalid.");
            //resets the page to english to not destroy the page conentent
            langCode = "en";
        }

        String originalText = request.getParameter("text");
        System.out.println("Page translated too: " + langCode);

        //translate text
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation = translate.translate(originalText, Translate.TranslateOption.targetLanguage(langCode));
        String translatedText = translation.getTranslatedText();

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(translatedText);
    }
}