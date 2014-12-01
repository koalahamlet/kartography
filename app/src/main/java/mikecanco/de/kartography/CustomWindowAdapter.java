package mikecanco.de.kartography;

/**
 * Created by koalahamlet on 11/30/14.
 */

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class CustomWindowAdapter implements InfoWindowAdapter{
    LayoutInflater mInflater;
    Map<Marker, String> imageStringMapMarker;
    Context ceeernterrxt;
    Boolean qwerty = true;
    int count = 0;

    public CustomWindowAdapter(LayoutInflater i,  Map<Marker, String> imageStringMapMarker2, Context context ){
        mInflater = i;
        imageStringMapMarker = imageStringMapMarker2;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        // Getting view from the layout file
        View v = mInflater.inflate(R.layout.custom_info_window, null);

//        TextView title = (TextView) v.findViewById(R.id.tv_info_window_title);
//        title.setText(marker.getTitle());
//
//        TextView description = (TextView) v.findViewById(R.id.tv_info_window_description);
//        description.setText(marker.getSnippet());

        ImageView ivThumbnail = (ImageView) v.findViewById(R.id.ivThumbnail);
        String urlImage = imageStringMapMarker.get(marker).toString();
//          Bitmap bmImage = imageStringMapMarker.get(marker);  //.toString();
//        ivThumbnail.setImageBitmap(bm);
        Picasso.with(ceeernterrxt).load(Uri.parse(urlImage)).resize(260, 250).into(ivThumbnail);
//        .placeholder(R.drawable.ic_flag)
//        Picasso.with(ceeernterrxt).load(urlImage)
//        .centerCrop().noFade()


        return v;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }
}
