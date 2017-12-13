package com.example.marty.pocketbolt;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Potencia_Bomba extends Fragment {

    EditText LPS;
    EditText GMP;
    EditText M;
    EditText FT;
    EditText CDT;
    EditText AGUA;
    EditText EFICIENCIA;
    EditText RES;
    EditText Q;
    Spinner spinner;
    List<String> values;
    ImageButton save;
    ImageButton back;
    ImageButton clear;
    View myview;

    public Potencia_Bomba() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myview = inflater.inflate(R.layout.fragment_potencia__bomba, container, false);

        LPS = myview.findViewById(R.id.LPSedit);
        GMP = myview.findViewById(R.id.GPMedit);
        M = myview.findViewById(R.id.Medit);
        FT = myview.findViewById(R.id.FTedit);
        CDT = myview.findViewById(R.id.CDTedit);
        AGUA = myview.findViewById(R.id.DAGUAedit);
        EFICIENCIA = myview.findViewById(R.id.EFedit);
        Q = myview.findViewById(R.id.Qedit);
        RES = myview.findViewById(R.id.Redit);
        save = (ImageButton)myview.findViewById(R.id.saveBtn);
        back = (ImageButton)myview.findViewById(R.id.volver);
        clear = (ImageButton)myview.findViewById(R.id.clearBtn);

        showHints();

        spinner = (Spinner) myview.findViewById(R.id.spinner);
        ArrayAdapter AA = ArrayAdapter.createFromResource(myview.getContext(), R.array.tipo_dato_array,
                android.R.layout.simple_spinner_item );
        AA.setDropDownViewResource(R.layout.spinner_custom);
        spinner.setAdapter(AA);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)myview.findViewById(R.id.LPSlbl)).setVisibility(View.INVISIBLE);
                ((TextView)myview.findViewById(R.id.Mlbl)).setVisibility(View.INVISIBLE);
                ((TextView)myview.findViewById(R.id.GPMlbl)).setVisibility(View.INVISIBLE);
                ((TextView)myview.findViewById(R.id.FTlbl)).setVisibility(View.INVISIBLE);

                LPS.setVisibility(View.INVISIBLE);
                M.setVisibility(View.INVISIBLE);
                GMP.setVisibility(View.INVISIBLE);
                FT.setVisibility(View.INVISIBLE);

                switch (spinner.getSelectedItem().toString()){
                    case "Litros por segundo/Metros":
                        ((TextView)myview.findViewById(R.id.LPSlbl)).setVisibility(View.VISIBLE);
                        ((TextView)myview.findViewById(R.id.Mlbl)).setVisibility(View.VISIBLE);
                        LPS.setVisibility(View.VISIBLE);
                        M.setVisibility(View.VISIBLE);
                        break;
                    case "Galones por minuto/Pies":
                        ((TextView)myview.findViewById(R.id.GPMlbl)).setVisibility(View.VISIBLE);
                        ((TextView)myview.findViewById(R.id.FTlbl)).setVisibility(View.VISIBLE);
                        GMP.setVisibility(View.VISIBLE);
                        FT.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //CREAR ADORNOS
        //crear canvas al tamanio de la pantalla, obtener el color primario oscuro para pintar y
        //ajustar el adorno al texto

        //buscar la layout
        RelativeLayout clayout = (RelativeLayout)myview.findViewById(R.id.bombahpLayout);
        //crear un color de pintura
        Paint paint = new Paint();
        String color = "#" + Integer.toHexString(ContextCompat.getColor(myview.getContext(), R.color.colorPrimaryDark));
        paint.setColor(Color.parseColor(color));
        //medir el tamanio de la pantalla
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        //crear un canvas del tamanio de la pantalla para usarlo de fondo
        Bitmap bg = Bitmap.createBitmap(height,width, Bitmap.Config.ARGB_8888);
        clayout.setBackground(new BitmapDrawable(bg));
        Canvas canvas = new Canvas(bg);
        //medir el textview del titulo
        TextView title = (TextView)myview.findViewById(R.id.titulo);
        title.measure(0, 0);       //must call measure!
        int rectHeight = title.getMeasuredHeight()+5; //get height
        int rectWidth = title.getMeasuredWidth()+15;  //get width

        //medir el textview para volver
        back.measure(0, 0);       //must call measure!
        int backHeight = back.getMeasuredHeight()+10; //get height
        int backWidth = back.getMeasuredWidth();  //get width

        //dibujar los adornos
        //titulo
        Path path = RoundedRect(0, rectHeight, rectWidth*2, rectHeight*2, rectHeight, rectHeight,
                false, true, true, false);
        canvas.drawPath(path, paint);
        //volver
        int right = backWidth*2;
        int bottom = canvas.getHeight() - 20;
        int top = canvas.getHeight() - backHeight + 25;
        path = RoundedRect(0, top, right, bottom, backHeight, backHeight,
                false, true, true, false);
        canvas.drawPath(path, paint);

        //setear los clicks listeners
        setClicks();
        setInitialZero();
        setValuesChanges();

        //obtener valores comerciales de bombas
        DBaccess databaseAccess = DBaccess.getInstance(myview.getContext());
        databaseAccess.open();
        values = databaseAccess.getHPvalues();
        databaseAccess.close();
        return myview;
    }

    private void setInitialZero(){
        LPS.setText("0");
        M.setText("0");
        GMP.setText("0");
        FT.setText("0");
        CDT.setText("0");
        AGUA.setText("1");
        EFICIENCIA.setText(".7");
        RES.setText("0");
        Q.setText("0");
    }

    private void calculateHP(){
        double ku = Double.parseDouble(Q.getText().toString());
        double cdt = Double.parseDouble(CDT.getText().toString());
        double agua = Double.parseDouble(AGUA.getText().toString());
        double ef = Double.parseDouble(EFICIENCIA.getText().toString());
        double resultado = ((ku*cdt*agua) / (3960*ef));

        String preview = "";
        for(int i=0; i < values.size(); i++){
            if (resultado > 300) {
                preview = String.format("%.2f", resultado);
                break;
            }else{
                if (Double.parseDouble(values.get(i)) > resultado) {
                    preview = String.format("%.2f -> %s", resultado, values.get(i));
                    break;
                }
            }
        }
        RES.setText(preview);
    }

    private void setValuesChanges(){
        LPS.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (LPS.getText().length() > 0){
                        double lts = Double.parseDouble(LPS.getText().toString());
                        lts = (15.85*lts);
                        GMP.setText( String.format("%.2f", lts) );
                        Q.setText( String.format("%.2f", lts) );
                    }else{
                        LPS.setText("0");
                        GMP.setText("0");
                        Q.setText("0");
                    }
                    calculateHP();
                }
            }
        });

        M.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (M.getText().length() > 0){
                        double mts = Double.parseDouble(M.getText().toString());
                        mts = (3.28*mts);
                        FT.setText( String.format("%.2f", mts) );
                        CDT.setText( String.format("%.2f", mts) );
                    }else{
                        M.setText("0");
                        FT.setText("0");
                        CDT.setText("0");
                    }
                    calculateHP();
                }
            }
        });

        GMP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (GMP.getText().length() > 0){
                        double gpm = Double.parseDouble(GMP.getText().toString());
                        Q.setText( String.format("%.2f", gpm) );
                        gpm = (gpm/15.85);
                        LPS.setText( String.format("%.2f", gpm) );
                    }else{
                        LPS.setText("0");
                        GMP.setText("0");
                        Q.setText("0");
                    }
                    calculateHP();
                }
            }
        });

        Q.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (Q.getText().length() > 0){
                        double gpm = Double.parseDouble(Q.getText().toString());
                        GMP.setText( String.format("%.2f", gpm) );
                        gpm = (gpm/15.85);
                        LPS.setText( String.format("%.2f", gpm) );
                    }else{
                        LPS.setText("0");
                        GMP.setText("0");
                        Q.setText("0");
                    }
                    calculateHP();
                }
            }
        });

        FT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (FT.getText().length() > 0){
                        double ft = Double.parseDouble(FT.getText().toString());
                        CDT.setText( String.format("%.2f", ft) );
                        ft = (ft/3.28);
                        M.setText( String.format("%.2f", ft) );
                    }else{
                        M.setText("0");
                        FT.setText("0");
                        CDT.setText("0");
                    }
                    calculateHP();
                }
            }
        });

        CDT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (CDT.getText().length() > 0){
                        double ft = Double.parseDouble(CDT.getText().toString());
                        FT.setText( String.format("%.2f", ft) );
                        ft = (ft/3.28);
                        M.setText( String.format("%.2f", ft) );
                    }else{
                        M.setText("0");
                        FT.setText("0");
                        CDT.setText("0");
                    }
                    calculateHP();
                }
            }
        });

        AGUA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (AGUA.getText().length() < 1){
                        AGUA.setText("0");
                    }
                    calculateHP();
                }
            }
        });

        EFICIENCIA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (EFICIENCIA.getText().length() < 1){
                        EFICIENCIA.setText("0");
                    }
                    calculateHP();
                }
            }
        });
    }

    private void setClicks(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(v.getContext(), Index.class);
                startActivity(I);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setBackgroundTintMode(PorterDuff.Mode.DARKEN);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInitialZero();
            }
        });
    }

    public static Path RoundedRect(
            float left, float top, float right, float bottom, float rx, float ry,
            boolean tl, boolean tr, boolean br, boolean bl
    ){
        Path path = new Path();
        if (rx < 0) rx = 0;
        if (ry < 0) ry = 0;
        float width = right - left;
        float height = bottom - top;
        if (rx > width / 2) rx = width / 2;
        if (ry > height / 2) ry = height / 2;
        float widthMinusCorners = (width - (2 * rx));
        float heightMinusCorners = (height - (2 * ry));

        path.moveTo(right, top + ry);
        if (tr)
            path.rQuadTo(0, -ry, -rx, -ry);//top-right corner
        else{
            path.rLineTo(0, -ry);
            path.rLineTo(-rx,0);
        }
        path.rLineTo(-widthMinusCorners, 0);
        if (tl)
            path.rQuadTo(-rx, 0, -rx, ry); //top-left corner
        else{
            path.rLineTo(-rx, 0);
            path.rLineTo(0,ry);
        }
        path.rLineTo(0, heightMinusCorners);

        if (bl)
            path.rQuadTo(0, ry, rx, ry);//bottom-left corner
        else{
            path.rLineTo(0, ry);
            path.rLineTo(rx,0);
        }

        path.rLineTo(widthMinusCorners, 0);
        if (br)
            path.rQuadTo(rx, 0, rx, -ry); //bottom-right corner
        else{
            path.rLineTo(rx,0);
            path.rLineTo(0, -ry);
        }

        path.rLineTo(0, -heightMinusCorners);

        path.close();//Given close, last lineto can be removed.

        return path;
    }

    private int getRelativeLeft(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    private int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    public void showHints(){

        ImageButton ayuda = (ImageButton) myview.findViewById(R.id.help);

        ayuda.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog settingsDialog = new Dialog(v.getContext());
                        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        settingsDialog.setContentView(getActivity().getLayoutInflater().inflate(R.layout.help_potencia_bomba, null));
                        settingsDialog.setCanceledOnTouchOutside(true);
                        settingsDialog.show();
                    }
                }
        );
    }

}
