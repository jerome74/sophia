package indra.com.icertify.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

/**
 * Created by adifrancesco on 23/10/2017.
 */

public class CustomProgressDialog extends ProgressDialog
{
    private Context context;

    public CustomProgressDialog(Context context)
    {
        super(context);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int theme)
    {
        super(context, theme);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(0, 255, 255, 255)));
        setIndeterminate(true);
        setCancelable(false);
        //        DrawingView drawingView = new DrawingView(context,false,"#006643","#C4262E");
        DrawingView drawingView = new DrawingView(context,false,"#FFFFFF","#C4262E");
        setContentView(drawingView);
        drawingView.displayProgress();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
