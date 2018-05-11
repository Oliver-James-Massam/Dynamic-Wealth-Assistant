package Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {User.class, Account.class, Category.class, History.class, Value.class, Recurring.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class Room_Database extends RoomDatabase{

    public abstract DAO_Database dao_database();

    private static Room_Database INSTANCE;

    public static Room_Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Room_Database.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Room_Database.class, "finance_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
