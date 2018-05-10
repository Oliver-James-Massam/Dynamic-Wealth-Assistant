package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "accID",
        childColumns = "accID"))

public class History {
    @PrimaryKey(autoGenerate = true)
    private int historyID;

    @ColumnInfo(name = "accID")
    private int accID;

    @ColumnInfo(name = "balanceStart")
    private String balanceStart;

    @ColumnInfo(name = "balanceEnd")
    private String balanceEnd;

    @ColumnInfo(name = "historyStartDate")
    private String historyStartDate;
    
    @ColumnInfo(name = "historyEndDate")
    private String historyEndDate;
}
