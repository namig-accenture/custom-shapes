package com.example.namigtahmazli.playingwithandroiddesign

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        demo.apply {
            background = dropShadow(
                    mShape = HalfCutView(150 * context.resources.displayMetrics.density),
                    elevationGravity = Gravity.BOTTOM,
                    elevationSides = arrayOf(Sides.Bottom)
            )
        }

        demo_button.apply {
            background = dropShadow(
                    mShape = CutShape(12 * context.resources.displayMetrics.density),
                    ripple = true
            )
        }

        demo_circle.apply {
            background = dropShadow(
                    mShape = CircleShape()
            )
        }

        bottom_bar.apply {
            background = dropShadow(
                    mShape = NotchedBottomBarShape(
                            30 * context.resources.displayMetrics.density
                    ),
                    elevationSides = arrayOf(Sides.Top)
            )
        }

        btn_add.apply {
            background = dropShadow(
                    mShape = CircleShape(),
                    ripple = true
            )
        }
    }
}
