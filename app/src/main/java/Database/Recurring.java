package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Recurring {
    @PrimaryKey(autoGenerate = true)
    private int recurringID;

    @ColumnInfo(name = "recurrType")
    private String recurrType;

    @ColumnInfo(name = "recurrNum")
    private int recurrNum;

    public int getRecurringID() {
        return recurringID;
    }

    public void setRecurringID(int recurringID) {
        this.recurringID = recurringID;
    }

    public String getRecurrType() {
        return recurrType;
    }

    public void setRecurrType(String recurrType) {
        this.recurrType = recurrType;
    }

    public int getRecurrNum() {
        return recurrNum;
    }

    public void setRecurrNum(int recurrNum) {
        this.recurrNum = recurrNum;
    }
}
