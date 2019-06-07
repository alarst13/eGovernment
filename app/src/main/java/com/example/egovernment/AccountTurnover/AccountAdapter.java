package com.example.egovernment.AccountTurnover;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.egovernment.DatabaseAccess;
import com.example.egovernment.R;
import com.example.egovernment.model.Owner;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends BaseAdapter {
    String card_number;
    String from_date;
    String to_date;
    Context context;
    List<Owner> owners;

    public AccountAdapter(Context context, String card_number, String from_date, String to_date) {
        this.card_number = card_number;
        this.from_date = from_date;
        this.to_date = to_date;
        this.context = context;

        DatabaseAccess databaseAccess = new DatabaseAccess(context);

        owners = new ArrayList<>();
        Cursor c = databaseAccess.getDb().rawQuery("SELECT * FROM AccountTurnover WHERE card_number = ? AND date >= ? AND date <= ? ",new String[]{card_number,from_date,to_date});
        c.moveToFirst();
        while(!c.isAfterLast()){
            String card_num = c.getString(0);
            String date = c.getString(1);
            String type = c.getString(2);
            int amount = c.getInt(3);
            int bank_balance = c.getInt(4);
            Owner owner = new Owner(card_num, date, type, amount, bank_balance);
            owners.add(owner);
            c.moveToNext();
        }
    }

    @Override
    public int getCount() {
        return owners.size();
    }

    @Override
    public Object getItem(int position) {
        return owners.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_account_adapter, parent, false);
        }
        TextView card_number_txt = convertView.findViewById(R.id.card_number_txt);
        TextView date_txt = convertView.findViewById(R.id.date_txt);
        TextView type_txt = convertView.findViewById(R.id.type_txt);
        TextView amount_txt = convertView.findViewById(R.id.amount_txt);
        TextView bank_balance_txt = convertView.findViewById(R.id.bank_balance_txt);

        card_number_txt.setText(owners.get(position).getCard_number());
        date_txt.setText(owners.get(position).getDate());
        type_txt.setText(owners.get(position).getType());
        amount_txt.setText(String.valueOf(owners.get(position).getAmount()));
        bank_balance_txt.setText(String.valueOf(owners.get(position).getBank_balance()));

        return convertView;
    }

    public List<Owner> getOwners() {
        return owners;
    }
}
