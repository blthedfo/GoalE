package edu.csce4623.lukelmiller.goale.editgoalactivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.data.GoalItem;
import edu.csce4623.lukelmiller.goale.data.GoalItemRepository;
import edu.csce4623.lukelmiller.goale.goalListActivity.FullListActivity;
import util.AppExecutors;


public class EditGoalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    //GoalItemRepository repo;

    public EditGoalActivity(){
        super(R.layout.activity_edit_goal);
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goal);

        etTitle = findViewById(R.id.etTitle);
        etStart = findViewById(R.id.etStart);
        etCurrent = findViewById(R.id.etCurrent);
        etEnd = findViewById(R.id.etEnd);
        units = findViewById(R.id.spinnerUnits);
        units.setOnItemSelectedListener(this);
        etNotes = findViewById(R.id.etNotes);
        btnSave = findViewById(R.id.btnSave);
        Intent callingIntent = getIntent();
        //repo = GoalItemRepository.getInstance(new AppExecutors(),getActivity());
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveGoal();
            }
        });
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                deleteGoal();
            }
        });
        progress = findViewById(R.id.progress);
        tvPercentComplete = findViewById(R.id.tvPercentComplete);
        if(callingIntent.hasExtra("GoalItem")){
            goal = (GoalItem) callingIntent.getSerializableExtra("GoalItem");
            setExistingView();
        }
        else{
            setDefaultView();
        }



    }
    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    private void setDefaultView(){
        goal = new GoalItem();
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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
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
        goal.setNote(etNotes.getText().toString());
        //goal.setCategory();
        if((etStart.getText() == null && etCurrent.getText() == null) || etEnd.getText() == null){
            //Alert Saying Cannot Save Goal Here
//            Intent end = new Intent(getActivity(), FullListActivity.class);
//            startActivity(end);
            Intent end = new Intent();
            end.putExtra("GoalItem", goal);
            setResult(RESULT_CANCELED,end);
            finish();

        }else if(etStart.getText() == null || etCurrent.getText() == null){
            if (etStart.getText()==null){
                goal.setStart(Float.parseFloat(etCurrent.getText().toString()));
            }
            else{
                goal.setCurrent(Float.parseFloat(etStart.getText().toString()));
            }
        }else{

            goal.setStart(Float.parseFloat(etStart.getText().toString()));
            goal.setCurrent(Float.parseFloat(etCurrent.getText().toString()));
            goal.setEnd(Float.parseFloat(etEnd.getText().toString()));
            //goal.setCategory();
        }

//        Intent end = new Intent(getActivity(), FullListActivity.class);
//        startActivity(end);
        Intent end = new Intent();
        end.putExtra("GoalItem", goal);
        setResult(RESULT_OK,end);
        finish();
    }
    private void deleteGoal(){
        //repo.deleteGoalItem(goal);
        Intent end = new Intent();
        end.putExtra("GoalItem", goal);
        end.putExtra("result", 2);
        setResult(RESULT_OK,end);
        finish();
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