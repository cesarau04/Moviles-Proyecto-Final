package mx.tec.proyectofinal.beans;

public class Job {

    private String titleJob;
    private String descJob;
    private String date;

    public Job() {
    }

    public String getTitleJob() {
        return titleJob;
    }

    public String getDescJob() {
        return descJob;
    }

    public String getDate() {
        return date;
    }

    public void setTitleJob(String titleJob) {
        this.titleJob = titleJob;
    }

    public void setDescJob(String descJob) {
        this.descJob = descJob;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
