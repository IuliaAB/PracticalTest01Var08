package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    EditText riddle;
    EditText answer;
    Button play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        riddle = (EditText)findViewById(R.id.riddle_edit_text);
        answer = (EditText)findViewById(R.id.answer_edit_text);

        play_button = (Button)findViewById(R.id.play_button);

        play_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(riddle.getText().toString().length() != 0 && answer.getText().toString().length() != 0) {
                    Intent intent = new Intent(getBaseContext(), PracticalTest01Var02PlayActivity.class);
                    intent.putExtra("riddle", riddle.getText().toString());
                    intent.putExtra("answer", answer.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        riddle = (EditText)findViewById(R.id.riddle_edit_text);
        answer = (EditText)findViewById(R.id.answer_edit_text);


        savedInstanceState.putString("riddle", riddle.getText().toString());
        savedInstanceState.putString("answer", answer.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("riddle")) {
                riddle.setText(savedInstanceState.getString("riddle"));
            }

            if (savedInstanceState.containsKey("answer")) {
                answer.setText(savedInstanceState.getString("answer"));
            }
        }
    }

}
