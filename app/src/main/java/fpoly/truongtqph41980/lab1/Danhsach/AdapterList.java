package fpoly.truongtqph41980.lab1.Danhsach;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.truongtqph41980.lab1.R;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private Context context;
    private ArrayList<CityModel> list ;

    public AdapterList(Context context, ArrayList<CityModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       CityModel cityModel = list.get(position);
        holder.txtName.setText("Name: " + cityModel.getName());
        holder.txtCountry.setText("Country: " + cityModel.getCountry());
        holder.txtFoundedYear.setText("Fonunded Year: " + cityModel.getFoundedYear());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtCountry, txtFoundedYear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtFoundedYear = itemView.findViewById(R.id.txtFoundedYear);
            txtCountry = itemView.findViewById(R.id.txtCountry);
        }
    }

}
