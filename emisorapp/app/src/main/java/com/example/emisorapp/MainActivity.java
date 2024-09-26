package com.example.emisorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emisorapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Acceder a los campos de texto y botón usando binding
        binding.btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarBroadcast();
            }
        });
    }

    private void enviarBroadcast() {
        // Obtener los valores de los campos de texto usando binding
        String ciudad = binding.edtCiudad.getText().toString();
        String temperatura = binding.edtTemperatura.getText().toString();
        String clima = binding.edtClima.getText().toString();

        // Validar si los campos están vacíos
        if (ciudad.isEmpty() || temperatura.isEmpty() || clima.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear el intent y enviar el broadcast
        Intent intent = new Intent();
        intent.setAction("com.example.CLIMA");
        intent.putExtra("ciudad", ciudad);
        intent.putExtra("temperatura", temperatura);
        intent.putExtra("clima", clima);
        sendBroadcast(intent);

        Toast.makeText(getApplicationContext(), "Clima enviado", Toast.LENGTH_SHORT).show();
    }
}
