package com.example.easyvoice;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Locale;

public class FromTextFragment extends Fragment {

    private TextToSpeech textToSpeech;
    private EditText editTextInput;
    private Button buttonSpeak;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_from_text, container, false);

        editTextInput = view.findViewById(R.id.editTextInput);
        buttonSpeak = view.findViewById(R.id.buttonSpeak);

        textToSpeech = new TextToSpeech(getContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.getDefault());
            }
        });

        buttonSpeak.setOnClickListener(v -> speakOutText());

        return view;
    }

    private void speakOutText() {
        String text = editTextInput.getText().toString();
        if (!text.isEmpty()) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Toast.makeText(getContext(), "Please enter text to speak.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
