package mikecanco.de.kartography.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import mikecanco.de.kartography.POIArrayAdapter;
import mikecanco.de.kartography.PoiHandler;
import mikecanco.de.kartography.R;
import mikecanco.de.kartography.activities.PoiDetailActivity;
import mikecanco.de.kartography.models.Poi;

public class PoiListFragment extends Fragment {

    ListView lvPoi;
    POIArrayAdapter poiAdapter;
    PoiHandler poiHandler;
    List<Poi> images;
    ProgressBar pBar;
    RelativeLayout gradientOverlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_list_poi, null, false);
        lvPoi = (ListView) view.findViewById(R.id.lvPoi);
        pBar = (ProgressBar) view.findViewById(R.id.progressBar1);



        lvPoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                Poi poi = (Poi) arg0.getItemAtPosition(position);
                String id = poi.getObjectId();
//				ParseFile pf = poi.getPhotoFile();

                Intent i = new Intent(getActivity(), PoiDetailActivity.class);
                i.putExtra("id", id);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }

        });






//    for later    lvPoi.setOnScrollListener(endlessScrollListener);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        /// this temporarily put on hold till we figure out callbacks
//		 poiHandler = new PoiHandler();
//
//		 images = poiHandler.getPoi();


        //tried to do this with the PoiHandler object doing the call, but don't know callbacks yet.
        List<Poi> ohHaiPoi = null;
        ParseQuery<Poi> query = ParseQuery.getQuery(Poi.class);
        query.whereNotEqualTo("flagged", true).orderByDescending("updatedAt");
        query.findInBackground(new FindCallback<Poi>() {
            public void done(List<Poi> itemList, ParseException e) {
                if (e == null) {
                    // Access the array of results here
                    for(Poi poi: itemList){
                        String itemId = poi.getObjectId();
//			            Log.d("DEBUG", itemId + "ER MER GERD");
//			            Log.d("DEBUG", poi.toString());
                    }
                    pBar.setVisibility(View.INVISIBLE);
                    images = itemList;
                    poiAdapter = new POIArrayAdapter(getActivity(), images);
                    lvPoi.setAdapter(poiAdapter);
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                    Log.d("DEBUG", "Oh noooooooooooooooooooooooo");
                }
            }
        });




        super.onActivityCreated(savedInstanceState);
    }

    public POIArrayAdapter returnAdapter(){
        return poiAdapter;
    }



}
