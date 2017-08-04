package com.xianwei.interestingquiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    OneAnswerQuestion secondOneAnswerQuestion;
    MultiAnswersQuestion firstMultiAnswersQuestion;
    MultiAnswersQuestion secondMultiAnswersQuestion;
    View questionOneView = null;
    View questionTwoView = null;
    View questionThreeView = null;
    View questionFourView = null;

    @BindView(R.id.radio_button_a) RadioButton radioButtonA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupUI();
    }

    private void setupUI() {
        createQuestions();
        questionOneView = createOneAnswerView(firstOneAnswerQuestion);
        questionTwoView = createOneAnswerView(secondOneAnswerQuestion);
        questionThreeView = createMultiAnswersView(firstMultiAnswersQuestion);
        questionFourView = createMultiAnswersView(secondMultiAnswersQuestion);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.questions_view);
        linearLayout.addView(questionOneView);
        linearLayout.addView(questionTwoView);
        linearLayout.addView(questionThreeView);
        linearLayout.addView(questionFourView);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createToast(submit());
                }
            }
        );
    }

    private String submit() {
        int score = 0;
        RadioButton questionOneAnswerRadio = (RadioButton) questionOneView.findViewById(R.id.radio_button_c);
        RadioButton questionTwoAnswerRadio = (RadioButton) questionTwoView.findViewById(R.id.radio_button_a);

        CheckBox questionThreeCheckboxOne = (CheckBox) questionThreeView.findViewById(R.id.checkbox_a);
        CheckBox questionThreeCheckboxTwo = (CheckBox) questionThreeView.findViewById(R.id.checkbox_b);
        CheckBox questionThreeCheckboxThree = (CheckBox) questionThreeView.findViewById(R.id.checkbox_c);
        CheckBox questionThreeCheckboxFour = (CheckBox) questionThreeView.findViewById(R.id.checkbox_d);

        CheckBox questionFourCheckboxOne = (CheckBox) questionFourView.findViewById(R.id.checkbox_a);
        CheckBox questionFourCheckboxTwo = (CheckBox) questionFourView.findViewById(R.id.checkbox_b);
        CheckBox questionFourCheckboxThree = (CheckBox) questionFourView.findViewById(R.id.checkbox_c);
        CheckBox questionFourCheckboxFour = (CheckBox) questionFourView.findViewById(R.id.checkbox_d);

        if (questionOneAnswerRadio.isChecked()){
            score += 25;
        }
        if (questionTwoAnswerRadio.isChecked()) {
            score += 25;
        }
        if (!questionThreeCheckboxOne.isChecked()&&
            questionThreeCheckboxTwo.isChecked()&&
            questionThreeCheckboxThree.isChecked()&&
            questionThreeCheckboxFour.isChecked()) {
            score +=25;
        }

        if (questionFourCheckboxOne.isChecked()&&
            !questionFourCheckboxTwo.isChecked()&&
            questionFourCheckboxThree.isChecked()&&
            !questionFourCheckboxFour.isChecked()) {
            score +=25;
        }

        return "Your final score is :" + score + "/100";
    }

    private void createQuestions(){
        Bitmap imageOne = BitmapFactory.decodeResource(getResources(), R.drawable.mountfuji);
        firstOneAnswerQuestion = new OneAnswerQuestion("Question One:\nWhich country does this landmark belongs to ?",imageOne,"China", "Korea","Japan","This is Mountain FuJi");

        Bitmap imageTwo = BitmapFactory.decodeResource(getResources(), R.drawable.machupicchu);
        secondOneAnswerQuestion = new OneAnswerQuestion("Question Two:\nWhich country does this landmark belongs to ?",imageTwo,"Peru", "Chile","Brazil","This is Machu Picchu");

        Bitmap imageThree = BitmapFactory.decodeResource(getResources(), R.drawable.tajmahal);
        firstMultiAnswersQuestion = new MultiAnswersQuestion("Question Three:\nPlease choose the right description of this building.",
                                                                imageThree,
                                                                "This building is in Thailand",
                                                                "This building is in India",
                                                                "The building is a tomb",
                                                                "It is one of the New 7 Wonders of the World",
                                                                "Its name is Taj Mahal");

        Bitmap imageFour = BitmapFactory.decodeResource(getResources(), R.drawable.chichenitza);
        secondMultiAnswersQuestion = new MultiAnswersQuestion("Question Four:\nPlease choose the right description of this building.",
                                                                imageFour,
                                                                "It is one of the New 7 Wonders of the World",
                                                                "It is in Egypt",
                                                                "It is in Mexico",
                                                                "It was built before 2500 BCE",
                                                                "I can\'t tell you !");

    }


    private View createOneAnswerView (final OneAnswerQuestion question) {
        View newView = LayoutInflater.from(this).inflate(R.layout.activity_one_answer, null);

        TextView questionView = (TextView) newView.findViewById(R.id.one_answer_question_view);
        questionView.setText(question.getQuestion());

        ImageView imageView = (ImageView) newView.findViewById(R.id.one_answer_image_view);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
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
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, displayString, duration);
        toast.show();
    }
}
