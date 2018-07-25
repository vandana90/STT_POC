package com.stt.stt.common.views.splash;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.stt.stt.R;
import com.stt.stt.common.constants.NuanceTestCreds;
import com.stt.stt.databinding.ActivitySplashBinding;
import com.nuance.speechkit.Audio;
import com.nuance.speechkit.AudioPlayer;
import com.nuance.speechkit.DetectionType;
import com.nuance.speechkit.Language;
import com.nuance.speechkit.Recognition;
import com.nuance.speechkit.RecognitionType;
import com.nuance.speechkit.RecognizedPhrase;
import com.nuance.speechkit.ResultDeliveryType;
import com.nuance.speechkit.Session;
import com.nuance.speechkit.Transaction;
import com.nuance.speechkit.TransactionException;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.btnSpeak.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnSpeak) {
            createNuanceSession();
        }
    }

    /**
     * create nuance session
     */
    private void createNuanceSession() {
        Session session = Session.Factory.session(this, NuanceTestCreds.CONF_SERVER_URL, NuanceTestCreds.APP_KEY);
        Transaction.Options options = new Transaction.Options();
        options.setRecognitionType(RecognitionType.DICTATION);
        options.setDetection(DetectionType.Long);
        // options.setLanguage(Language.ENG_USA);
        options.setResultDeliveryType(ResultDeliveryType.FINAL);
        createNuanceTransaction(session, options);
    }

    private void createNuanceTransaction(Session session, Transaction.Options options){
        Transaction transaction = session.recognize(options, new Transaction.Listener() {
            public void onStartedRecording(Transaction transaction) {
                System.out.print("aud " + transaction.getAudioLevel());
            }

            public void onFinishedRecording(Transaction transaction) {
                System.out.print("onFinishedRecording " + transaction.getSession());
               // transaction.stopRecording();
                createNuanceSession();
            }

            public void onRecognition(Transaction transaction, Recognition recognition) {
                //Take the best result
                String topRecognitionText = recognition.getText();
                createEditText().setText(topRecognitionText);
                //Or iterate through the NBest list
                List<RecognizedPhrase> nBest = recognition.getDetails();
                for (RecognizedPhrase phrase : nBest) {
                    String text = phrase.getText();
                    double confidence = phrase.getConfidence();
                    System.out.println("rec text " + text +" "+confidence);
                }
            }

            public void onSuccess(Transaction transaction, String s) {
                System.out.print("onSuccess " + s);
            }

            public void onError(Transaction transaction, String s, TransactionException e) {
                System.out.print("onError " + e);
            }
        });
    }

    private EditText createEditText(){
        EditText editText = new EditText(this);
        binding.lnrEdt.addView(editText);
        return editText;
    }
}
