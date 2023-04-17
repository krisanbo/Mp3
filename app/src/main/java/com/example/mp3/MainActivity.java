package com.example.mp3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // no hace falta encapsular
    Button play_pause,bt_repetir;
    MediaPlayer mp;
    ImageView iv;
    // creamos variables
    int repetir = 1, posicion =0;

    // creamos el vector media player porque hay varias canciones
    MediaPlayer vectormp []= new MediaPlayer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // traemos solo los que van a cambiar
        play_pause = (Button) findViewById(R.id.bt_play);
        bt_repetir= (Button) findViewById(R.id.bt_repetir);
        iv= (ImageView) findViewById(R.id.imageView);

        //  creamos lista de reproducion, y la metemos en el vector

        vectormp[0]= MediaPlayer.create(this,R.raw.race);
        vectormp[1]= MediaPlayer.create(this,R.raw.sound);
        vectormp[2]= MediaPlayer.create(this,R.raw.tea);
    }

    // metodo para el boton play-pause

    public void PlayPause(View view){
        // vamos a meter condicional para saber si esta en play o pause

        if (vectormp[posicion].isPlaying()){
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();

        }else{
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();

        }

    }
    // metodo boton stop
    public void Stop (View view){
        int p_actual=posicion;//opcional esto
        // condicional
        if(vectormp[posicion] !=null){
            vectormp[posicion].stop();
            // como se vacia tengo que volver a poner el vector
            vectormp[0]= MediaPlayer.create(this,R.raw.race);
            vectormp[1]= MediaPlayer.create(this,R.raw.sound);
            vectormp[2]= MediaPlayer.create(this,R.raw.tea);
            posicion=0;
            // o si quieres que para en la cancion que estes
            posicion= p_actual;
            //cuando pulse stop quiero que play pause este..
            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();


        }
    }
    //metodo repetir la pista
    public void Repetir(View view){
        // condicional ==1 por ser dintinto a 2 que era la variable creada
        if(repetir==1){
            bt_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            // para que no se repita
            vectormp[posicion].setLooping(false);
            repetir=2;

        }else{
            bt_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir=1;


        }
    }

    // metodo siguiente cancion
    public void Siguiente(View view){
        //condicional para ver si hay mas canciones o no(posicion 0,1,2) Vectormp 1,2,3
        if(posicion< vectormp.length -1){
            //otro condicional para ver si se esta reproduciendo o no y si si parar la cancion
            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                vectormp[0]= MediaPlayer.create(this,R.raw.race);
                vectormp[1]= MediaPlayer.create(this,R.raw.sound);
                vectormp[2]= MediaPlayer.create(this,R.raw.tea);
                posicion ++;
                vectormp[posicion].start();
                //cambiar la portada
                if(posicion==0){
                    iv.setImageResource(R.drawable.portada1);

                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);

                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);

                }
            }else{
                posicion ++;
                if(posicion==0){
                    iv.setImageResource(R.drawable.portada1);

                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);

                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);
                }
            }


        }else{
            Toast.makeText(this, "no hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }
    // metodo anterior cancion
    public void Anterior(View view){
        // condicional para saber en que posicion viene
        if(posicion >=1){
            if(vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                vectormp[0]= MediaPlayer.create(this,R.raw.race);
                vectormp[1]= MediaPlayer.create(this,R.raw.sound);
                vectormp[2]= MediaPlayer.create(this,R.raw.tea);
                posicion --;
                vectormp[posicion].start();
                //cambiar la portada
                if(posicion==0){
                    iv.setImageResource(R.drawable.portada1);

                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);

                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);

                }
            }else{
                posicion --;
                if(posicion==0){
                    iv.setImageResource(R.drawable.portada1);

                }else if(posicion==1){
                    iv.setImageResource(R.drawable.portada2);

                }else if(posicion==2){
                    iv.setImageResource(R.drawable.portada3);
                }
            }

        }else{
            Toast.makeText(this, "no hay mas canciones", Toast.LENGTH_SHORT).show();
        }

    }
}