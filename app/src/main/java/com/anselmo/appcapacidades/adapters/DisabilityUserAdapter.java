package com.anselmo.appcapacidades.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anselmo.appcapacidades.R;
import com.anselmo.appcapacidades.models.DisabilityUser;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.List;

/**
 * Created by anselmo on 2/29/16.
 */
public class DisabilityUserAdapter extends RecyclerView.Adapter<DisabilityUserAdapter.ViewHolder> {
    private Activity context;
    private List<DisabilityUser> items;

    public DisabilityUserAdapter(Activity context, List<DisabilityUser> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public DisabilityUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_disability_user, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(DisabilityUserAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        DisabilityUser userInfo = items.get(position);

        viewHolder.name.setText( userInfo.getName() + " " + userInfo.getFatherLastname() );
        viewHolder.typeDisability.setText( userInfo.getTypeDisability() );
        viewHolder.levelDisability.setText( userInfo.getLevelDisability() );
        viewHolder.dateBirthday.setText( userInfo.getDateBirthday() );
        viewHolder.gender.setText( userInfo.getGender() );
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView typeDisability;
        public TextView levelDisability;
        public TextView dateBirthday;
        public TextView gender;

        public ViewHolder(View itemView) {

            super(itemView);

            name             = (TextView) itemView.findViewById(R.id.item_fullname);
            typeDisability   = (TextView) itemView.findViewById(R.id.item_type_disability);
            levelDisability  = (TextView) itemView.findViewById(R.id.item_level_disability);
            dateBirthday     = (TextView) itemView.findViewById(R.id.item_date_birthday);
            gender           = (TextView) itemView.findViewById(R.id.item_gender);

            name.setTypeface(EasyFonts.robotoLight(context));
            typeDisability.setTypeface(EasyFonts.robotoLight(context));
            levelDisability.setTypeface(EasyFonts.robotoLight(context));
            dateBirthday.setTypeface(EasyFonts.robotoLight(context));
            gender.setTypeface(EasyFonts.robotoLight(context));

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();

            /*
            if (items.get(position).getStores().isEmpty()) {
                new SnackBar.Builder(context)
                        .withMessage(context.getString(R.string.message_empyt_stores))
                        .withTypeFace(EasyFonts.robotoLight(context))
                        .withTextColorId(R.color.color_primary)
                        .withStyle(SnackBar.Style.DEFAULT)
                        .withDuration(SnackBar.MED_SNACK)
                        .show();
            } else {
                Intent i = new Intent(context, StoreDetailActivity.class);
                i.putExtra("arrayPosition", position);
                context.startActivity(i);
            }
            */
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return items.size();
    }

}
