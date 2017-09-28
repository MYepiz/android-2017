package com.example.marty.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Dialogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialogs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.alerta_basica) {
            createRadioListDialog();
            TextView CODE = (TextView)findViewById(R.id.codigo);
            CODE.setText(
                    "\tpublic void createRadioListDialog() {\n"+
                        "\t\tAlertDialog.Builder builder = new AlertDialog.Builder(this);\n"+
                        "\t\t\tbuilder.setTitle(\"Diálogo simple\")\n"+
                        "\t\t\t.setMessage(\"Están hechos leña, menos el Papu.\n" +
                            "\t\t\t\t\\nÉse simplemente está tonto.\")\n"+
                        "\t\t\t.setPositiveButton(\"LA NETA\", new DialogInterface.OnClickListener() {\n"+
                        "\t@Override\n"+
                        "\t\tpublic void onClick(DialogInterface dialog, int which) {\n"+
                        "\t\t\tToast.makeText(Dialogs.this, \"Paaaapu toooonto...\", \n" +
                            "\t\t\t\tToast.LENGTH_SHORT).show();\n"+
                        "\t\t}\n"+
                        "\t})\n"+
                        "\t.setNegativeButton(\"SEGÚN TÚ\", new DialogInterface.OnClickListener() {\n"+
                        "\t\t@Override\n"+
                        "\t\tpublic void onClick(DialogInterface dialog, int which) {\n"+
                        "\t\t\tToast.makeText(Dialogs.this, \"El bato\", Toast.LENGTH_SHORT).show();\n"+
                        "\t\t}\n"+
                        "\t});\n"+
                        "\tbuilder.show();\n"+
                        "\t}\n"
            );
        }
        if (id == R.id.alerta_login){
            createLoginDialogo();
            TextView CODE = (TextView)findViewById(R.id.codigo);
            CODE.setText(
                    "\tpublic void createLoginDialogo() {\n"+
                            "\t\tfinal AlertDialog builder = new AlertDialog.Builder(this).create();\n"+
                            "\t\tLayoutInflater inflater = this.getLayoutInflater();\n"+
                            "\t\tView v = inflater.inflate(R.layout.login_dialog_chido, null);\n"+
                            "\t\tbuilder.setView(v);\n"+
                            "\t\tButton signup = (Button) v.findViewById(R.id.crear_boton);\n"+
                            "\t\tButton signin = (Button) v.findViewById(R.id.entrar_boton);\n"+

                            "\t\tsignup.setOnClickListener(\n"+
                            "\t\t\tnew View.OnClickListener() {\n"+
                            "\t\t\t@Override\n" +
                            "\t\t\tpublic void onClick(View v) {\n" +
                            "\t\t\tDialog settingsDialog = new Dialog(v.getContext());\n" +
                            "\t\t\tsettingsDialog.getWindow().requestFeature(\n" +
                            "\t\t\t\tWindow.FEATURE_NO_TITLE);\n" +
                            "\t\t\tsettingsDialog.setContentView(getLayoutInflater()\n" +
                            "\t\t\t\t.inflate(R.layout.image_layout, null));\n" +
                            "\t\t\tsettingsDialog.setCanceledOnTouchOutside(true);\n" +
                            "\t\t\tsettingsDialog.show();\n" +
                            "\t\t\t}\n" +
                            "\t\t}\n" +
                            "\t\t);\n" +

                            "\t\tsignin.setOnClickListener(\n"+
                            "\t\t\tnew View.OnClickListener() {\n"+
                            "\t\t\t\t@Override\n" +
                            "\t\t\t\tpublic void onClick(View v) {\n" +
                            "\t\t\t\t\tbuilder.dismiss();\n" +
                            "\t\t\t\t}\n" +
                            "\t\t\t}\n" +
                            "\t\t);\n" +

                            "\t\tbuilder.show();\n"+
                            "\t}\n"
            );
        }

        return super.onOptionsItemSelected(item);
    }

    public void createRadioListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Diálogo simple")
                .setMessage("Están hechos leña, menos el Papu.\nÉse simplemente está tonto.")
                .setPositiveButton("LA NETA", new DialogInterface.OnClickListener() {
        @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Dialogs.this, "Paaaapu toooonto...", Toast.LENGTH_SHORT).show();
            }
        })
        .setNegativeButton("SEGÚN TÚ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Dialogs.this, "El bato", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void createLoginDialogo() {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.login_dialog_chido, null);
        builder.setView(v);

        Button signup = (Button) v.findViewById(R.id.crear_boton);
        Button signin = (Button) v.findViewById(R.id.entrar_boton);

        signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog settingsDialog = new Dialog(v.getContext());
                        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout, null));
                        settingsDialog.setCanceledOnTouchOutside(true);
                        settingsDialog.show();
                    }
                }
        );
        signin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                }

        );
        builder.show();
    }

}
