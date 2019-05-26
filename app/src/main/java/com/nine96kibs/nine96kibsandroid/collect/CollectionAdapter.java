package com.nine96kibs.nine96kibsandroid.collect;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nine96kibs.nine96kibsandroid.R;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionViewHolder> {

    private List<Collection> collectionList;
    private List<Integer> shownPositionList;
    private List<Integer> parentPositionList;
    private List<Integer> parentShownPositionList;

    public CollectionAdapter(List<Collection> collectionList, List<Integer> shownPositionList, List<Integer> parentPositionList, List<Integer> parentShownPositionList) {
        this.collectionList = collectionList;
        this.shownPositionList = shownPositionList;
        this.parentPositionList = parentPositionList;
        this.parentShownPositionList = parentShownPositionList;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CollectionViewHolder collectionViewHolder;
        switch (i) {
            case R.id.parent_type:
                collectionViewHolder = new CollectionParentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collection_parent, viewGroup, false));
                collectionViewHolder.setOnClickViewListener(v -> {
                    int parentPosition = (int) v.getTag(R.id.parent_position);
                    ImageView arrow = v.findViewById(R.id.arrow);
                    String arrowDirection = String.valueOf(arrow.getTag(R.id.arrow_direction));
                    if (arrowDirection.equals("down")) {
                        arrow.animate().rotation(-90f).setDuration(200).start();
                        arrow.setTag(R.id.arrow_direction, "right");
                        collapse(parentPosition);
                    } else if (arrowDirection.equals("right")) {
                        arrow.animate().rotation(0f).setDuration(200).start();
                        arrow.setTag(R.id.arrow_direction, "down");
                        expand(parentPosition);
                    }

                });
                break;
            case R.id.child_type:
                collectionViewHolder = new CollectionChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collection_child, viewGroup, false));
                collectionViewHolder.setOnClickViewListener(v -> {
                    String text = viewGroup.getResources().getString(R.string.warning);
                    Snackbar.make(v, text, Snackbar.LENGTH_SHORT).setAction("OK", null).show();
                });
                break;
            default:
                collectionViewHolder = new CollectionViewHolder(new View(null));
        }
        return collectionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder collectionViewHolder, int i) {
        Collection collection = collectionList.get(shownPositionList.get(i));
        collectionViewHolder.setTag(R.id.parent_position, collection.getParentPosition());
        collectionViewHolder.setTag(R.id.child_position, collection.getChildPosition());
        collectionViewHolder.setInfo(collection.getInfo());
        //use switch do something different (TODO)
        if (collection.getType() == R.id.parent_type) {
            ((CollectionParentViewHolder) collectionViewHolder).tagArrow(R.id.arrow_direction, "down");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return collectionList.get(shownPositionList.get(position)).getType();
    }

    @Override
    public int getItemCount() {
        return shownPositionList.size();
    }

    private void collapse(int parentPosition) {
        int positionStart = parentShownPositionList.get(parentPosition) + 1;
        int itemCount = getChangedItemCount(parentPosition);
        for (int i = 0; i < itemCount; i++)
            shownPositionList.remove(positionStart);
        for (int i = parentPosition + 1; i < parentShownPositionList.size(); i++)
            parentShownPositionList.set(i, parentShownPositionList.get(i) - itemCount);
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    private void expand(int parentPosition) {
        int positionStart = parentShownPositionList.get(parentPosition) + 1;
        int itemCount = getChangedItemCount(parentPosition);
        for (int i = 0; i < itemCount; i++)
            shownPositionList.add(positionStart + i, shownPositionList.get(positionStart + i - 1) + 1);
        for (int i = parentPosition + 1; i < parentShownPositionList.size(); i++)
            parentShownPositionList.set(i, parentShownPositionList.get(i) + itemCount);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    private int getChangedItemCount(int parentPosition) {
        if (parentPosition + 1 == parentPositionList.size())
            return collectionList.size() - parentPositionList.get(parentPosition) - 1;
        else
            return parentPositionList.get(parentPosition + 1) - parentPositionList.get(parentPosition) - 1;
    }

}
