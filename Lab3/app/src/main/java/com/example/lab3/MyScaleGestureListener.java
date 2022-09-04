//package com.example.lab3;
//
//import android.view.ScaleGestureDetector;
//
//public class MyScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//    private float mScaleFactor = 100f;
//    DrawView drawView;
//
//    @Override
//    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
//        mScaleFactor *= scaleGestureDetector.getScaleFactor();
//        mScaleFactor = Math.max(1f, Math.min(mScaleFactor, 1000f));
//
//        invalidate();
//
//        return true;
//    }
//}
