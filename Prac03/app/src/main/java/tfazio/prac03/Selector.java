package tfazio.prac03;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Selector.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Selector#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Selector extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private StructureData structureData;

    private OnFragmentInteractionListener mListener;

    public Selector() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Selector.
     */
    // TODO: Rename and change types and number of parameters
    public static Selector newInstance(String param1, String param2) {
        Selector fragment = new Selector();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recycView = (RecyclerView) getView().findViewById(R.id.mapRecyclerView);
        recycView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        SelectorAdapter adapter = new SelectorAdapter();
        recycView.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_selector, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private class SelectorAdapter extends RecyclerView.Adapter<StructureDataVHolder>
    {
        //default construct
        public SelectorAdapter()
        {

        }

        @Override
        public int getItemCount() {
            return structureData.size();
        }

        @NonNull
        @Override
        public StructureDataVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new StructureDataVHolder(li,viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull StructureDataVHolder structureDataVHolder, int i)
        {
            structureDataVHolder.bind(structureData.get(i));
        }
    }

    private class StructureDataVHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView label;

        public StructureDataVHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell,parent,false));

            image =  (ImageView) itemView.findViewById(R.id.picture);
            label =  (TextView) itemView.findViewById(R.id.label);

        }

        public void bind(Structure data)
        {
            image.setImageResource(data.getDrawableId());
            label.setText(data.getLabel());
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
