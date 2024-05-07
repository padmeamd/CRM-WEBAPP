package controllers;

import db.DBManager;
import entity.Mark;
import entity.Student;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student-progress")
public class StudentProgressController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 - достаем студента по id(придет с запросом)
        // 2 - достаем активные семестры (для выпадающего списка)
        // 3 - определяем, какой семестр сейчас выбрать
        // если мы перешли с главной стр, то выбранным является Первый из пункта 2
        // если мы нажали на кнопку ВЫБРАТьмы ДОЛЖНЫ ОЖИДАТЬ id выбранного семестра
        // 4 - вытягиваем оценки относительно выбранного семестра
        // 5 - посчитать средний балл (из (массива) пункта 4)
        // все переменные отправить на отображение JSP в виде атрибутов
//1
        String idStud = req.getParameter("hiddenIdToProgress");
        Student student = DBManager.getStudentById(idStud);
        req.setAttribute("student", student);
//2
        List<Term> terms = DBManager.getAllActiveTerms();
        req.setAttribute("terms", terms);

        // 3
        Term selectedTerm = null;

        // нам передали айди выбранного?
        if (req.getParameter("idSelectedTerm") != null) {
            // вытягивать семестр с базы по id
            selectedTerm = DBManager.getTermById(req.getParameter("idSelectedTerm"));
        } else {
            // вытягивать из всех активных первого
            selectedTerm = terms.get(0);

        }
        req.setAttribute("selectedTerm", selectedTerm);

        //4
        List<Mark> marks = DBManager.getMarksByStudentAndTerm(idStud, selectedTerm.getId());
        req.setAttribute("marks", marks);

        //5
        double sumMarks = 0;
        for (Mark m : marks) {
            sumMarks = sumMarks + m.getMark();
        }
        double average = sumMarks / marks.size();
        req.setAttribute("average", average);
        req.getRequestDispatcher("WEB-INF/student-progress.jsp").forward(req, resp);
    }


}
