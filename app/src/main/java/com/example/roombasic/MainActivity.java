package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button buttonInsert,buttonUpdate,buttonDelete,buttonClear;
    WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        textView = findViewById(R.id.textView);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for(int i=0;i<words.size();i++) {
                    Word word = words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseMeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("Hello","你好！");
                Word word2 = new Word("World","世界！");
                wordViewModel.insertWords(word1,word2);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi","你好啊!");
                word.setId(90);
                wordViewModel.updateWords(word);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi","你好啊!");
                word.setId(90);
                wordViewModel.deleteWords(word);
            }
        });

    }


}
