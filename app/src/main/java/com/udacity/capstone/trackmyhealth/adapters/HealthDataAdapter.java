package com.udacity.capstone.trackmyhealth.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.capstone.trackmyhealth.R;
import com.udacity.capstone.trackmyhealth.database.AppHealthDataDatabase;
import com.udacity.capstone.trackmyhealth.database.HealthData;

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
    public HealthDataAdapter.HealthDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.health_data_item, viewGroup, false);
        return new HealthDataAdapter.HealthDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthDataAdapter.HealthDataViewHolder healthDataViewHolder, int i) {
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
        TextView docName, visitDate, a1c, bloodsugar, triglycerides, weight, ldl, hdl;
        ImageView editImage;
        AppHealthDataDatabase mDb;

        HealthDataViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppHealthDataDatabase.getInstance(context);
          //  docName = itemView.findViewById(R.id.doc_name);
            visitDate = itemView.findViewById(R.id.date_of_visit);
            a1c = itemView.findViewById(R.id.a1c_value);
            bloodsugar = itemView.findViewById(R.id.bloodsugar_value);
            triglycerides = itemView.findViewById(R.id.trig_value);
            weight = itemView.findViewById(R.id.weight_value);
            ldl = itemView.findViewById(R.id.ldl_value);
            hdl = itemView.findViewById(R.id.hdl_value);

//            editImage = itemView.findViewById(R.id.edit_Image);
//            editImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int elementId = mHealthDataList.get(getAdapterPosition()).getId();
//                    Intent i = new Intent(context, MedicationEditActivity.class);
//                    i.putExtra(Constants.UPDATE_Medication_Id, elementId);
//                    context.startActivity(i);
//                }
//            });
//        }
    }
}
}
