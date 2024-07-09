package com.example.jogodavelha
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import kotlin.Pair
import com.example.jogodavelha.databinding.ActivityMainactivity2Binding
import kotlin.random.Random

class mainactivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainactivity2Binding

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
        binding = ActivityMainactivity2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
// Habilitando Edge-to-Edge
        enableEdgeToEdge()
// Definindo o layout da atividade
        setContentView(binding.root)
//botao que volta para o inicio
        binding.buttonsair2.setOnClickListener {
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

            binding.buttonZero2.id -> tabuleiro[0][0] = jogadorAtual
            binding.buttonUm2.id -> tabuleiro[0][1] = jogadorAtual
            binding.buttonDois2.id -> tabuleiro[0][2] = jogadorAtual
            binding.buttonTres2.id -> tabuleiro[1][0] = jogadorAtual
            binding.buttonQuatro2.id -> tabuleiro[1][1] = jogadorAtual
            binding.buttonCinco2.id -> tabuleiro[1][2] = jogadorAtual
            binding.buttonSeis2.id -> tabuleiro[2][0] = jogadorAtual
            binding.buttonSete2.id -> tabuleiro[2][1] = jogadorAtual
            binding.buttonOito2.id -> tabuleiro[2][2] = jogadorAtual
        }
//altera o fundo do botao clicado
        buttonSelecionado.setBackgroundResource(R.drawable.hello)
//desabilita o botao clicado
        buttonSelecionado.isEnabled = false
//verifica o vencedor
        var vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
//mostra mensagem para o vencedor
            Toast.makeText(
                this, "Vencedor: " + vencedor,
                Toast.LENGTH_LONG
            ).show()
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
                binding.buttonZero2.setBackgroundResource(R.drawable.sombras)
                binding.buttonZero2.isEnabled = false
            }

            1 -> {
                binding.buttonUm2.setBackgroundResource(R.drawable.sombras)
                binding.buttonUm2.isEnabled = false
            }

            2 -> {
                binding.buttonDois2.setBackgroundResource(R.drawable.sombras)
                binding.buttonDois2.isEnabled = false
            }

            3 -> {
                binding.buttonTres2.setBackgroundResource(R.drawable.sombras)
                binding.buttonTres2.isEnabled = false
            }

            4 -> {
                binding.buttonQuatro2.setBackgroundResource(R.drawable.sombras)
                binding.buttonQuatro2.isEnabled = false
            }

            5 -> {
                binding.buttonCinco2.setBackgroundResource(R.drawable.sombras)
                binding.buttonCinco2.isEnabled = false
            }

            6 -> {
                binding.buttonSeis2.setBackgroundResource(R.drawable.sombras)
                binding.buttonSeis2.isEnabled = false
            }

            7 -> {
                binding.buttonSete2.setBackgroundResource(R.drawable.sombras)
                binding.buttonSete2.isEnabled = false

            }

            8 -> {
                binding.buttonOito2.setBackgroundResource(R.drawable.sombras)
                binding.buttonOito2.isEnabled = false
            }
        }
//Irá verificar se há um vencedor, após a jogada do computador
        vencedor = verificaVencedor(tabuleiro)
        if (!vencedor.isNullOrBlank()) {
//Mostra uma mensagem para o vencedor
            Toast.makeText(
                this, "Vencedor: " + vencedor,
                Toast.LENGTH_LONG
            ).show()
//cria um intent para reiniciar a atividade
            val intent = Intent(this, MainActivity::class.java)
//incia a atividade
            startActivity(intent)
//finaliza
            finish()
        }
    }

    // Função para verificar o vencedor
    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
