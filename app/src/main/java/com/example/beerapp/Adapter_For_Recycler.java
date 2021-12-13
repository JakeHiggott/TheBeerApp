package com.example.beerapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

//https://www.youtube.com/watch?v=__OMnFR-wZU

//https://www.youtube.com/watch?v=vBxNDtyE_Co

public class Adapter_For_Recycler extends RecyclerView.Adapter<Adapter_For_Recycler.BrewRecycler> {

    ArrayList<String> NamesArray = new ArrayList<>();
    private RecyclerViewClickListener listener;

    public Adapter_For_Recycler(ArrayList<String> arrayList, RecyclerViewClickListener listener){
        this.NamesArray = arrayList;
        this.listener = listener;
        Log.d("ADAPTER 1 ARRAY SIZE", String.valueOf(NamesArray.size()));
    }
    public class BrewRecycler extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView nameTxt;

        public BrewRecycler(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.nameListTextView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public Adapter_For_Recycler.BrewRecycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.brew_list_items,parent,false);
        return new BrewRecycler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_For_Recycler.BrewRecycler holder, int position) {
        String name = NamesArray.get(position).toString();
        holder.nameTxt.setText(name);

    }

    @Override
    public int getItemCount() {
        return NamesArray.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);

    }
}
