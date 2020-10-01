package com.psss.travelgram.viewmodel;

import android.net.Uri;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.psss.travelgram.model.entity.Memory;
import com.psss.travelgram.model.entity.Traveler;
import com.psss.travelgram.model.repository.TravelerRepository;

import java.util.Observable;
import java.util.Observer;

import static android.app.Activity.RESULT_OK;


public class InsertMemoryViewModel extends ViewModel implements Observer {

    private Memory memory;
    private MutableLiveData<String> taskResult;


    public InsertMemoryViewModel(){
        taskResult = new MutableLiveData<>();
        memory = new Memory();
        memory.addObserver(this);
    }



    // ----------- operazioni sul Live Data -----------

    public LiveData<String> getTaskResult() {return taskResult;}

    public void setTaskResult(String value) {
        taskResult.setValue(value);
    }


    public void insertMemory(int resultCode, Uri uri, AutoCompleteTextView country, EditText description){

        if (resultCode == RESULT_OK) {
            memory.setPlace(country.getText().toString());
            memory.setDescription(description.getText().toString());
            memory.insertMemory(uri);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setTaskResult(arg.toString());    // TODO: controllare se va, e personalizzare il messaggio (eventuale errore)

        Traveler traveler = new Traveler();
        traveler.addVisitedCountry(memory.getPlace());
    }
}
