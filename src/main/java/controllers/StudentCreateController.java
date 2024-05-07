package controllers;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet (name = "StudentCreateController", urlPatterns = "/student-create")

public class StudentCreateController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/student-create.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String groupe = req.getParameter("groupe");
        String date = req.getParameter("date");

        surname = surname.replace(" ","");
        name = name.replace(" ","");
        groupe = groupe.replace(" ","");
        date = date.replace(" ","");

        if (surname == null || name == null || groupe == null || date == null
                || surname.equals("") || name.equals("") || groupe.equals("") || date.equals("")
        ){
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/student-create.jsp").forward(req,resp);

        }

        DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Date stringToDate = null;
        try {
            stringToDate = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateToDatabase = formatter.format(stringToDate);
        DBManager.createNewStudent(surname, name, groupe, dateToDatabase);

        resp.sendRedirect("/students");

    }


}

