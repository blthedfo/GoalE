package edu.csce4623.lukelmiller.goale.categoryProgress;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.data.GoalItem;
import edu.csce4623.lukelmiller.goale.data.GoalItemRepository;
import edu.csce4623.lukelmiller.goale.data.GoalListDataSource;
import util.AppExecutors;

public class categoryProgressView extends AppCompatActivity {

    private List<GoalItem> goalItemList;

    private GoalItemRepository repoDB;
    private List<GoalItem> healthItems;
    private List<GoalItem> financeItems;
    private List<GoalItem> quantityItems;
    private List<GoalItem> qualityItems;
    private Integer healthProgress;
    private Integer financeProgress;
    private Integer qualityProgress;
    private Integer quantityProgress;
    private TextView tvHealth;
    private TextView tvFinance;
    private TextView tvQuality;
    private TextView tvQuantity;



//    //public categoryProgressView() {
//        super(R.layout.category_progress_layout);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_progress_layout);



        healthItems =  new ArrayList<>();
        financeItems =  new ArrayList<>();
        quantityItems =  new ArrayList<>();
        qualityItems =  new ArrayList<>();
        healthProgress = 0;
        financeProgress = 0;
        qualityProgress = 0;
        quantityProgress = 0;
        tvHealth = findViewById(R.id.healthText);
        tvFinance = findViewById(R.id.financialText);
        tvQuality = findViewById(R.id.qualityText);
        tvQuantity = findViewById(R.id.quantityText);


        repoDB = GoalItemRepository.getInstance(new AppExecutors(),this);
        repoDB.getGoalItems(new GoalListDataSource.LoadGoalItemsCallback(){
            @Override
            public void onGoalItemsLoaded(List<GoalItem> goalItems) {
              goalItemList = goalItems;
              loadItem();
            }

            @Override
            public void onDataNotAvailable() {

            }
        } );


    }

    public void loadItem(){
        goalItemList.get(0);

        for(int i =0; i < goalItemList.size(); i++){
            //Category 1 is health
            if(goalItemList.get(i).getCategory() == 1){

                healthItems.add(goalItemList.get(i));
            }
            if(goalItemList.get(i).getCategory() == 2){

                financeItems.add(goalItemList.get(i));
            }
            if(goalItemList.get(i).getCategory() == 3){

                quantityItems.add(goalItemList.get(i));
            }
            if(goalItemList.get(i).getCategory() == 4){

                qualityItems.add(goalItemList.get(i));
            }
        }

        calcHealthProgress();
        calcFinanceProgress();
        calcQualityProgress();
        calcQuantityProgress();

    }



    private void calcQuantityProgress() {
        int totalProgress = 0;
        int avgProgress = 0;
        for(int i = 0; i < healthItems.size(); i++){
            totalProgress += calcProgress(healthItems.get(i));
        }
        avgProgress = totalProgress/healthItems.size();
        tvHealth.setText(Integer.toString(avgProgress) + "%");
    }

    private int calcProgress(GoalItem goalItem){
        int progress = 0;
        if(goalItem.getCurrent() == goalItem.getEnd() && goalItem.getEnd()!=0){
            progress = 100;
        }else if(goalItem.getCurrent()< goalItem.getEnd() && goalItem.getEnd()!=0){
            progress = (int) Math.ceil(goalItem.getCurrent()/goalItem.getEnd()*100);
        }else if(goalItem.getCurrent()< goalItem.getEnd() && goalItem.getEnd()==0){
            progress = (int) Math.ceil(goalItem.getCurrent()/0.00000001*100);
        }else if(goalItem.getEnd() < goalItem.getCurrent() && goalItem.getCurrent()!=0){
            progress = (int) Math.ceil(goalItem.getEnd()/goalItem.getCurrent()*100);
        }else if(goalItem.getEnd() < goalItem.getCurrent() && goalItem.getCurrent()==0){
            progress = (int) Math.ceil(goalItem.getEnd()/0.00000001*100);
        }else{
            progress = 0;
        }
        if(progress>=100){
            progress = 100;
        }
        return progress;
    }

    private void calcQualityProgress() {
    }

    private void calcFinanceProgress() {
    }

    private void calcHealthProgress() {


    }


}
