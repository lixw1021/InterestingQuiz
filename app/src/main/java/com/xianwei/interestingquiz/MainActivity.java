package com.xianwei.interestingquiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    OneAnswerQuestion firstOneAnswerQuestion;
    MultiAnswersQuestion firstMultiAnswersQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        createQuestions();
        View viewOne = createOneAnswerView(firstOneAnswerQuestion);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.questions_view);
        linearLayout.addView(viewOne);

    }

    private void createQuestions(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.mountfuji);
        firstOneAnswerQuestion = new OneAnswerQuestion("Which country does this landmark come from ?",image,"China", "Thailand","Japan","This is Mountain FuJi");
    }


    private View createOneAnswerView (final OneAnswerQuestion question) {
        View newView = LayoutInflater.from(this).inflate(R.layout.activity_one_answer, null);

        TextView questionView = (TextView) newView.findViewById(R.id.one_answer_question_view);
        questionView.setText(question.getQuestion());

        ImageView imageView = (ImageView) newView.findViewById(R.id.one_answer_image_view);
        imageView.setImageBitmap(question.getImage());

        RadioButton buttonA = (RadioButton) newView.findViewById(R.id.radio_button_a);
        buttonA.setText(question.getAnswerA());

        RadioButton buttonB = (RadioButton) newView.findViewById(R.id.radio_button_b);
        buttonB.setText(question.getAnswerB());

        RadioButton buttonC = (RadioButton) newView.findViewById(R.id.radio_button_c);
        buttonC.setText(question.getAnswerC());

        Button hintButton = (Button) newView.findViewById(R.id.one_answer_hint_button);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createToast(question.getHint());
            }
        });

        return newView;
    }

    private View createMultiAnswersView(final MultiAnswersQuestion question) {
        View newView = LayoutInflater.from(this).inflate(R.layout.activity_multi_answers, null);

        TextView questionView = (TextView) newView.findViewById(R.id.multi_answers_question_view);
        questionView.setText(question.getQuestion());

        ImageView imageView = (ImageView) newView.findViewById(R.id.multi_answers_image_view);
        imageView.setImageBitmap(question.getImage());

        CheckBox checkBoxA = (CheckBox) newView.findViewById(R.id.checkbox_a);
        checkBoxA.setText(question.getAnswerA());

        CheckBox checkBoxB = (CheckBox) newView.findViewById(R.id.checkbox_b);
        checkBoxB.setText(question.getAnswerB());

        CheckBox checkBoxC = (CheckBox) newView.findViewById(R.id.checkbox_c);
        checkBoxC.setText(question.getAnswerC());

        CheckBox checkBoxD = (CheckBox) newView.findViewById(R.id.checkbox_d);
        checkBoxD.setText(question.getAnswerD());

        Button hintButton = (Button) newView.findViewById(R.id.multi_answers_hint_button);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createToast(question.getHint());
            }
        });

        return newView;
    }

    private void createToast(String displayString) {
        Context context = getApplicationContext();
        CharSequence text = displayString;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
