package com.ufs.sicaa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ufs.sicaa.R;
import com.ufs.sicaa.model.Criterio;

import java.util.List;

/**
 * Created by guilhermeboroni on 09/04/17.
 */

public class AvaliacaoAdapter extends ArrayAdapter<Criterio> {

private Context ctx;

public AvaliacaoAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        }

public AvaliacaoAdapter(Context context, int resource, List<Criterio> items) {
        super(context, resource, items);
        }

public void setCtx(Context ctx){
        this.ctx = ctx;
        }

@Override
public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        v = vi.inflate(R.layout.row_avaliacao, null);
        }

        TextView criterio = (TextView) v.findViewById(R.id.criterio);
        criterio.setText(getItem(position).getNome());

        return v;
        }

}