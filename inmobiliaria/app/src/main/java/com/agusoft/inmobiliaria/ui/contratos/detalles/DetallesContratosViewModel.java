package com.agusoft.inmobiliaria.ui.contratos.detalles;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.inmobiliaria.modelo.Contrato;
import com.agusoft.inmobiliaria.modelo.Inmueble;
import com.agusoft.inmobiliaria.request.ApiClient;

public class DetallesContratosViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contratoM;
    public DetallesContratosViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Contrato> getContratoM() {
        if(contratoM == null){
            contratoM = new MutableLiveData<>();
        }
        return contratoM;
    }

    public void obtenerInformacionContrato(Bundle bundle){
        Inmueble inmueble= (Inmueble) bundle.get("inmueble");
        Contrato contrato = ApiClient.getApi().obtenerContratoVigente(inmueble);
        contratoM.setValue(contrato);
    }
}