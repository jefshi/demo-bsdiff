package com.demo.bsdiff;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.sample_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) view).setText(stringFromJNI());
            }
        });

        findViewById(R.id.btn_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            }
        });

        findViewById(R.id.btn_bspatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final File old = new File(Environment.getExternalStorageDirectory(), "old.apk");
                final File out = new File(Environment.getExternalStorageDirectory(), "out.apk");
                final File patch = new File(Environment.getExternalStorageDirectory(), "PATCH.patch");
                BsdiffUtil.bspatch(old, out, patch);
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
