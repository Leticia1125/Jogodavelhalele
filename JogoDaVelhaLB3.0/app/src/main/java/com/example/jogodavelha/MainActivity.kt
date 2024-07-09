package com.example.jogodavelha
import android.content.Intent
import android.os.Bundle
import android.view.View

import android.widget.Button
import android.widget.Toast
import kotlin.random.Random
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityInicioBinding
import com.example.jogodavelha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Vetor bidimensional representa o tabuleiro
    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )
    //Qual o Jogador está jogando
    var jogadorAtual = "laço"
    override fun onCreate(savedInstanceState: Bundle?) {
//inflando o layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
// Habilitando Edge-to-Edge
        enableEdgeToEdge()
// Definindo o layout da atividade
        setContentView(binding.root)
//botao que volta para o inicio
        binding.buttonsair.setOnClickListener {
// Criando uma Intent para iniciar a atividade InicioActivity
            val intent = Intent(this, InicioActivity::class.java)
//inicia a atividade do intent
            startActivity(intent)
        }
    }

    //Função associada com todos os botões param view é o botão clicado
    fun buttonClick(view: View) {
//O botão clicado é associado com uma constante
        val buttonSelecionado = view as Button
//O texto do botão recebe o jogador atual

//o botao que for clicado irá receber o jogador atual
        when (buttonSelecionado.id) {
            binding.buttonZero.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito.id -> tabuleiro[2][2] = jogadorAtual
        }
//altera o fundo do botao clicado
        buttonSelecionado.setBackgroundResource(R.drawable.hello)
//desabilita o botao clicado
        buttonSelecionado.isEnabled = false
//verifica o vencedor
        var vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
//mostra mensagem para o vencedor
            Toast.makeText(this, "Vencedor: " + vencedor,
                Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
//jogada aleatória do computador
        var rX = Random.nextInt(0, 3)
        var rY = Random.nextInt(0, 3)
        var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)

            if (tabuleiro[rX][rY] != "laço" && tabuleiro[rX][rY] != "sombra") {
//se encontar vazio sai do looping
                break
            }
            i++
        }
//"O" será jogado em uma posição aleatória
        tabuleiro[rX][rY] = "sombra"
        val posicao = rX * 3 + rY

//Atualização do botão de acordo com á jogada do computador
        when (posicao) {
            0 -> {
                binding.buttonZero.setBackgroundResource(R.drawable.sombras)
                binding.buttonZero.isEnabled = false
            }
            1 -> {
                binding.buttonUm.setBackgroundResource(R.drawable.sombras)
                binding.buttonUm.isEnabled = false
            }
            2 -> {
                binding.buttonDois.setBackgroundResource(R.drawable.sombras)
                binding.buttonDois.isEnabled = false
            }
            3 -> {
                binding.buttonTres.setBackgroundResource(R.drawable.sombras)
                binding.buttonTres.isEnabled = false
            }
            4 -> {
                binding.buttonQuatro.setBackgroundResource(R.drawable.sombras)
                binding.buttonQuatro.isEnabled = false
            }
            5 -> {
                binding.buttonCinco.setBackgroundResource(R.drawable.sombras)
                binding.buttonCinco.isEnabled = false

            }
            6 -> {
                binding.buttonSeis.setBackgroundResource(R.drawable.sombras)
                binding.buttonSeis.isEnabled = false
            }
            7 -> {
                binding.buttonSete.setBackgroundResource(R.drawable.sombras)
                binding.buttonSete.isEnabled = false
            }
            8 -> {
                binding.buttonOito.setBackgroundResource(R.drawable.sombras)
                binding.buttonOito.isEnabled = false
            }
        }
//Irá verificar se há um vencedor, após a jogada do computador
        vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
//Mostra uma mensagem para o vencedor
            Toast.makeText(this, "Vencedor: " + vencedor,

                Toast.LENGTH_LONG).show()

//cria um intent para reiniciar a atividade
            val intent = Intent(this, MainActivity::class.java)
//incia aatividade
            startActivity(intent)
//finaliza a atividade
            finish()
        }
    }
    // verifica o vencedor
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
// Verifica linhas e colunas
        for (i in 0 until 3) {
//Verifica se há três itens iguais na linha
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
//Verifica se há três itens iguais na coluna

            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
// Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }
//Verifica a quantidade de jogadores
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if(valor.equals("laço")||valor.equals("sombra")){
                    empate++
                }
            }
        }
//Se existem 9 jogadas e não há três letras iguais, houve um empate
        if(empate == 9){
            return "Empate"
        }
// Nenhum vencedor
        return null
    }
}