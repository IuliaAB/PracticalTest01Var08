package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    EditText riddle_edit_text;
    EditText answer_edit_text;
    EditText edit_text;

    Button check_button, back_button;
    private int serviceStatus = Constants.SERVICE_STOPPED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        riddle_edit_text = (EditText)findViewById(R.id.riddle_edit_view_id);
        answer_edit_text = (EditText)findViewById(R.id.answer_edit_view_id);

        edit_text = (EditText)findViewById(R.id.edit_text_id);

        check_button = (Button)findViewById(R.id.check_button);
        back_button  = (Button)findViewById(R.id.back_button);

        String riddle = null;
        String answer = null;

        try {
            Intent intent = getIntent();
            if (intent != null) {
                Bundle data = intent.getExtras();
                riddle = data.getString("riddle");
                answer = data.getString("answer");

            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        riddle_edit_text.setText(riddle);
        answer_edit_text.setText(answer);

        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer_this = edit_text.getText().toString();
                if(answer_this.equals(answer_edit_text.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Equal values", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Not equal values", Toast.LENGTH_LONG).show();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(serviceStatus == Constants.SERVICE_STOPPED) {

            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
            int len_answer = answer_edit_text.getText().toString().length();
            intent.putExtra("length", len_answer);
            intent.putExtra("answer", answer_edit_text.getText().toString());
            getApplicationContext().startService(intent);
            serviceStatus = Constants.SERVICE_STARTED;

        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var08Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
