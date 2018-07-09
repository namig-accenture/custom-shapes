package com.example.namigtahmazli.playingwithandroiddesign

import android.content.res.ColorStateList
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View

enum class Sides {
    Left, Top, Right, Bottom
}

fun View.dropShadow(
        @DimenRes elevation: Int = R.dimen.elevation,
        @DimenRes cornerRadius: Int = R.dimen.cornerRadius,
        @ColorRes shadowColor: Int = R.color.shadowColor,
        @ColorRes backgroundColor: Int = android.R.color.background_light,
        mShape: Shape,
        elevationGravity: Int = Gravity.CENTER,
        elevationSides: Array<Sides> = arrayOf(Sides.Left, Sides.Top, Sides.Right, Sides.Bottom),
        ripple: Boolean = false,
        @ColorRes rippleColor: Int = R.color.colorAccent
): Drawable {
    val mElevation = context.resources.getDimension(elevation)
    val mCornerRadius = context.resources.getDimension(cornerRadius)
    val mShadowColor = ContextCompat.getColor(context, shadowColor)
    val mBackgroundColor = ContextCompat.getColor(context, backgroundColor)
    val mRippleColor = ContextCompat.getColor(context, rippleColor)

    val elevationPadding = Rect(0, 0, 0, 0)
    elevationSides.forEach {
        when (it) {
            Sides.Left -> elevationPadding.left = mElevation.toInt()
            Sides.Right -> elevationPadding.right = mElevation.toInt()
            Sides.Top -> elevationPadding.top = mElevation.toInt()
            Sides.Bottom -> elevationPadding.bottom = mElevation.toInt()
        }
    }

    val dy = when (elevationGravity) {
        Gravity.CENTER -> 0f
        Gravity.TOP -> {
            elevationPadding.top += mElevation.toInt()
            -mElevation.toInt() / 3f
        }
        Gravity.BOTTOM -> {
            elevationPadding.bottom += mElevation.toInt()
            mElevation.toInt() / 3f
        }
        else -> 0f
    }

    val shapeDrawable = ShapeDrawable().apply {
        setPadding(elevationPadding)
        paint.color = mBackgroundColor
        paint.setShadowLayer(mCornerRadius / 3, 0f, dy, mShadowColor)
        shape = mShape
    }

    setLayerType(View.LAYER_TYPE_SOFTWARE, shapeDrawable.paint)

    val drawables = mutableListOf<Drawable>().apply {
        add(shapeDrawable)
        if (ripple) {
            add(RippleDrawable(
                    ColorStateList.valueOf(mRippleColor),
                    null,
                    shapeDrawable
            ))
        }
    }
    return LayerDrawable(drawables.toTypedArray()).apply {
        setLayerInset(0,
                elevationPadding.left,
                elevationPadding.top,
                elevationPadding.right,
                elevationPadding.bottom
        )
    }
}