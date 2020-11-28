package edu.csce4623.lukelmiller.goale.editgoalactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.data.GoalItem;
import edu.csce4623.lukelmiller.goale.data.GoalItemRepository;


public class EditGoalActivity extends AppCompatActivity {


    GoalItemRepository repo;
    public EditGoalActivity(){
        super(R.layout.activity_edit_goal);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            Bundle bundle = new Bundle();
            Intent callingIntent = getIntent();
            if(callingIntent.hasExtra("GoalItem")){
                GoalItem item = (GoalItem) callingIntent.getSerializableExtra("GoalItem");
                bundle.putSerializable("GoalItem", item);
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .add(R.id.fragEditGoal, EditGoalFragment.class, bundle).commit();
            }
        }
//        setContentView(R.layout.activity_edit_goal);


    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }
}