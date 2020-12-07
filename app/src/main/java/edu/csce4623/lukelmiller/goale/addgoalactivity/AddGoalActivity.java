package edu.csce4623.lukelmiller.goale.addgoalactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.R.layout;
import edu.csce4623.lukelmiller.goale.data.GoalItem;
import edu.csce4623.lukelmiller.goale.editgoalactivity.EditGoalActivity;
import edu.csce4623.lukelmiller.goale.goalListActivity.GoalListContract;

public class AddGoalActivity extends AppCompatActivity {

    GoalItem goal;
    Button btnHealth;
    Button btnFinancial;
    Button btnQuality;
    Button btnQuantity;
    private static final int CREATE_GOAL_REQUEST = 0;
    private GoalListContract.Presenter presenter;

    public AddGoalActivity() {
        super(layout.activity_add_goal);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_add_goal);

        btnHealth = findViewById(R.id.btnHealth);
        btnFinancial = findViewById(R.id.btnFinancial);
        btnQuality = findViewById(R.id.btnQuality);
        btnQuantity = findViewById(R.id.btnQuantity);
        Intent callingIntent = getIntent();

        //Intent EditIntent = new Intent(AddGoalActivity.this, EditGoalActivity.class);

        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callingIntent.hasExtra("GoalItem")){
                    goal = (GoalItem) callingIntent.getSerializableExtra("GoalItem");
                    goal.setCategory(1);
                    Intent editIntent = new Intent(view.getContext(), EditGoalActivity.class);
                    editIntent.putExtra("GoalItem",goal);
                    startActivity(editIntent);
                }
                else{
                    Log.d("error","No Goal Found");
                }
                finish();
            }
        });
        btnFinancial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callingIntent.hasExtra("GoalItem")){
                    goal = (GoalItem) callingIntent.getSerializableExtra("GoalItem");
                    goal.setCategory(2);
                    Intent editIntent = new Intent(view.getContext(), EditGoalActivity.class);
                    editIntent.putExtra("GoalItem",goal);
                    startActivity(editIntent);
                }
                else{
                    Log.d("error","No Goal Found");
                }
                finish();
            }
        });
        btnQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callingIntent.hasExtra("GoalItem")){
                    goal = (GoalItem) callingIntent.getSerializableExtra("GoalItem");
                    goal.setCategory(3);
                    Intent editIntent = new Intent(view.getContext(), EditGoalActivity.class);
                    editIntent.putExtra("GoalItem",goal);
                    startActivity(editIntent);
                }
                else{
                    Log.d("error","No Goal Found");
                }
                finish();
            }
        });
        btnQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callingIntent.hasExtra("GoalItem")){
                    goal = (GoalItem) callingIntent.getSerializableExtra("GoalItem");
                    goal.setCategory(4);
                    Intent editIntent = new Intent(view.getContext(), EditGoalActivity.class);
                    editIntent.putExtra("GoalItem",goal);
                    startActivity(editIntent);
                }
                else{
                    Log.d("error","No Goal Found");
                }
                finish();
            }
        });
    }

}

