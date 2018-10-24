package tfazio.mad_assignment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class OverviewMap extends Fragment
{
    GameData gameData;
    int mapHeight;
    int mapWidth;

    public OverviewMap() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        gameData = gameData.getInstance();
        mapHeight = gameData.getYMax()+1;
        mapWidth=gameData.getXMax()+1;

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        RecyclerView recycView = (RecyclerView) getView().findViewById(R.id.mapRecView);
        recycView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                mapHeight,
                GridLayoutManager.HORIZONTAL,
                false));

        MapAdapter adapter = new MapAdapter();
        recycView.setAdapter(adapter);
        return v;
    }

    private class MapAdapter extends RecyclerView.Adapter<MapDataVHolder>
    {
        //default construct
        public MapAdapter()
        {

        }

        @Override
        public int getItemCount() {
            return mapHeight * mapWidth;
        }

        @NonNull
        @Override
        public MapDataVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new MapDataVHolder(li,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull MapDataVHolder mapDataVHolder, int i)
        {
            int[] xy = {i%mapHeight,i/mapHeight};
            mapDataVHolder.bind(gameData.getArea(xy));
        }
    }

    private class MapDataVHolder extends RecyclerView.ViewHolder
    {
        ImageView cellBackground;
        ImageView cellFav;
        ImageView cellPlayer;

        public MapDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell,parent,false));
            int size = parent.getMeasuredHeight()/mapHeight +1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            cellBackground = (ImageView)itemView.findViewById(R.id.cellBackground);
            cellFav = (ImageView)itemView.findViewById(R.id.cellFav);
            cellPlayer = (ImageView)itemView.findViewById(R.id.cellPlayer);
        }

        public void bind(Area area)
        {

        }
    }
}
