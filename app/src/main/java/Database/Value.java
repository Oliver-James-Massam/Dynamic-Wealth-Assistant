package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Value.class,
                parentColumns = "categoryID",
                childColumns = "categoryID"),
        @ForeignKey(entity = Account.class,
                parentColumns = "accID",
                childColumns = "accID"),
        @ForeignKey(entity = Value.class,
                parentColumns = "recurringID",
                childColumns = "recurringID")})
public class Value {
    @PrimaryKey(autoGenerate = true)
    private int valueID;

    @ColumnInfo(name = "valueType")
    private String valueType;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "transactionDate")
    private Date transactionDate;

    @ColumnInfo(name = "categoryID")
    private int categoryID;

    @ColumnInfo(name = "accID")
    private int accID;

    @ColumnInfo(name = "recurringID")
    private int recurringID;

    public int getValueID() {
        return valueID;
    }

    public void setValueID(int valueID) {
        this.valueID = valueID;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public int getRecurringID() {
        return recurringID;
    }

    public void setRecurringID(int recurringID) {
        this.recurringID = recurringID;
    }
}
