package com.moringaschool.gamify.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.gamify.R;
import com.moringaschool.gamify.models.GameSearchResponse;
import com.moringaschool.gamify.ui.GamesDetailsActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesViewHolder>{
    private Context mContext;
    private List<GameSearchResponse> mGames;


    public GamesListAdapter(Context context, List<GameSearchResponse> games){
        mContext =context;
        mGames = games;
    }

    public GamesListAdapter(List<GameSearchResponse> games) {
        this.mGames=games;
    }


    @Override
    public GamesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list_item, parent, false);
        return new GamesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(GamesViewHolder holder, int position) {
        holder.mGameNameTextView.setText(mGames.get(position).getTitle());
        holder.mDescriptionNameTextView.setText(mGames.get(position).getShortDescription());
        holder.mDateTextView.setText(mGames.get(position).getReleaseDate());
        Picasso.get()
                .load(mGames.get(position).getThumbnail())
                .centerCrop()
                .fit()
                .into(holder.mGameImageView);

    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }


    public class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.gameImageView)
        ImageView mGameImageView;
        @BindView(R.id.gameNameTextView)
        TextView mGameNameTextView;
        @BindView(R.id.descriptionTextView) TextView mDescriptionNameTextView;
        @BindView(R.id.dateTextView) TextView mDateTextView;

        private Context mContext;


        public GamesViewHolder( View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

//        public void bindGames(GameSearchResponse gameSearchResponse) {
//            mGameNameTextView.setText(gameSearchResponse.getTitle());
//            mDescriptionNameTextView.setText(gameSearchResponse.getShortDescription());
//            mDateTextView.setText(gameSearchResponse.getReleaseDate());
//            Picasso.get().load(gameSearchResponse.getThumbnail()).into(mGameImageView);
//        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GamesDetailsActivity.class);
            intent.putExtra("position",itemPosition);
            intent.putExtra("games", Parcels.wrap(mGames));
            mContext.startActivity(intent);
        }
    }
}
