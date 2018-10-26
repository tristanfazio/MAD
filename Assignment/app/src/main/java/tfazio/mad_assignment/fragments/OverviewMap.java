package tfazio.mad_assignment.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tfazio.mad_assignment.DataClasses.Area;
import tfazio.mad_assignment.DataClasses.GameData;
import tfazio.mad_assignment.R;
import tfazio.mad_assignment.activities.Overview;

public class OverviewMap extends Fragment
{
    GameData gameData;
    int mapHeight;
    int mapWidth;
    MapAdapter adapter;

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
        RecyclerView recycView = v.findViewById(R.id.mapRecView);
        recycView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                mapHeight,
                GridLayoutManager.HORIZONTAL,
                false));

        adapter = new MapAdapter();
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
        Area area;
        Overview activity;

        public MapDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell,parent,false));
            int size = parent.getMeasuredHeight()/mapHeight +1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;
            activity = (Overview)getActivity();

            cellBackground = (ImageView)itemView.findViewById(R.id.cellBackground);
            cellFav = (ImageView)itemView.findViewById(R.id.cellFav);
            cellPlayer = (ImageView)itemView.findViewById(R.id.cellPlayer);

            //listener for cell
            cellBackground.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                   activity.getAreaInfoFrag().setAreaInfo(area);
                   adapter.notifyDataSetChanged();
                }
            });
        }

        public void bind(Area area)
        {
            this.area = area;//bind the area

            //set background
            if(!area.isExplored())//if it isn't explored
            {
                //hidden area
                cellBackground.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
            else
            {//it is explored
                if(area.isTown())//its a town
                {
                    cellBackground.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_building2));
                }
                else//its a wilderness
                {
                    cellBackground.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_tree4));
                }
            }

            //set star
            if(area.getStarred())
            {
                cellFav.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.star_big_on));
                cellFav.bringToFront();
            }
            else
            {
                cellFav.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }

            //set player
            if(area.getX()==gameData.getPlayer().getPosition()[0] && area.getY()==gameData.getPlayer().getPosition()[1])
            {
                cellPlayer.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.presence_online));
                cellFav.bringToFront();
            }
            else
            {
                cellPlayer.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }
        }
    }

    public void notifyAdapter()
    {
        adapter.notifyDataSetChanged();
    }
}
