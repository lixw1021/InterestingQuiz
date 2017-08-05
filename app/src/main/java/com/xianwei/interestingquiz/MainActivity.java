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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_PHONE;
import static android.text.InputType.TYPE_NUMBER_VARIATION_NORMAL;

public class MainActivity extends AppCompatActivity {

    OneAnswerQuestion firstOneAnswerQuestion;
    OneAnswerQuestion secondOneAnswerQuestion;
    MultiAnswersQuestion firstMultiAnswersQuestion;
    MultiAnswersQuestion secondMultiAnswersQuestion;
    InputAnswerQuestion firstInputAnswerQuestion;
    View questionOneView = null;
    View questionTwoView = null;
    View questionThreeView = null;
    View questionFourView = null;
    View questionFiveView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        createQuestions();
        questionOneView = createOneAnswerView(firstOneAnswerQuestion);
        questionTwoView = createOneAnswerView(secondOneAnswerQuestion);
        questionThreeView = createMultiAnswersView(firstMultiAnswersQuestion);
        questionFourView = createMultiAnswersView(secondMultiAnswersQuestion);

        questionFiveView = createInputAnswerView(firstInputAnswerQuestion);
        EditText editText = (EditText) questionFiveView.findViewById(R.id.input_answer_edit_text);
        editText.setInputType( TYPE_CLASS_NUMBER | TYPE_NUMBER_VARIATION_NORMAL);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.questions_view);
        linearLayout.addView(questionOneView);
        linearLayout.addView(questionTwoView);
        linearLayout.addView(questionThreeView);
        linearLayout.addView(questionFourView);
        linearLayout.addView(questionFiveView);

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

        EditText questionFiveEditText = (EditText) questionFiveView.findViewById(R.id.input_answer_edit_text);

        int questionFiveAnswer = Integer.parseInt(questionFiveEditText.getText().toString());

        if (questionOneAnswerRadio.isChecked()) {
            score += 20;
        }
        if (questionTwoAnswerRadio.isChecked()) {
            score += 20;
        }
        if (!questionThreeCheckboxOne.isChecked() &&
                questionThreeCheckboxTwo.isChecked() &&
                questionThreeCheckboxThree.isChecked() &&
                questionThreeCheckboxFour.isChecked()) {
            score += 20;
        }

        if (questionFourCheckboxOne.isChecked() &&
                !questionFourCheckboxTwo.isChecked() &&
                questionFourCheckboxThree.isChecked() &&
                !questionFourCheckboxFour.isChecked()) {
            score += 20;
        }
        if (questionFiveAnswer == 8) {
            score += 20;
            return "Your final score is :" + score + "/100";
        } else {
            return "Your final score is :" + score + "/100";
        }
    }

    private void createQuestions() {
        firstInputAnswerQuestion = new InputAnswerQuestion(getString(R.string.question5),
                                                            getString(R.string.question5_edit_hint),
                                                            getString(R.string.question5_button_hint));

        Bitmap imageOne = BitmapFactory.decodeResource(getResources(), R.drawable.mountfuji);
        firstOneAnswerQuestion = new OneAnswerQuestion(getString(R.string.question1),
                                                        imageOne,
                                                        getString(R.string.question1_answer_a),
                                                        getString(R.string.question1_answer_b),
                                                        getString(R.string.question1_answer_c),
                                                        getString(R.string.question1_hint));

        Bitmap imageTwo = BitmapFactory.decodeResource(getResources(), R.drawable.machupicchu);
        secondOneAnswerQuestion = new OneAnswerQuestion(getString(R.string.question2),
                                                        imageTwo,
                                                        getString(R.string.question2_answer_a),
                                                        getString(R.string.question2_answer_b),
                                                        getString(R.string.question2_answer_c),
                                                        getString(R.string.question2_hint));

        Bitmap imageThree = BitmapFactory.decodeResource(getResources(), R.drawable.tajmahal);
        firstMultiAnswersQuestion = new MultiAnswersQuestion(getString(R.string.question3),
                                                            imageThree,
                                                            getString(R.string.question3_answer_a),
                                                            getString(R.string.question3_answer_b),
                                                            getString(R.string.question3_answer_c),
                                                            getString(R.string.question3_answer_d),
                                                            getString(R.string.question3_hint));

        Bitmap imageFour = BitmapFactory.decodeResource(getResources(), R.drawable.chichenitza);
        secondMultiAnswersQuestion = new MultiAnswersQuestion(getString(R.string.question4),
                                                            imageFour,
                                                            getString(R.string.question4_answer_a),
                                                            getString(R.string.question4_answer_b),
                                                            getString(R.string.question4_answer_c),
                                                            getString(R.string.question4_answer_d),
                                                            getString(R.string.question4_hint));

    }

    private View createInputAnswerView(final InputAnswerQuestion question) {
        View newView = LayoutInflater.from(this).inflate(R.layout.activity_input_answer, null);

        InputAnswerViewHolder holder = new InputAnswerViewHolder(newView);
        holder.questionTextView.setText(question.getQuestion());
        holder.editText.setHint(question.getEditHint());
        holder.hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createToast(question.getButtonHint());
            }
        });
        return newView;
    }

    static class InputAnswerViewHolder {
        @BindView(R.id.input_answer_question_view)
        TextView questionTextView;
        @BindView(R.id.input_answer_edit_text)
        EditText editText;
        @BindView(R.id.input_answer_hint_button)
        Button hintButton;

        public InputAnswerViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private View createOneAnswerView(final OneAnswerQuestion question) {
        View newView = LayoutInflater.from(this).inflate(R.layout.activity_one_answer, null);

        OneAnswerViewHolder holder = new OneAnswerViewHolder(newView);
        holder.questionTextView.setText(question.getQuestion());
        holder.imageView.setImageBitmap(question.getImage());
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER);
        holder.radioButtonA.setText(question.getAnswerA());
        holder.radioButtonB.setText(question.getAnswerB());
        holder.radioButtonC.setText(question.getAnswerC());
        holder.hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createToast(question.getHint());
            }
        });

        return newView;
    }

    static class OneAnswerViewHolder {

        @BindView(R.id.one_answer_question_view)
        TextView questionTextView;
        @BindView(R.id.one_answer_image_view)
        ImageView imageView;
        @BindView(R.id.radio_button_a)
        RadioButton radioButtonA;
        @BindView(R.id.radio_button_b)
        RadioButton radioButtonB;
        @BindView(R.id.radio_button_c)
        RadioButton radioButtonC;
        @BindView(R.id.one_answer_hint_button)
        Button hintButton;

        public OneAnswerViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    private View createMultiAnswersView(final MultiAnswersQuestion question) {
        View newView = LayoutInflater.from(this).inflate(R.layout.activity_multi_answers, null);
        MultiAnswersViewHolder holder = new MultiAnswersViewHolder(newView);

        holder.questionTextView.setText(question.getQuestion());
        holder.imageView.setImageBitmap(question.getImage());
        holder.checkBoxA.setText(question.getAnswerA());
        holder.checkBoxB.setText(question.getAnswerB());
        holder.checkBoxC.setText(question.getAnswerC());
        holder.checkBoxD.setText(question.getAnswerD());
        holder.hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createToast(question.getHint());
            }
        });
        return newView;
    }

    static class MultiAnswersViewHolder {
        @BindView(R.id.multi_answers_question_view)
        TextView questionTextView;
        @BindView(R.id.multi_answers_image_view)
        ImageView imageView;
        @BindView(R.id.checkbox_a)
        CheckBox checkBoxA;
        @BindView(R.id.checkbox_b)
        CheckBox checkBoxB;
        @BindView(R.id.checkbox_c)
        CheckBox checkBoxC;
        @BindView(R.id.checkbox_d)
        CheckBox checkBoxD;
        @BindView(R.id.multi_answers_hint_button)
        Button hintButton;

        public MultiAnswersViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void createToast(String displayString) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, displayString, duration);
        toast.show();
    }
}
