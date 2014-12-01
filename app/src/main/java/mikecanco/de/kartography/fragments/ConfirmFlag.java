package mikecanco.de.kartography.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import mikecanco.de.kartography.R;

public class ConfirmFlag extends DialogFragment {

    public static interface ConfirmFlagListener {
        public void onFlagSuccess();
    }

    public static ConfirmFlag newInstance(String title){
        ConfirmFlag cf = new ConfirmFlag();
        Bundle args = new Bundle();
        args.putString("title", title);
        cf.setArguments(args);
        return cf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_flag, container);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        Button confirm = (Button) view.findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmFlagListener listener = (ConfirmFlagListener) getActivity();
                listener.onFlagSuccess();
                ConfirmFlag.this.dismiss();
            }
        });
        Button cancel = (Button) view.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmFlag.this.dismiss();
            }
        });


        return view;
    }

}