package com.example.fitneshelperfinal.treningFolder;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import com.example.fitneshelperfinal.R;

public class TreningClass extends Activity {
 private Button StartTrening, PlaneTrening;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trening_menu);
    }

    public void StartTrening_Click(View v){



    }
    public void PlaneTrening_Click(View v){
        Intent intent = new Intent(v.getContext(), TreningSetPlane.class);
        startActivity(intent);

    }
}
