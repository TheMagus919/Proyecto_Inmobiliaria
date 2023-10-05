package com.agusoft.inmobiliaria.ui.perfil;

import android.app.Application;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.agusoft.inmobiliaria.modelo.Propietario;
import com.agusoft.inmobiliaria.request.ApiClient;
import com.agusoft.inmobiliaria.ui.OpcionesViewModel;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> actualM;
    private MutableLiveData<Boolean> editarM;
    private MutableLiveData<Boolean> guardarM;
    private OpcionesViewModel om;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void obtenerDatos(){
        ApiClient api = ApiClient.getApi();
        Propietario pro = api.obtenerUsuarioActual();
        actualM.setValue(pro);
    }

    public void opcion(String s,Propietario pro){
        if(s.equals("Editar")){
            editarM.setValue(true);
        }else if(s.equals("Guardar")){
            if(pro==null){
                Toast.makeText(context, "Porfavor ingresar Datos validos.", Toast.LENGTH_SHORT).show();
            }else{
                ApiClient api = ApiClient.getApi();
                Propietario propi = api.obtenerUsuarioActual();
                propi.setId(pro.getId());
                propi.setDni(pro.getDni());
                propi.setNombre(pro.getNombre());
                propi.setApellido(pro.getApellido());
                propi.setEmail(pro.getEmail());
                propi.setContraseña(pro.getContraseña());
                propi.setTelefono(pro.getTelefono());
                api.actualizarPerfil(propi);
                guardarM.setValue(true);
            }
        }
    }
    public LiveData<Boolean> getEditarM() {
        if(editarM == null){
            editarM = new MutableLiveData<>();
        }
        return editarM;
    }
    public LiveData<Boolean> getGuardarM() {
        if(guardarM == null){
            guardarM = new MutableLiveData<>();
        }
        return guardarM;
    }

    public LiveData<Propietario> getActualM() {
        if(actualM == null){
            actualM = new MutableLiveData<>();
        }
        return actualM;
    }
}