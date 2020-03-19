package com.example.ptms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyTravelPlansActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessBtn;
    private TextView txtTotalAmount, txtMsg1;

    private int overTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_travel_plans);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NextProcessBtn = (Button) findViewById(R.id.next_process_btn);
        txtTotalAmount = (TextView) findViewById(R.id.total_price);
        //txtMsg1=(TextView) findViewById(R.id.msg1);

        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtTotalAmount.setText("Total Price" + String.valueOf(overTotalPrice));

                Intent intent = new Intent(MyTravelPlansActivity.this, PasMenuActivity.class);
                intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                startActivity(intent);
                finish();
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        CheckOrderState();
//
//        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("bookingList");
//
//        FirebaseRecyclerOptions<Book> options =
//                new FirebaseRecyclerOptions.Builder<Book>()
//                        .setQuery(cartListRef.child("passengerBookingView")
//                                .child(Prevelent.currentOnlineUser.getPhone())
//                                .child("busBooking"), Book.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<Book, BookViewHolder> adapter
//                = new FirebaseRecyclerAdapter<Book, BookViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull final Book model) {
//
//                holder.txtProductQuantity.setText("Quantitiy = "+ model.getQuantity());
//                holder.txtProductPrice.setText("Price $ "+model.getPrice());
//                holder.txtProductName.setText(model.getPname());
//
//                int oneTyprProductTPrice = ((Integer.valueOf(model.getPrice())))*Integer.valueOf(model.getQuantity());
//                overTotalPrice = overTotalPrice+oneTyprProductTPrice;
//
//
//                holder.itemView.setOnClickListener( new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        CharSequence options[] = new CharSequence[]
//                                {
//                                        "Edit",
//                                        "Remove"
//                                };
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
//                        builder.setTitle("Cart Options");
//
//                        builder.setItems(options, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (which == 0)
//                                {
//                                    Intent intent= new Intent(CartActivity.this, ProductDetailsActivity.class);
//                                    intent.putExtra("pid", model.getPid());
//                                    startActivity(intent);
//
//                                }
//                                if (which==1)
//                                {
//                                    cartListRef.child("User View")
//                                            .child(Prevelent.currentOnlineUser.getPhone())
//                                            .child("Products")
//                                            .child(model.getPid())
//                                            .removeValue()
//                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    if (task.isSuccessful())
//                                                    {
//                                                        Toast.makeText(CartActivity.this, "Item removed", Toast.LENGTH_SHORT).show();
//
//                                                        Intent intent= new Intent(CartActivity.this, HomeActivity.class);
//                                                        startActivity(intent);
//                                                    }
//                                                }
//                                            });
//                                }
//
//                            }
//                        });
//                        builder.show();
//                    }
//                });
//
//            }
//
//            @NonNull
//            @Override
//            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                //return null;
//                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_items_layout, viewGroup, false);
//                CartViewHolder holder = new CartViewHolder(view);
//                return holder;
//
//            }
//        };
//
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
//    }
//
//    private void CheckOrderState()
//    {
//        DatabaseReference orderRef;
//        orderRef = FirebaseDatabase.getInstance().getReference()
//                .child("Orders")
//                .child(Prevalent.currentOnlineUser.getPhone());
//
//        orderRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists())
//                {
//                    String shippingState = dataSnapshot.child("state").getValue().toString();
//                    String userName = dataSnapshot.child("name").getValue().toString();
//                    if (shippingState.equals("shipped"))
//                    {
//                        txtTotalAmount.setText("Dear" +userName+ "\n book is shipped ");
//                        recyclerView.setVisibility(View.GONE);
//
//                        txtMsg1.setVisibility(View.VISIBLE);
//                        txtMsg1.setText("Your ordred book will be recieved as soon as possible");
//
//                        NextProcessBtn.setVisibility(View.GONE);
//
//                        Toast.makeText(CartActivity.this, "You can buy more Books", Toast.LENGTH_SHORT).show();
//
//                    }
//                    else if (shippingState.equals("not shipped"))
//                    {
//                        txtTotalAmount.setText("Shipping State = Not Shipped");
//                        recyclerView.setVisibility(View.GONE);
//
//                        txtMsg1.setVisibility(View.VISIBLE);
//                        NextProcessBtn.setVisibility(View.GONE);
//
//                        Toast.makeText(CartActivity.this, "You can buy more Books", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }

}
