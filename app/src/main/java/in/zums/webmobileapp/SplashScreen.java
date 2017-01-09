package in.zums.webmobileapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        final Drawable homeicon = ContextCompat.getDrawable(this,R.mipmap.ic_launcher);
//        getSupportActionBar().setHomeAsUpIndicator(homeicon);



//        checkInternet(this);
        new StartNewAct().execute();

    }

    class StartNewAct extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(3000);
                publishProgress();
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);

            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }



    public void checkInternet(final Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        AlertDialog alertDialog = null;

        if (!isConnected){

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Please check your internet connection!")
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            dialog.dismiss();
                            checkInternet(context);
                        }
                    });
//                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
////                            dialog.dismiss();
//                        }
//                    });

            alertDialog = builder.create();
            alertDialog.show();

        }else{
            if (alertDialog !=null){
                alertDialog.dismiss();
            }


        }

    }
}
