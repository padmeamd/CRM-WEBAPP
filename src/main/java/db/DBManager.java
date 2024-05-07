package db;

import entity.Discipline;
import entity.Mark;
import entity.Student;
import entity.Term;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    public static List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<Student>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student where status = 1 ;");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroupe(rs.getString("groupe"));
                student.setDate(rs.getDate("date"));
                students.add(student);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;

    }

    public static List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<Term>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from term where status = '1'");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setDuration(rs.getString("duration"));
                term.setTerm(rs.getString("term"));
                terms.add(term);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return terms;

    }

    public static void modifyStudent(String surname, String name, String groupe, String date, String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE`student` SET `surname` = '" + surname + "', `name` = '" + name + "', `groupe` = '" + groupe + "', `date` = '" + date + "' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createNewStudent(String surname, String name, String groupe, String date) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `student` (`surname`, `name`, `groupe`, `date`) VALUES ('" + surname + "', '" + name + "', '" + groupe + "', '" + date + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Student getStudentById(String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student where status = '1' and id = " + id);

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroupe(rs.getString("groupe"));
                student.setDate(rs.getDate("date"));
                return student;

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean canLogin(String login, String password, String role) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_role as ur\n" +
                    "left join user as u on ur.id_user = u.id\n" +
                    "where u.user = '" + login + "' and u.password = '" + password + "' and ur.id_role = '" + role + "'");

            while (rs.next()) {
                return true;

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void deleteStudent(String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Term getTermById(String id) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from term where status = '1' and id = '" + id + "'");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setDuration(rs.getString("duration"));
                term.setTerm(rs.getString("term"));
                return term;

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    public static List<Mark> getMarksByStudentAndTerm(String idStud, int idTerm) {
        ArrayList<Mark> marks = new ArrayList<Mark>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.name, m.mark FROM mark as m\n" +
                    "left join term_discipline as td on m.id_term_disc = td.id\n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "where m.id_student = '" + idStud + "' and td.id_term = '" + idTerm + "' and d.status = '1'");
            while (rs.next()) {
                Mark mark = new Mark();
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("name"));
                mark.setDiscipline(discipline);
                mark.setMark(rs.getInt("mark"));

                marks.add(mark);
            }

            if (marks.size() == 0) {
                rs = stmt.executeQuery("SELECT * FROM term_discipline as td\n" +
                        "left join discipline as d on td.id_discipline = d.id\n" +
                        "where td.id_term = '" + idTerm + "' and d.status = '1'");
                while (rs.next()) {
                    Mark mark = new Mark();
                    Discipline discipline = new Discipline();
                    discipline.setDiscipline(rs.getString("name"));
                    mark.setDiscipline(discipline);
                    mark.setMark(-1);

                    marks.add(mark);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return marks;
    }
}



