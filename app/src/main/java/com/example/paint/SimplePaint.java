package com.example.paint;  // MUDAR PARA ISSO

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Stack;

public class SimplePaint extends View {
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();
    private Stack<Path> undos = new Stack<>();
    private Stack<Paint> undopaints = new Stack<>();
    private Paint paint = new Paint();
    private Path path = new Path();
    private int mode = 0;  // 0=pincel, 1=quadrado, 2=circulo
    private float startX, startY;

    public SimplePaint(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                path.reset();
                if (mode == 0) {
                    path.moveTo(x, y);
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                path.reset();
                if (mode == 0) {
                    path.moveTo(startX, startY);
                    path.lineTo(x, y);
                } else if (mode == 1) {
                    path.addRect(startX, startY, x, y, Path.Direction.CW);
                } else if (mode == 2) {
                    float r = (float) Math.sqrt(Math.pow(x-startX,2)+Math.pow(y-startY,2));
                    path.addCircle(startX, startY, r, Path.Direction.CW);
                }
                break;

            case MotionEvent.ACTION_UP:
                paths.add(new Path(path));
                paints.add(new Paint(paint));
                path.reset();
                undos.clear();
                undopaints.clear();
                break;
        }

        invalidate();
        return true;
    }

    public void setMode(int m) {
        mode = m;
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setStroke(float w) {
        paint.setStrokeWidth(w);
    }

    public void clear() {
        paths.clear();
        paints.clear();
        invalidate();
    }

    public void undo() {
        if (!paths.isEmpty()) {
            undos.push(paths.remove(paths.size()-1));
            undopaints.push(paints.remove(paints.size()-1));
            invalidate();
        }
    }

    public void redo() {
        if (!undos.isEmpty()) {
            paths.add(undos.pop());
            paints.add(undopaints.pop());
            invalidate();
        }
    }
}