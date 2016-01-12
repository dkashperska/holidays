package kashperska.dto;

import java.util.Date;

public class Holiday implements Comparable<Date>{

    private Date date;
    private String name;

    public Holiday(){

    }

    public Holiday(Date date, String name){
        this.date = date;
        this.name = name;
    }

    public int compareTo(Date o) {
        return this.date.compareTo(o);
    }

    public String toString(){
        return name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
