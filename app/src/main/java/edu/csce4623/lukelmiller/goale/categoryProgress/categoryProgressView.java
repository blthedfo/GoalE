package edu.csce4623.lukelmiller.goale.categoryProgress;

import android.content.Intent;
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
    private int healthProgress;
    private int financeProgress;
    private int qualityProgress;
    private int quantityProgress;
    private TextView tvHealth;
    private TextView tvFinance;
    private TextView tvQuality;
    private TextView tvQuantity;
    private TextView tvOverall;
    private int avgH;
    private int avgF;
    private int avgQuan;
    private int avgQual;



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
        tvOverall = findViewById(R.id.overallText);


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

        if(healthItems.size()!=0){
            avgH = calcHealthProgress();
            tvHealth.setText(avgH + "%");
        }
        else {
            tvHealth.setText("--%");
        }
        if (financeItems.size()!=0){
           avgF = calcFinanceProgress();
           tvFinance.setText(avgF + "%");
        }
        else {
            tvFinance.setText("--%");
        }
        if(qualityItems.size()!=0){
            avgQual = calcQualityProgress();
            tvQuality.setText(avgQual + "%");
        }
        else {
            tvQuality.setText("--%");
        }
        if(quantityItems.size()!=0){
            avgQuan = calcQuantityProgress();
            tvQuantity.setText(avgQuan + "%");
        }
        else {
            tvQuantity.setText("--%");
        }
        calOverallProgress();
    }

    private void calOverallProgress() {
        int totalProgress = 0;
        int avgProgress = 0;
        if(healthItems.size() != 0 && financeItems.size() != 0 && qualityItems.size() != 0 && quantityItems.size() != 0){
            Integer intHealth = Integer.parseInt(tvHealth.toString());
            Integer intFinance = Integer.parseInt(tvFinance.toString());
            Integer intQuality = Integer.parseInt(tvQuality.toString());
            Integer intQuantity = Integer.parseInt(tvQuantity.toString());
            totalProgress = intHealth + intFinance + intQuality + intQuantity;
            avgProgress = totalProgress/4;

        }
        else if(healthItems.size() == 0 && financeItems.size() == 0 && qualityItems.size() == 0 && quantityItems.size() == 0){
            tvOverall.setText("--%");

        }
        else{

            if(healthItems.isEmpty() || financeItems.isEmpty() || quantityItems.isEmpty() || qualityItems.isEmpty() ){
                if(healthItems.isEmpty()){
                    if(financeItems.isEmpty() || quantityItems.isEmpty() || qualityItems.isEmpty()){

                        if(financeItems.isEmpty()){
                            if(quantityItems.isEmpty()|| qualityItems.isEmpty()){

                                if(qualityItems.isEmpty()){
                                    avgProgress = avgQuan;
                                }
                                if(quantityItems.isEmpty()){
                                    avgProgress = avgQual;
                                }
                            }
                            else{
                                totalProgress = avgQual + avgQuan;
                                avgProgress = totalProgress/2;
                            }
                        }
                        else if(quantityItems.isEmpty()){
                            if(qualityItems.isEmpty()){
                                avgProgress = avgF;
                            }
                            else{
                                totalProgress =  avgF + avgQual;
                                avgProgress = totalProgress/2;
                            }
                        }
                        else{
                            if(quantityItems.isEmpty()){
                                avgProgress = avgF;
                            }
                            else{
                                totalProgress =  avgF+ avgQuan;
                                avgProgress = totalProgress/2;
                            }
                        }
                    }
                    else{
                        totalProgress = avgF + avgQual + avgQuan;
                        avgProgress = totalProgress/3;
                    }
                }
                else if(financeItems.isEmpty()){

                    if(quantityItems.isEmpty()|| qualityItems.isEmpty()){

                        if(qualityItems.isEmpty()){
                            avgProgress = avgQuan;
                        }
                        if(quantityItems.isEmpty()){
                            avgProgress = avgQual;
                        }
                    }
                    else{
                        totalProgress = avgH + avgQuan + avgQual;
                        avgProgress = totalProgress/3;
                    }
                }
                else{

                    if(quantityItems.isEmpty()){
                        if(qualityItems.isEmpty()){
                            totalProgress = avgF + avgH;
                            avgProgress = totalProgress/2;
                        }
                        else{
                            totalProgress =  avgF + avgQual + avgH;
                            avgProgress = totalProgress/3;
                        }
                    }
                    else{
                        if(qualityItems.isEmpty()){
                            totalProgress = avgF + avgH + avgQuan;
                            avgProgress = totalProgress/3;
                        }
                    }
                }
            }
        }

        tvOverall.setText(Integer.toString(avgProgress) + "%");
    }


    private int calcQuantityProgress() {
        int totalProgress = 0;
        int avgProgress = 0;

        for (int i = 0; i < quantityItems.size(); i++) {
            totalProgress += calcProgress(quantityItems.get(i));
        }
        avgProgress = totalProgress / quantityItems.size();
        return avgProgress;
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

    private int calcQualityProgress() {
        int totalProgress = 0;
        int avgProgress = 0;
        for (int i = 0; i < qualityItems.size(); i++) {
            totalProgress += calcProgress(qualityItems.get(i));
        }
        avgProgress = totalProgress / qualityItems.size();
        return avgProgress;
    }

    private int calcFinanceProgress() {
        int totalProgress = 0;
        int avgProgress = 0;
        for (int i = 0; i < financeItems.size(); i++) {
            totalProgress += calcProgress(financeItems.get(i));
        }
        avgProgress = totalProgress / financeItems.size();
        return avgProgress;
    }

    private int calcHealthProgress() {
        int totalProgress = 0;
        int avgProgress = 0;

        for (int i = 0; i < healthItems.size(); i++) {
            totalProgress += calcProgress(healthItems.get(i));
        }
        avgProgress = totalProgress / healthItems.size();
        return avgProgress;
    }


}
