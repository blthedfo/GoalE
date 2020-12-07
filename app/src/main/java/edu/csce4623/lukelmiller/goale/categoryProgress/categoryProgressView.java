package edu.csce4623.lukelmiller.goale.categoryProgress;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.data.GoalItem;
import edu.csce4623.lukelmiller.goale.goalListActivity.GoalListContract;

public class categoryProgressView extends AppCompatActivity {

   List<GoalItem> goalItemList;
    private static final int CREATE_GOAL_REQUEST = 0;
    private GoalListContract.Presenter presenter;

    public categoryProgressView() {
        super(R.layout.category_progress_layout);
    }
}