// Verifica linhas e colunas
        for (i in 0 until 3) {
//Verifica se há três itens iguais na linha
            if (tabuleiro[i][0] == tabuleiro[i][1] || tabuleiro[i][0] == tabuleiro[i][2] ||
                tabuleiro[i][1] == tabuleiro[i][2]
            ) {
                return tabuleiro[i][0]
            }
//Verifica se há três itens iguais na coluna
            if (tabuleiro[0][i] == tabuleiro[1][i] || tabuleiro[0][i] == tabuleiro[2][i] ||
                tabuleiro[1][i] == tabuleiro[2][i]
            ) {
                return tabuleiro[0][i]
            }
        }
// Verifica diagonais
        if (tabuleiro[0][0] == tabuleiro[1][1] || tabuleiro[0][0] == tabuleiro[2][2] ||
            tabuleiro[1][1] == tabuleiro[2][2]
        ) {

            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] || tabuleiro[0][2] == tabuleiro[2][0] ||
            tabuleiro[1][1] == tabuleiro[2][0]
        ) {
            return tabuleiro[0][2]
        }
//Verifica a quantidade de jogadores
        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if (valor.equals("laço") || valor.equals("sombra")) {
                    empate++
                }
            }
        }
//Se existem 9 jogadas e não há três letras iguais, houve um empate
        if (empate == 9) {
            return "Empate"
        }
        fun findBestMove(tabuleiro: Array<Array<String>>): Pair<Int, Int> {
            // Verificar se a máquina pode vencer

            // Jogada aleatória
            var rX: Int
            var rY: Int
            var i = 0
            while (i < 9) {
                rX = Random.nextInt(0, 3)
                rY = Random.nextInt(0, 3)
                if (tabuleiro[rX][rY] != "laço" && tabuleiro[rX][rY] != "sombra") {
                    return Pair(rX, rY)
                }
                i++
            }
            return Pair(0, 0) // Fallback, should not happen
        }


        // Função genérica para encontrar jogadas críticas (bloquear ou vencer)
        fun findCriticalMove(tabuleiro: Array<Array<String>>, jogador: String): Pair<Int, Int>? {
            for (i in 0 until 3) {
                // Verificar linhas
                if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] != "laço" && tabuleiro[i][2] != "sombra") return Pair(
                    i,
                    2
                )
                if (tabuleiro[i][0] == jogador && tabuleiro[i][2] == jogador && tabuleiro[i][1] != "laço" && tabuleiro[i][1] != "sombra") return Pair(
                    i,
                    1
                )
                if (tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador && tabuleiro[i][0] != "laço" && tabuleiro[i][0] != "sombra") return Pair(
                    i,
                    0
                )

                // Verificar colunas
                if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] != "laço" && tabuleiro[2][i] != "sombra") return Pair(
                    2,
                    i
                )
                if (tabuleiro[0][i] == jogador && tabuleiro[2][i] == jogador && tabuleiro[1][i] != "laço" && tabuleiro[1][i] != "sombra") return Pair(
                    1,
                    i
                )
                if (tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador && tabuleiro[0][i] != "laço" && tabuleiro[0][i] != "sombra") return Pair(
                    0,
                    i
                )
            }

            // Verificar diagonais
            if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] != "laço" && tabuleiro[2][2] != "sombra") return Pair(
                2,
                2
            )
            if (tabuleiro[0][0] == jogador && tabuleiro[2][2] == jogador && tabuleiro[1][1] != "laço" && tabuleiro[1][1] != "sombra") return Pair(
                1,
                1
            )
            if (tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador && tabuleiro[0][0] != "laço" && tabuleiro[0][0] != "sombra") return Pair(
                0,
                0
            )

            if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] != "laço" && tabuleiro[2][0] != "sombra") return Pair(
                2,
                0
            )
            if (tabuleiro[0][2] == jogador && tabuleiro[2][0] == jogador && tabuleiro[1][1] != "laço" && tabuleiro[1][1] != "sombra") return Pair(
                1,
                1
            )
            if (tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador && tabuleiro[0][2] != "laço" && tabuleiro[0][2] != "sombra") return Pair(
                0,
                2
            )

            return null
        }
        return null
    }

}