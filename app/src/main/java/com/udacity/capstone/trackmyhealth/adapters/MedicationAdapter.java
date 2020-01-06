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
import com.udacity.capstone.trackmyhealth.constants.Constants;
import com.udacity.capstone.trackmyhealth.database.AppDatabase;
import com.udacity.capstone.trackmyhealth.database.AppExecutors;
import com.udacity.capstone.trackmyhealth.database.Medication;
import com.udacity.capstone.trackmyhealth.ui.MedicationEditActivity;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder> {

    private Context context;
    private Intent intent;
    private List<Medication> mMedicationList;

    public MedicationAdapter(Context context, Activity activity) {

        this.context = context;
        this.intent = activity.getIntent();
    }

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.medication_item, viewGroup, false);
        return new MedicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationAdapter.MedicationViewHolder medicationViewHolder, int i) {
        medicationViewHolder.name.setText(mMedicationList.get(i).getName());
        medicationViewHolder.dose.setText(mMedicationList.get(i).getDose());
        medicationViewHolder.unit.setText(mMedicationList.get(i).getUnit());
        medicationViewHolder.frequency.setText(mMedicationList.get(i).getFrequency());
    }

    @Override
    public int getItemCount() {
        if (mMedicationList == null) {
            return 0;
        }
        return mMedicationList.size();

    }

    public void setTasks(List<Medication> medicationList) {
        mMedicationList = medicationList;
        notifyDataSetChanged();
    }

    public List<Medication> getTasks() {

        return mMedicationList;
    }

    class MedicationViewHolder extends RecyclerView.ViewHolder {
        TextView name, dose, unit, frequency;
        ImageView editImage, deleteImage;
        AppDatabase mDb;

        MedicationViewHolder(@NonNull final View itemView) {
            super(itemView);
            mDb = AppDatabase.getInstance(context);
            name = itemView.findViewById(R.id.medication_name);
            dose = itemView.findViewById(R.id.dose);
            unit = itemView.findViewById(R.id.unit);
            frequency = itemView.findViewById(R.id.frequency);

            editImage = itemView.findViewById(R.id.edit_Image);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mMedicationList.get(getAdapterPosition()).getId();
                    Intent i = new Intent(context, MedicationEditActivity.class);
                    i.putExtra(Constants.UPDATE_Medication_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
