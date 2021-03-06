package in.ac.iitb.gymkhana.iitbapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import in.ac.iitb.gymkhana.iitbapp.R;
import in.ac.iitb.gymkhana.iitbapp.data.Body;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BodyCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BodyCardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_BODY = "body";

    // TODO: Rename and change types of parameters
    private Body body;

    public BodyCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param arg_body Body passed.
     * @return A new instance of fragment BodyCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BodyCardFragment newInstance(Body arg_body) {
        BodyCardFragment fragment = new BodyCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BODY, new Gson().toJson(arg_body));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            body = new Gson().fromJson(getArguments().getString(ARG_BODY), Body.class);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.body_card_layout);
        ImageView bodyAvatar = (ImageView) getView().findViewById(R.id.body_card_avatar);
        TextView bodyName = (TextView) getView().findViewById(R.id.body_card_name);
        TextView bodyDescription = (TextView) getView().findViewById(R.id.body_card_description);

        bodyName.setText(body.getBodyName());
        bodyDescription.setText(body.getBodyShortDescription());
        Picasso.with(getContext()).load(body.getBodyImageURL()).into(bodyAvatar);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Show the next fragment and destroy the page */
                BodyFragment bodyFragment = BodyFragment.newInstance(body);
                bodyFragment.setArguments(getArguments());
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.framelayout_for_fragment, bodyFragment, bodyFragment.getTag());
                ft.addToBackStack(bodyFragment.getTag());
                ft.commit();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.body_card_view, container, false);
    }

}
