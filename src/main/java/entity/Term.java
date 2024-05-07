package entity;

import java.util.ArrayList;
import java.util.List;

public class Term {
    private int id;
    private String term;
    private int status;
    private String duration;
    private List<Discipline> disciplines = new ArrayList<Discipline>();

    public Term() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term1 = (Term) o;

        if (id != term1.id) return false;
        if (status != term1.status) return false;
        if (term != null ? !term.equals(term1.term) : term1.term != null) return false;
        if (duration != null ? !duration.equals(term1.duration) : term1.duration != null) return false;
        return disciplines != null ? disciplines.equals(term1.disciplines) : term1.disciplines == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (disciplines != null ? disciplines.hashCode() : 0);
        return result;
    }
}
