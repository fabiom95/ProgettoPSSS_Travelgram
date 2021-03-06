package com.psss.travelgram.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.psss.travelgram.model.entity.Traveler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class ScratchMapViewModel extends ViewModel implements Observer{

    private Traveler traveler;
    private MutableLiveData<Boolean> firstTime;     // la prima volta che viene fatta la query
    private MutableLiveData<ArrayList<String>> visitedCountries;
    private MutableLiveData<ArrayList<String>> wishedCountries;


    // costruttore
    public ScratchMapViewModel(){
        visitedCountries = new MutableLiveData<>();
        wishedCountries  = new MutableLiveData<>();

        firstTime = new MutableLiveData<>();
        firstTime.setValue(true);

        traveler = new Traveler();
        traveler.loadTraveler();
        traveler.addObserver(this);
    }


    // set e get
    public void setFirstTime() { this.firstTime.setValue(false); }
    public void setVisitedCountries() { this.visitedCountries.setValue(traveler.getVisitedCountries()); }
    public void setWishedCountries() { this.wishedCountries.setValue(traveler.getWishedCountries()); }

    public MutableLiveData<Boolean> getFirstTime() { return firstTime; }
    public MutableLiveData<ArrayList<String>> getVisitedCountries() { return visitedCountries; }
    public MutableLiveData<ArrayList<String>> getWishedCountries() { return wishedCountries; }

    public String getUsername(){
        return traveler.getUsername();
    }



    // ricezione notifica dal Subject (il Traveler)
    @Override
    public void update(Observable o, Object arg) {
        setVisitedCountries();
        setWishedCountries();

        // la prima volta serve per caricare tutta la mappa
        if (firstTime.getValue())
            setFirstTime();

    }

}