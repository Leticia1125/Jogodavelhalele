package com.example.jogodavelha
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityInicioBinding
class InicioActivity: AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//Aqui esta sendo inflando o layout da tela principal.
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root) //App ira usar esse layout na tela

// Definindo um listener de clique para o botão "buttonnivel1"
        binding.buttonnivel1.setOnClickListener {

// Criando uma intent para iniciar a MainActivity
            val intent = Intent(this, MainActivity::class.java)

// Iniciando a atividade MainActivity
            startActivity(intent)
        }
// Definindo um listener de clique para o botão "buttonnivel2"
        binding.buttonnivel2.setOnClickListener {

// Criando uma intent para iniciar a MainActivity2
            val intent = Intent(this, mainactivity2::class.java)

// Iniciando a atividade MainActivity2
            startActivity(intent)
        }

    }
}