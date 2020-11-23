package edu.csce4623.lukelmiller.goale.goalListActivity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import static com.google.common.base.Preconditions.checkNotNull;
import android.os.Bundle;
import android.widget.Button;

import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.data.GoalItemRepository;
import util.AppExecutors;

public class FullListActivity extends AppCompatActivity{


    private GoalListPresenter goalListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_list);

        ListFragment goalListFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fullListFragmentFrame);
        if(goalListFragment == null){
            goalListFragment = ListFragment.newInstance();
            checkNotNull(goalListFragment);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//            transaction.replace(R.id.fullListFragmentFrame, goalListFragment).commit();
            transaction.add(R.id.fullListFragmentFrame, goalListFragment);
            transaction.commit();
        }
        goalListPresenter = new GoalListPresenter(GoalItemRepository.getInstance(new AppExecutors(),getApplicationContext()),goalListFragment);
    }

}
