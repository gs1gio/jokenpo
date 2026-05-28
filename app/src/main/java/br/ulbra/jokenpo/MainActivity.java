package br.ulbra.jokenpo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int ptsJogador = 0;
    int ptsBot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void atualizarPlacar() {
        TextView txtplacar = findViewById(R.id.txtPlacar);
        txtplacar.setText("JOGADOR:" + ptsJogador +"\n" +
                "APP:" + ptsBot);
    }
    public void reiniciarPlacar(View view){
        ptsJogador = 0;
        ptsBot = 0;
        atualizarPlacar();
        ImageView imgApp = findViewById(R.id.imgApp);
        imgApp.setImageResource(android.R.color.transparent);
    }

    public void selecionadoPedra(View view){
        this.opcaoSelecionado("pedra");
    }
    public void selecionadoPapel(View view){
        this.opcaoSelecionado("papel");
    }
    public void selecionadoTesoura(View view){
        this.opcaoSelecionado("tesoura");
    }
    public void opcaoSelecionado(String opcaoSelecionado){
        ImageView imgApp = findViewById(R.id.imgApp);
        TextView txRes = findViewById(R.id.txtRes);

        String opcoes[] = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[new Random().nextInt(3)];
        switch (opcaoApp){
            case "pedra":
                imgApp.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imgApp.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imgApp.setImageResource(R.drawable.tesoura);
                break;
        }



        if((opcaoApp.equals("tesoura") && opcaoSelecionado.equals("papel"))||
                (opcaoApp.equals("papel") && opcaoSelecionado.equals("pedra")) ||
                (opcaoApp.equals("pedra") && opcaoSelecionado.equals("tesoura"))){
            ptsBot++;
            txRes.setText("RESULTADO: Você perdeu!");

        }else if((opcaoSelecionado.equals("tesoura") && opcaoApp.equals("papel"))||
                (opcaoSelecionado.equals("papel") && opcaoApp.equals("pedra")) ||
                (opcaoSelecionado.equals("pedra") && opcaoApp.equals("tesoura"))){
            ptsJogador++;
            txRes.setText("RESULTADO: Você venceu!");

        }else{
            txRes.setText("RESULTADO: VOCÊS EMPATARAM");
        }
        atualizarPlacar();
    }
}