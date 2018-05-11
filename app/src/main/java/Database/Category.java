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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }
}
