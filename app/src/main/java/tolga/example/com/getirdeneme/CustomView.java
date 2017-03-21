package tolga.example.com.getirdeneme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.List;

import tolga.example.com.getirdeneme.model.Element;

public class CustomView extends View {

    Paint paint;
    List<Element> elementsList;

    public CustomView(Context context) {
        super(context);
        paint = new Paint();
    }
    public CustomView(Context context, List<Element> elementsList){
        this(context);
        this.elementsList = elementsList;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (elementsList.size() <= 0) {
            return;
        }
        for(Element element: elementsList){
            switch (element.getType()){
                case "circle":
                    drawCircle(canvas, element);
                    break;
                case "rectangle":
                    drawRectangle(canvas, element);
                    break;
                default:
                    Log.e("CustomView: ", "Unknown shape type error: " + element.getType());
            }
        }
    }

    private void drawCircle(Canvas canvas, Element circle){
        paint.setColor(Color.parseColor("#" + circle.getColor()));
        canvas.drawCircle(circle.getXPosition(),
                circle.getYPosition(), circle.getRadius(), paint);
    }

    private void drawRectangle(Canvas canvas, Element rectangle){
        int left = rectangle.getXPosition();
        int top = rectangle.getYPosition();
        int right = left + rectangle.getWidth();
        int bottom = top + rectangle.getHeight();

        paint.setColor(Color.parseColor("#" + rectangle.getColor()));
        canvas.drawRect(left, top, right, bottom, paint);
    }
}
