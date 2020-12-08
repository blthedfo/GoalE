package edu.csce4623.lukelmiller.goale.categoryProgress;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import edu.csce4623.lukelmiller.goale.R;
import edu.csce4623.lukelmiller.goale.data.GoalItem;
import edu.csce4623.lukelmiller.goale.data.GoalItemRepository;
import edu.csce4623.lukelmiller.goale.data.GoalListDataSource;
import util.AppExecutors;

public class categoryProgressView extends AppCompatActivity {

   List<GoalItem> goalItemList;
    private static final int CREATE_GOAL_REQUEST = 0;
    private GoalItemRepository repoDB;
    List<GoalItem> healthItems = Collections.emptyList();
    List<GoalItem> financeItems = Collections.emptyList();
    List<GoalItem> quantityItems = Collections.emptyList();
    List<GoalItem> qualityItems = Collections.emptyList();
    Integer healthProgress = 0;
    Integer financeProgress = 0;
    Integer qualityProgress = 0;
    Integer quantityProgress = 0;
    TextView tvHealth = findViewById(R.id.healthText);
    TextView tvFinance = findViewById(R.id.financialText);
    TextView tvQuality = findViewById(R.id.qualityText);
    TextView tvQuantity = findViewById(R.id.quantityText);



//    //public categoryProgressView() {
//        super(R.layout.category_progress_layout);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_progress_layout);

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
        Integer totalProgress = 0;
        Integer avgProgress = 0;
        for(int i = 0; i < healthItems.size(); i++){
            healthProgress = 0;
            if(healthItems.get(i).getCurrent()< healthItems.get(i).getEnd() && healthItems.get(i).getEnd()!=0){
                healthProgress += (int) Math.ceil(healthItems.get(i).getCurrent()/healthItems.get(i).getEnd()*100);
            }else if(healthItems.get(i).getCurrent()< healthItems.get(i).getEnd() && healthItems.get(i).getEnd()==0){
                healthProgress += (int) Math.ceil(healthItems.get(i).getCurrent()/0.00000001*100);
            }else if(healthItems.get(i).getEnd() < healthItems.get(i).getCurrent() && healthItems.get(i).getCurrent()!=0){
                healthProgress += (int) Math.ceil(healthItems.get(i).getEnd()/healthItems.get(i).getCurrent()*100);
            }else if(healthItems.get(i).getEnd() < healthItems.get(i).getCurrent() && healthItems.get(i).getCurrent()==0){
                healthProgress += (int) Math.ceil(healthItems.get(i).getEnd()/0.00000001*100);
            }else{
                healthProgress = 0;
            }
            totalProgress += healthProgress;

        }
        avgProgress = totalProgress/healthItems.size();
        tvHealth.setText(avgProgress.toString() + "%");

    }

    private void calcQualityProgress() {
    }

    private void calcFinanceProgress() {
    }

    private void calcHealthProgress() {


    }


}
