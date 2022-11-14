package truongducnguyen.aprotrain.com.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import truongducnguyen.aprotrain.com.R;
import truongducnguyen.aprotrain.com.models.Mytask;

public class TaskAdapter extends BaseAdapter {
    private final List<Mytask> mytasks;

    public TaskAdapter(List<Mytask> mytasks) {
        this.mytasks = mytasks;
    }

    @Override
    public int getCount() {//Cho listview biết số lượng dòng sẽ được hiển thị
        return mytasks.size();
    }

    @Override
    public Object getItem(int i) {//Cho listview biết object data được hiển thị ở vị trí thứ i là object nào
        return mytasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_view, viewGroup, false);
        }

        TextView txtTitle = view.findViewById(R.id.text_title);
        TextView txtContent = view.findViewById(R.id.text_content);
        Mytask mytask = mytasks.get(i);
        txtTitle.setText(mytask.getTaskTitle());
        txtContent.setText(mytask.getTaskContent());

        return view;
    }
}

