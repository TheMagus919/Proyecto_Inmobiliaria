package com.agusoft.inmobiliaria.ui.perfil;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.agusoft.inmobiliaria.databinding.FragmentPerfilBinding;
import com.agusoft.inmobiliaria.modelo.Propietario;
import com.agusoft.inmobiliaria.request.ApiClient;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        perfilViewModel.getActualM().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.edCodigoPro.setText(propietario.getId()+"");
                binding.edDniPro.setText(propietario.getDni().toString());
                binding.edNombrePro.setText(propietario.getNombre().toString());
                binding.edApellidoPro.setText(propietario.getApellido().toString());
                binding.edEmailPro.setText(propietario.getEmail().toString());
                binding.edPasswordPro.setText(propietario.getContraseña().toString());
                binding.edTelefonoPro.setText(propietario.getTelefono().toString());
            }
        });
        perfilViewModel.getEditarM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.edCodigoPro.setEnabled(true);
                binding.edDniPro.setEnabled(true);
                binding.edNombrePro.setEnabled(true);
                binding.edApellidoPro.setEnabled(true);
                binding.edEmailPro.setEnabled(true);
                binding.edPasswordPro.setEnabled(true);
                binding.edTelefonoPro.setEnabled(true);
                binding.btEditar.setText("Guardar");
            }
        });
        perfilViewModel.getGuardarM().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.edCodigoPro.setEnabled(false);
                binding.edDniPro.setEnabled(false);
                binding.edNombrePro.setEnabled(false);
                binding.edApellidoPro.setEnabled(false);
                binding.edEmailPro.setEnabled(false);
                binding.edPasswordPro.setEnabled(false);
                binding.edTelefonoPro.setEnabled(false);
                binding.btEditar.setText("Editar");
            }
        });
        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("salida",binding.btEditar.getText()+"  text");

                int id = Integer.parseInt(binding.edCodigoPro.getText().toString());
                Long dni = Long.parseLong(binding.edDniPro.getText().toString());
                String nombre = binding.edNombrePro.getText().toString();
                String apellido = binding.edApellidoPro.getText().toString();
                String email = binding.edEmailPro.getText().toString();
                String password = binding.edPasswordPro.getText().toString();
                String telefono = binding.edTelefonoPro.getText().toString();
                int avatar = ApiClient.getApi().obtenerUsuarioActual().getAvatar();
                Propietario pro = new Propietario(id,dni,nombre,apellido,email,password,telefono,avatar);

                Log.d("salida",pro.getNombre()+" - "+pro.getApellido()+" - "+pro.getEmail()+" - "+pro.getAvatar());
                perfilViewModel.opcion(binding.btEditar.getText().toString(),pro);
            }
        });
        perfilViewModel.obtenerDatos();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}