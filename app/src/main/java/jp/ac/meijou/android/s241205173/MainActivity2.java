package jp.ac.meijou.android.s241205173;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s241205173.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s241205173.databinding.ActivityMain4Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.textView7.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        binding.textView7.setText("Result: Canceled");
                    }
                    default -> {
                        binding.textView7.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView text = findViewById(R.id.text);
        binding.button2.setOnClickListener(view -> {
            //文字列取得
            var text2_1 = binding.editTextText2.getText().toString();
            var intent = new Intent(this, MainActivity4.class);
            intent.putExtra("title", text2_1);
            startActivity(intent);
        });
        
        binding.button5.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        binding.button6.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp "));
            startActivity(intent);
        });

        binding.buttonKidou.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity4.class);
            var text2_1 = binding.editTextText2.getText().toString();
            intent.putExtra("title", text2_1);
            getActivityResult.launch(intent);
                });

    }
}