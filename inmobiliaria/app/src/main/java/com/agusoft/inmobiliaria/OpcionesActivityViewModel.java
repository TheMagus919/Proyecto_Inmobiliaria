package com.agusoft.inmobiliaria;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.inmobiliaria.modelo.Propietario;
import com.agusoft.inmobiliaria.request.ApiClient;

public class OpcionesActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> proM;
    public OpcionesActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }
    public void setInformacion(){
        Propietario pro = ApiClient.getApi().obtenerUsuarioActual();
        proM.setValue(pro);
    }

    public LiveData<Propietario> getProM(){
        if(proM==null){
            proM = new MutableLiveData<>();
        }
        return proM;
    }
}
