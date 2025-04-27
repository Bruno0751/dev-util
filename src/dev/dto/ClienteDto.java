package dev.dto;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 19/04/2025
 */
public class ClienteDto {
    
    private String id;
    private String name;
    private int oldYear;
    private double height;
    private String dateTime;

    public ClienteDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOldYear() {
        return oldYear;
    }

    public void setOldYear(int oldYear) {
        this.oldYear = oldYear;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ClienteDto{" + "id=" + id + ", name=" + name + ", oldYear=" + oldYear + ", height=" + height + ", dateTime=" + dateTime + '}';
    }
}
