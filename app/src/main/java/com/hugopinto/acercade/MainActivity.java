package com.hugopinto.acercade;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView guardarview;
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guardarview=findViewById(R.id.foto);

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
    public void compartir(View view){
        Bitmap bitmap=jalarbitmapdelview(guardarview);
        try{
            File file = new File(this,getExternalCacheDir(),"imagen.png");
            FileOutputStream fOut=new FileOutputStream(file);
        }
    }
}
