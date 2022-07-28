package com.example.CardiacRecorder;

import java.util.ArrayList;

public class RecordList {

    public static ArrayList<ModelClass> mcl= new ArrayList<>();


    public void addRecord(ModelClass modelClass){
        if(mcl.contains(modelClass)){
            throw new IllegalArgumentException();
        }
        mcl.add(modelClass);


    }



    public void deleteRecord(int position){


        if (position>=0 && position<mcl.size()) {
            mcl.remove(position);
        }
        else {
            throw new IllegalArgumentException() ;
        }


    }
    public int  count(){
        return  mcl.size();
    }

}