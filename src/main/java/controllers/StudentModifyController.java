package controllers;


import db.DBManager;
import entity.Student;

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

@WebServlet(name = "StudentModifyController", urlPatterns = "/student-modify")
public class StudentModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStudToModify = req.getParameter("hiddenModifyId");
        Student student = DBManager.getStudentById(idStudToModify);

        req.setAttribute("student",student);
        req.getRequestDispatcher("WEB-INF/student-modify.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String groupe = req.getParameter("groupe");
        String date = req.getParameter("date");
        String idStudToModify = req.getParameter("idStudToModify");

        surname = surname.replaceAll(" ","");
        name = name.replaceAll(" ","");
        groupe = groupe.replaceAll(" ","");
        date = date.replaceAll(" ","");

        if (surname == null || name == null || groupe == null || date == null || surname.equals("") || name.equals("") || groupe.equals("") || date.equals("")){
            req.setAttribute("message", "1");
            req.getRequestDispatcher("WEB-INF/student-modify.jsp").forward(req,resp);

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

        DBManager.modifyStudent(surname,name,groupe,dateToDatabase,idStudToModify);

        resp.sendRedirect("/students");
    }
}
