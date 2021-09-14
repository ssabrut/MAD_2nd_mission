package model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.warhol.mad_2nd_mission.R;
import com.warhol.mad_2nd_mission.UserDetailActivity;

import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserViewHolder> {

    public static ArrayList<User> userList;

    public UserRVAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_card, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserRVAdapter.UserViewHolder holder, int position) {
        holder.card_full_name.setText(userList.get(position).getFull_name());
        holder.card_age.setText(userList.get(position).getAge());
        holder.card_address.setText(userList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView card_full_name, card_age, card_address;

        public UserViewHolder(View itemView) {
            super(itemView);

            card_full_name = itemView.findViewById(R.id.card_full_name);
            card_age = itemView.findViewById(R.id.card_age);
            card_address = itemView.findViewById(R.id.card_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UserDetailActivity.class);
                    intent.putExtra("full_name", card_full_name.getText());
                    intent.putExtra("age", card_age.getText());
                    intent.putExtra("address", card_address.getText());

                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i).getFull_name().equalsIgnoreCase(card_full_name.getText().toString())) {
                            intent.putExtra("index", i);
                        }
                    }

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
