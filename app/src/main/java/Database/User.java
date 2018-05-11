package Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userID;

    @ColumnInfo(name = "passCode")
    private int passCode;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPassCode() {
        return passCode;
    }

    public void setPassCode(int passCode) {
        this.passCode = passCode;
    }
}
