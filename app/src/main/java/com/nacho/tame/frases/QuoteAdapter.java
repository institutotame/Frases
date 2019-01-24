package com.nacho.tame.frases;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>{

    private List<Quote> mQuoteList;
    private LayoutInflater mInflater;
    private Context context;


    public QuoteAdapter(Context context,List<Quote> quoteList){
        mInflater=LayoutInflater.from(context);
        mQuoteList = quoteList;
        this.context=context;
    }


    class QuoteViewHolder extends RecyclerView.ViewHolder {
    public final TextView txtTitle;
    public final TextView txtContent;


    public QuoteViewHolder(View itemView) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtContent = itemView.findViewById(R.id.txtContent);
    }
}
    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater
                .inflate(R.layout.quote_item, viewGroup, false);
        return new QuoteViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder quoteViewHolder, int position) {
        Quote quote = mQuoteList.get(position);
        quoteViewHolder.txtTitle.setText(quote.getTitle());

        Spanned spanned = Html.fromHtml(quote.getContent());
        quoteViewHolder.txtContent.setText(spanned);

    }

    @Override
    public int getItemCount() {
        if(mQuoteList==null)
            return 0;

        return mQuoteList.size();
    }

    public void setData(List<Quote> newList){
        mQuoteList = newList;
        notifyDataSetChanged();
    }
}