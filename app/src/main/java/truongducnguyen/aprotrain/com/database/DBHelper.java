package truongducnguyen.aprotrain.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.room.Database;

import java.util.ArrayList;
import java.util.List;

import truongducnguyen.aprotrain.com.models.Mytask;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "my_application_db";
    private SQLiteDatabase database;
    private static DBHelper instance;

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(sqLiteDatabase);
    }

    //Contact Table
    private static final String TABLE_NOTE = "table_note";
    //columns name
    private static final String TASK_ID = "task_id";
    private static final String TASK_TITLE = "task_title";
    private static final String TASK_CONTACT = "task_contact";
    private static final String TASK_IMPORTANT = "task_important";

    private static final String CREATE_TABLE_NOTE = "CREATE TABLE " + TABLE_NOTE
            + "(" + TASK_ID + " INT DEFAULT 0," + TASK_TITLE + " TEXT, " + TASK_CONTACT + " TEXT, "
            + TASK_IMPORTANT + " BOOLEAN )";

    public void addTask(Mytask mytask) {
        ContentValues values = new ContentValues();
        values.put(TASK_ID, mytask.getTaskId());
        values.put(TASK_TITLE, mytask.getTaskTitle());
        values.put(TASK_CONTACT, mytask.getTaskContent());
        values.put(TASK_IMPORTANT, mytask.getImportant());

        this.database.insert(TABLE_NOTE, null, values);
        Log.e("Log: ", "row inserted, ID = " + values);
    }

    public void updateTask(Mytask mytask) {
        ContentValues values = new ContentValues();
        values.put(TASK_TITLE, mytask.getTaskTitle());
        values.put(TASK_CONTACT, mytask.getTaskContent());

        this.database.update(TABLE_NOTE, values,
                TASK_ID + " = " + mytask.getTaskId(), null);
    }

    public List<Mytask> getAllTask() {
        List<Mytask> note = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NOTE;
        Cursor cursor = this.database.rawQuery(query, null);

        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Mytask mytask = new Mytask();
                mytask.setTaskId(cursor.getInt(0));
                mytask.setTaskTitle(cursor.getString(1));
                mytask.setTaskContent(cursor.getString(2));
                mytask.setImportant(Boolean.valueOf(cursor.getString(2)));

                note.add(mytask);
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        return note;
    }

    public void deleteTask(long contactId) {
        this.database.delete(TABLE_NOTE, TASK_ID + " = " + contactId, null);
    }
}
