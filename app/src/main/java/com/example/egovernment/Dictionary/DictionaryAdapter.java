package com.example.egovernment.Dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.egovernment.ColorsGame.ColorsGameActivity;
import com.example.egovernment.R;

import java.util.LinkedList;

public class DictionaryAdapter extends BaseAdapter {

    LinkedList <Word> words;
    Context context;

    public DictionaryAdapter(LinkedList<Word> words, Context context) {
        this.words = words;
        this.context = context;
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int position) {
        return words.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.dictionary_list , parent , false);
        }
        TextView english = convertView.findViewById(R.id.dictionary_list_adapter_english);
        TextView persian = convertView.findViewById(R.id.dictionary_list_adapter_persian);
        english.setText(words.get(position).getEnglish());
        persian.setText(words.get(position).getPersian());
        return convertView;
    }
}
