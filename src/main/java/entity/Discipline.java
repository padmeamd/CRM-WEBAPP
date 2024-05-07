package entity;

public class Discipline {
    private int id;
    private String discipline;
    private int status;

    public Discipline() {
    }

    public Discipline(int id, String discipline, int status) {
        this.id = id;
        this.discipline = discipline;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
