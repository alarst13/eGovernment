package com.example.egovernment.TextGram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.egovernment.News.News;
import com.example.egovernment.R;

import java.util.LinkedList;

public class TextGramAdapter extends BaseAdapter {

    Context context;
    LinkedList <TextMessage> textMessages;
    String phone;

    public TextGramAdapter(Context context, LinkedList<TextMessage> textMessages , String phone) {
        this.context = context;
        this.textMessages = textMessages;
        this.phone = phone;
    }

    @Override
    public int getCount() {
        return textMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return textMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.text_gram_list_adapter , parent , false);
        }
        TextView phoneNumber = convertView.findViewById(R.id.text_gram_list_phone_number);
        TextView m = convertView.findViewById(R.id.text_gram_list_message);

        m.setText(textMessages.get(position).getMessage());
        if (!phone.equals(textMessages.get(position).getSender())){
            phoneNumber.setText(textMessages.get(position).getSender());
        }
        else {
            phoneNumber.setText(textMessages.get(position).getReceiver());
        }

        return convertView;
    }
}
