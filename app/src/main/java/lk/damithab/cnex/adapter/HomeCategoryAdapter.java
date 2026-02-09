package lk.damithab.cnex.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lk.damithab.cnex.R;
import lk.damithab.cnex.dto.CategoryDTO;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private final List<CategoryDTO> categoryList;

    private Context context;

    public HomeCategoryAdapter(List<CategoryDTO> categoryDTOList){
        this.categoryList = categoryDTOList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_category,parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryDTO category = categoryList.get(position);
        holder.categoryName.setText(category.getCategory());

        String imageName = "category_"+category.getCategory().toLowerCase(); // name without extension
        String packageName = context.getPackageName();

// 1. Get the integer ID of the resource
        int resId = context.getResources().getIdentifier(imageName, "drawable", packageName);

// 2. Build the URI
        Uri uri = Uri.parse("android.resource://" + packageName + "/" + resId);
        holder.categoryImage.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryImage = itemView.findViewById(R.id.home_c_image);
            this.categoryName = itemView.findViewById(R.id.home_c_text);
        }

        public ImageView getCategoryImage() {
            return categoryImage;
        }

        public TextView getCategoryName() {
            return categoryName;
        }
    }
}
