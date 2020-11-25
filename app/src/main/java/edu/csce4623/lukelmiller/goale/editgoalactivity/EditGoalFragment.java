package edu.csce4623.lukelmiller.goale.editgoalactivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.data.GoalItem;

public class EditGoalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    GoalItem goal;
    EditText etTitle;
    EditText etStart;
    EditText etCurrent;
    EditText etEnd;
    Spinner units;
    EditText etNotes;
    Button btnSave;
    Button btnDelete;
    ProgressBar progress;
    TextView tvPercentComplete;

    public EditGoalFragment(){
        super(R.layout.fragment_edit_goal);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){

        etTitle = view.findViewById(R.id.etTitle);
        etStart = view.findViewById(R.id.etStart);
        etCurrent = view.findViewById(R.id.etCurrent);
        etEnd = view.findViewById(R.id.etEnd);
        units = view.findViewById(R.id.spinnerUnits);
        units.setOnItemSelectedListener(this);
        etNotes = view.findViewById(R.id.etNotes);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveGoal();
            }
        });
        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                deleteGoal();
            }
        });
        progress = view.findViewById(R.id.progress);
        tvPercentComplete = view.findViewById(R.id.tvPercentComplete);
        try {
            if(savedInstanceState.getSerializable("GoalItem")!=null){
                goal = (GoalItem) savedInstanceState.getSerializable("GoalItem");
                setExistingView();
            }
        }catch (Exception e){
            setDefaultView();
        }
    }
    private void setDefaultView(){
        goal.setTitle("Title");
        goal.setCategory(1);
        goal.setCurrent(0);
        goal.setNote("Notes");
        goal.setEnd(0);
        goal.setStart(0);
        goal.setUnit("");
        setExistingView();
    }

    private void setExistingView(){

        etTitle.setText(goal.getTitle());
        etStart.setText(String.valueOf(goal.getStart()));
        etCurrent.setText(String.valueOf(goal.getCurrent()));
        etEnd.setText(String.valueOf(goal.getEnd()));

        Resources res = getResources();
        String[] labels;
        switch (goal.getCategory()){
            case 1:
                labels = res.getStringArray(R.array.health);
                break;
            case 2:
                labels = res.getStringArray(R.array.finance);
                break;
            case 3:
                labels = res.getStringArray(R.array.quantity);
                break;
            case 4:
                labels = res.getStringArray(R.array.quality);
                break;
            default:
                labels = res.getStringArray(R.array.quantity);
                break;
        }
        List<String> list = Arrays.asList(labels);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(dataAdapter);

        etNotes.setText(goal.getNote());

        int percentProgress;
        if(goal.getEnd() > goal.getCurrent()){
            if(goal.getEnd() == 0){
                percentProgress = (int) (goal.getCurrent() / 0.0000001 * 100);
            }
            else{
                percentProgress = (int) (goal.getCurrent() / goal.getEnd() * 100);
            }
        }else{
            if(goal.getCurrent() == 0){
                percentProgress = (int) (goal.getEnd() / 0.0000001 * 100);
            }
            else{
                percentProgress = (int) (goal.getEnd() / goal.getCurrent() * 100);
            }
        }
        progress.setProgress(percentProgress);
        tvPercentComplete.setText((percentProgress+ "%"));



    }


    private void saveGoal(){
        goal.setTitle(etTitle.getText().toString());
        if((etStart == null && etCurrent == null) || etEnd == null){
            //Alert Saying Cannot Save Goal Here
            //finish();

        }
    }
    private void deleteGoal(){

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item =  parent.getItemAtPosition(position).toString();
        goal.setUnit(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
