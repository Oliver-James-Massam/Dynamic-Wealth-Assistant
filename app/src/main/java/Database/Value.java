package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Value.class,
        parentColumns = "categoryID",
        childColumns = "categoryID"))
public class Value {
    @PrimaryKey(autoGenerate = true)
    private String valueID;

    @ColumnInfo(name = "valueType")
    private String valueType;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "transactionDate")
    private String transactionDate;

    @ColumnInfo(name = "categoryID")
    private String categoryID;

    @ColumnInfo(name = "accID")
    private String accID;

    @ColumnInfo(name = "recurringID")
    private String recurringID;
}
