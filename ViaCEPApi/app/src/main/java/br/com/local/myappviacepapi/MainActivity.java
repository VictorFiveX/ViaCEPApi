package br.com.local.myappviacepapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarCep;
    EditText txtCep;
    TextView RespostaCep,RspostaUf,RespostaLogradouro,RespostaBairro,RespostaComplemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        RespostaCep = findViewById(R.id.respostaCep);

        RspostaUf = findViewById(R.id.RspostaUf);
        RespostaLogradouro = findViewById(R.id.RespostaLogradouro);
        RespostaBairro = findViewById(R.id.RespostaBairro);
        RespostaComplemento = findViewById(R.id.RespostaComplemento);

        btnBuscarCep = findViewById(R.id.btnBuscaCep);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();

                    retorno.getUf();
                    retorno.getLogradouro();
                    retorno.getBairro();
                    retorno.getComplemento();


                    RspostaUf.setText(retorno.getUf().trim());
                    RespostaLogradouro.setText(retorno.getLogradouro().trim());
                    RespostaBairro.setText(retorno.getBairro().trim());
                    RespostaComplemento.setText(retorno.getComplemento().trim());
                    RespostaCep.setText(retorno.getCep().trim());

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}