package com.hugopinto.acercade;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView guardarview;
    Button btn;
    private TextView a,b,c,d,x,f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guardarview=findViewById(R.id.foto);
        btn=findViewById(R.id.share);
        a=findViewById(R.id.name);
        b=findViewById(R.id.carrera);
        c=findViewById(R.id.github);
        d=findViewById(R.id.correo);
        x=findViewById(R.id.twitter);
        f=findViewById(R.id.telefono);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Share(v);
            }
        });



        getSupportActionBar().setTitle("Acerca de:");
    }
    private Bitmap jalarbitmapdelview(View view){
        Bitmap Retorno=Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(Retorno);
        Drawable bgDrawable= view.getBackground();
        if(bgDrawable!=null){
            bgDrawable.draw(canvas);
        }
        else{
            canvas.drawColor(Color.BLACK);
        }
        view.draw(canvas);
        return Retorno;
    }
    public void Share(View view){
        Bitmap bitmap=jalarbitmapdelview(guardarview);
        try {
            File file = new File(this.getExternalCacheDir(),"logicchip.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TEXT, "Acerca de mi\nNombre: "+a.getText().toString()+"\nCarrera: "+b.getText().toString()
                    +"\nGitHub: "+c.getText().toString()+"\nCorreo: "+d.getText().toString()
                    +"\nTwitter: "+x.getText().toString()+"\nTelefono: "+f.getText().toString());
            intent.setType("*/*");
            startActivity(Intent.createChooser(intent, "Enviar a"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
