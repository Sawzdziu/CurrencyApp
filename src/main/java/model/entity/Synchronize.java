package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Synchronize implements Serializable{
    private int id;
    private int currencyId;
    private double value;
    private Date date;
    private Currency currencyByCurrencyId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CURRENCY_ID", insertable = false, updatable = false)
    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Basic
    @Column(name = "VALUE")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Synchronize that = (Synchronize) o;

        if (id != that.id) return false;
        if (currencyId != that.currencyId) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + currencyId;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID", nullable = false)
    public Currency getCurrencyByCurrencyId() {
        return currencyByCurrencyId;
    }

    public void setCurrencyByCurrencyId(Currency currencyByCurrencyId) {
        this.currencyByCurrencyId = currencyByCurrencyId;
    }
}
