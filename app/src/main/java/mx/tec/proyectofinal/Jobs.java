package mx.tec.proyectofinal;

public class Jobs {

    String titlejob;
    String findout;
    String descjob;
    //String keyjob;

    public Jobs() {
    }

    public Jobs(String titlejob, String findout, String descjob) {
        this.titlejob = titlejob;
        this.findout = findout;
        this.descjob = descjob;
    }

    public String getTitlejob() {
        return titlejob;
    }

    public void setTitlejob(String titlejob) {
        this.titlejob = titlejob;
    }

    public String getFindout() {
        return findout;
    }

    public void setFindout(String findout) {
        this.findout = findout;
    }

    public String getDescjob() {
        return descjob;
    }

    public void setDescjob(String descjob) {
        this.descjob = descjob;
    }
}
