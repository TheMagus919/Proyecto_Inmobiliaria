package com.agusoft.inmobiliaria.ui.inquilinos.detalles;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.agusoft.inmobiliaria.modelo.Inmueble;
import com.agusoft.inmobiliaria.modelo.Inquilino;
import com.agusoft.inmobiliaria.request.ApiClient;

public class DetallesInquilinosViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilinoM;
    public DetallesInquilinosViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Inquilino> getInquilinoM() {
        if(inquilinoM == null){
            inquilinoM = new MutableLiveData<>();
        }
        return inquilinoM;
    }

    public void obtenerInformacionInquilino(Bundle bundle){
        Inmueble inmueble= (Inmueble) bundle.get("inmueble");
        Inquilino inquilino = ApiClient.getApi().obtenerInquilino(inmueble);
        inquilinoM.setValue(inquilino);
    }

}