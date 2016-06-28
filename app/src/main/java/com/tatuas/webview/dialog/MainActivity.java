package com.tatuas.webview.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private EditText urlEditText;
    private SwitchCompat enableJavaScriptSwitchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.start_button);
        urlEditText = (EditText) findViewById(R.id.url_edit_text);
        enableJavaScriptSwitchCompat = (SwitchCompat) findViewById(R.id.enable_javascript_switch_compat);
        urlEditText.setSelection(urlEditText.getText().length());
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(WebViewDialogActivity.createIntent(
                        MainActivity.this,
                        urlEditText.getText().toString(),
                        enableJavaScriptSwitchCompat.isChecked()));
            }
        });
    }
}
