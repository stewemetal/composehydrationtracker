package hu.stewemetal.composehydrationtracker.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import hu.stewemetal.composehydrationtracker.R

class CustomButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val button: Button
    private val loader: ProgressBar

    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_button, this, true)
        button = findViewById(R.id.custom_button)
        loader = findViewById(R.id.custom_button_loader)
    }

    fun setText(text: String) {
        button.text = text
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        button.setOnClickListener { listener?.onClick(button) }
    }
}