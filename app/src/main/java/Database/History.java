package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "accID",
        childColumns = "accID"))
public class History {
    @PrimaryKey(autoGenerate = true)
    private int historyID;

    @ColumnInfo(name = "accID")
    private int accID;

    @ColumnInfo(name = "balanceStart")
    private double balanceStart;

    @ColumnInfo(name = "balanceEnd")
    private double balanceEnd;

    @ColumnInfo(name = "historyStartDate")
    private Date historyStartDate;
    
    @ColumnInfo(name = "historyEndDate")
    private Date historyEndDate;

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public double getBalanceStart() {
        return balanceStart;
    }

    public void setBalanceStart(double balanceStart) {
        this.balanceStart = balanceStart;
    }

    public double getBalanceEnd() {
        return balanceEnd;
    }

    public void setBalanceEnd(double balanceEnd) {
        this.balanceEnd = balanceEnd;
    }

    public Date getHistoryStartDate() {
        return historyStartDate;
    }

    public void setHistoryStartDate(Date historyStartDate) {
        this.historyStartDate = historyStartDate;
    }

    public Date getHistoryEndDate() {
        return historyEndDate;
    }

    public void setHistoryEndDate(Date historyEndDate) {
        this.historyEndDate = historyEndDate;
    }
}
