package com.udacity.capstone.trackmyhealth.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.AppHealthDataDatabase;
import com.udacity.capstone.trackmyhealth.database.HealthData;
import com.udacity.capstone.trackmyhealth.ui.GraphActivity;

import java.util.List;

public class HealthDataAdapter extends RecyclerView.Adapter<HealthDataAdapter.HealthDataViewHolder> {

    private Context context;
    private Intent intent;
    private List<HealthData> mHealthDataList;

    public HealthDataAdapter(Context context, Activity activity) {

        this.context = context;
        this.intent = activity.getIntent();
    }

    @NonNull
    @Override
    public HealthDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.health_data_item, viewGroup, false);
        return new HealthDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthDataViewHolder healthDataViewHolder, int i) {
        //healthDataViewHolder.docName.setText(mHealthDataList.get(i).getDocname());
        healthDataViewHolder.visitDate.setText(mHealthDataList.get(i).getDateofvisit());
        healthDataViewHolder.a1c.setText(mHealthDataList.get(i).getA1c());
        healthDataViewHolder.bloodsugar.setText(mHealthDataList.get(i).getBloodsugar());
        healthDataViewHolder.triglycerides.setText(mHealthDataList.get(i).getTriglycerides());
        healthDataViewHolder.weight.setText(mHealthDataList.get(i).getWeight());
        healthDataViewHolder.hdl.setText(mHealthDataList.get(i).getHdl());
        healthDataViewHolder.ldl.setText(mHealthDataList.get(i).getLdl());
    }

    @Override
    public int getItemCount() {
        if (mHealthDataList == null) {
            return 0;
        }
        return mHealthDataList.size();

    }

    public void setTasks(List<HealthData> healthDataList) {
        mHealthDataList = healthDataList;
        notifyDataSetChanged();
    }

    public List<HealthData> getTasks() {
        return mHealthDataList;
    }

    class HealthDataViewHolder extends RecyclerView.ViewHolder {
        TextView docNameKey, visitDateKey, a1cKey, bloodsugarKey, triglyceridesKey, weightKey, ldlKey, hdlKey;
        TextView docName, visitDate, a1c, bloodsugar, triglycerides, weight, ldl, hdl;
        ImageView editImage;
        AppHealthDataDatabase mDb;

        HealthDataViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppHealthDataDatabase.getInstance(context);
          //  docName = itemView.findViewById(R.id.doc_name);
            visitDate = itemView.findViewById(R.id.date_of_visit);

            a1cKey = itemView.findViewById(R.id.a1c_key);
            bloodsugarKey = itemView.findViewById(R.id.bloodsugar_key);
            triglyceridesKey = itemView.findViewById(R.id.trig_key);
            weightKey = itemView.findViewById(R.id.weight_key);
            ldlKey = itemView.findViewById(R.id.ldl_key);
            hdlKey = itemView.findViewById(R.id.hdl_key);


            a1c = itemView.findViewById(R.id.a1c_value);
            bloodsugar = itemView.findViewById(R.id.bloodsugar_value);
            triglycerides = itemView.findViewById(R.id.trig_value);
            weight = itemView.findViewById(R.id.weight_value);
            ldl = itemView.findViewById(R.id.ldl_value);
            hdl = itemView.findViewById(R.id.hdl_value);

            a1cKey.setOnClickListener(v -> {
                Toast.makeText(context, "clicked a1c", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, GraphActivity.class);
                context.startActivity(intent);
            });
    }
}
}
