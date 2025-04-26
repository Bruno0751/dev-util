package dev.dto;

import java.sql.Timestamp;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 19/04/2025
 */
public class Cliente {
    
    private String name;
    private int oldYear;
    private double heigth;

    public Cliente() {
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

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    @Override
    public String toString() {
        return "Cliente{" + "name=" + name + ", oldYear=" + oldYear + ", heigth=" + heigth + '}';
    }
    
}
