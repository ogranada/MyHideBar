package com.ogranada.myhidebar;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Inicio extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		Button ocultar = (Button) findViewById(R.id.btn_ocultar);
		ocultar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
				    //REQUIRES ROOT
				    Build.VERSION_CODES vc = new Build.VERSION_CODES();
				    Build.VERSION vr = new Build.VERSION();
				    String ProcID = "79"; //HONEYCOMB AND OLDER

				    //v.RELEASE  //4.0.3
				    if(vr.SDK_INT >= vc.ICE_CREAM_SANDWICH){
				        ProcID = "42"; //ICS AND NEWER
				    }

				    //REQUIRES ROOT
				    Process proc = Runtime.getRuntime().exec(new String[]{"su","-c","service call activity "+ ProcID +" s16 com.android.systemui"}); //WAS 79
				    proc.waitFor();

				}catch(Exception ex){
				    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
		Button mostrar = (Button) findViewById(R.id.btn_mostrar);
		mostrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
                    Process proc = Runtime.getRuntime().exec(new String[]{
                    		"su","-c","am","startservice","-n","com.android.systemui/.SystemUIService"});
                    proc.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_inicio, menu);
		return true;
	}

}
