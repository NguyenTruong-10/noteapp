package truongducnguyen.aprotrain.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import truongducnguyen.aprotrain.com.adapter.TaskAdapter;
import truongducnguyen.aprotrain.com.database.DBHelper;
import truongducnguyen.aprotrain.com.models.Mytask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button btnAddNewContact;
    ListView listView;
    TaskAdapter taskAdapter;
    List<Mytask> mytasks;
    DBHelper database;
    private EditText edt_title;
    private EditText edt_content;
    private CheckBox cb_important;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configView();
        configListView();
        addListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mytasks.size() == 0) {
            mytasks.addAll(database.getAllTask());
            taskAdapter.notifyDataSetChanged();
        }
    }

    private void configView() {
        btnAddNewContact = findViewById(R.id.btn_add_note);
        listView = findViewById(R.id.list_view_contacts);
        edt_content = findViewById(R.id.edt_content);
        edt_title = findViewById(R.id.edt_title);
        cb_important = findViewById(R.id.checkbox);

        database = (DBHelper) DBHelper.getInstance(this);
    }
    private void configListView() {
        mytasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(mytasks);
        listView.setAdapter(taskAdapter);
    }

    private void addListener() {
        btnAddNewContact.setOnClickListener(view -> {
            String title = edt_title.getText().toString();
            String content = edt_content.getText().toString();
            Boolean iportant = cb_important.isChecked();
            if (!title.isEmpty() && !content.isEmpty()) {
                Integer id = Math.toIntExact(Calendar.getInstance().getTimeInMillis() / 1000);
                Mytask taskModel = new Mytask();
                taskModel.setTaskId(id);
                taskModel.setTaskTitle(title);
                taskModel.setTaskContent(content);
                taskModel.setImportant(iportant);
                DBHelper database = (DBHelper) DBHelper.getInstance(MainActivity.this);
                database.addTask(taskModel);

                mytasks.add(taskModel);
                taskAdapter.notifyDataSetChanged();
                edt_title.setText("");
                edt_content.setText("");
            }else{
                Toast.makeText(MainActivity.this,
                        "Need to fill in all the information", Toast.LENGTH_SHORT).show();
            }

        });
    }
}