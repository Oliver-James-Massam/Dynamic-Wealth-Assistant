package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    @ColumnInfo(name = "categoryName")
    private String categoryName;

    @ColumnInfo(name = "icon")
    private String icon;

    @ColumnInfo(name = "goal")
    private double goal;
}
