//package com.example.ptms;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.ptms.Model.BusRoute;
//import com.example.ptms.ViewHolder.BusTimeDisplayViewHolder;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//
//public class Search4BookActivity extends AppCompatActivity {
//    private EditText mSearchField;
//    private ImageButton mSearchBtn;
//
//    private RecyclerView mResultList;
//
//    private DatabaseReference mTimeDatabase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search4_book);
//
//        mTimeDatabase = FirebaseDatabase.getInstance().getReference("Users");
//
//
//        mSearchField = (EditText) findViewById(R.id.search_field);
//        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
//
//        mResultList = (RecyclerView) findViewById(R.id.result_list);
//        mResultList.setHasFixedSize(true);
//        mResultList.setLayoutManager(new LinearLayoutManager(this));
//
//        mSearchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String searchText = mSearchField.getText().toString();
//
//                firebaseUserSearch(searchText);
//
//            }
//        });
//    }
//
//
//    private void firebaseUserSearch(String searchText) {
//
//        Toast.makeText(Search4BookActivity.this, "Started Search", Toast.LENGTH_LONG).show();
//
//        Query firebaseSearchQuery = mTimeDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
//
//        FirebaseRecyclerAdapter<BusRoute, BusTimeDisplayViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<BusRoute, BusTimeDisplayViewHolder>(
//
//                BusRoute.class,
//                R.layout.list_layout,
//                BusTimeDisplayViewHolder.class,
//                firebaseSearchQuery
//
//        ) {
//            @NonNull
//            @Override
//            public BusTimeDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull BusTimeDisplayViewHolder busTimeDisplayViewHolder, int i, @NonNull BusRoute busRoute) {
//
//            }
//
//            @Override
//            protected void populateViewHolder(BusTimeDisplayViewHolder viewHolder, BusRoute model, int position) {
//
//
//                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getStatus(), model.getImage());
//
//            }
//        };
//
//        mResultList.setAdapter(firebaseRecyclerAdapter);
//
//    }
//
//    // View Holder Class
//
//    public static class UsersViewHolder extends RecyclerView.ViewHolder {
//
//        View mView;
//
//        public UsersViewHolder(View itemView) {
//            super(itemView);
//
//            mView = itemView;
//
//        }
//
//        public void setDetails(Context ctx, String userName, String userStatus, String userImage) {
//
//            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
//            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
//            ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);
//
//
//            user_name.setText(userName);
//            user_status.setText(userStatus);
//
//            Glide.with(ctx).load(userImage).into(user_image);
//
//
//        }
//
//
//    }
//}
