package com.example.paint;

import android.graphics.Paint;
import android.graphics.Path;

public class DrawingPath {
    public Path path;
    public Paint paint;

    public DrawingPath(Path path, Paint paint) {
        this.path = new Path(path);
        this.paint = new Paint(paint);
    }
}